package com.inventory.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.apache.catalina.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.inventory.models.Item;
import com.inventory.models.PurchaseDetailFormater;
import com.inventory.models.PurchaseDetails;
import com.inventory.models.PurchaseMaster;
import com.inventory.models.PurchaseRequest;
import com.inventory.models.SaleDetails;
import com.inventory.models.SaleMaster;
import com.inventory.models.SaleRequest;
import com.inventory.models.Supplier;
import com.inventory.services.ItemService;
import com.inventory.services.PurchaseDetailService;
import com.inventory.services.PurchaseMasterService;
import com.inventory.services.SaleDetailService;
import com.inventory.services.SaleMasterService;
import com.inventory.services.SupplierService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MasterController {

	@Autowired
	private PurchaseDetailService pds;

	@Autowired
	private PurchaseMasterService pms;
	
	@Autowired
	private SupplierService supService;

	@Autowired
	private ItemService itemService;
	
	@Autowired
	private SaleMasterService sms; 
	
	@Autowired
	private SaleDetailService sds;

	@PostMapping("/dashboard/savePurchase")
	public String savePurchase(@ModelAttribute("purchaseRequest") PurchaseRequest purchaseRequest,
			HttpServletRequest request) {
		// Extract PurchaseMaster and PurchaseDetails from purchaseRequest
		PurchaseMaster purchaseMaster = purchaseRequest.getPurchaseMaster();
		Date currentDate = new Date();
		purchaseMaster.setCreatedAt(currentDate);
		purchaseMaster.setUpdatedAt(currentDate);
		System.out.println(purchaseMaster);

		PurchaseMaster pm = pms.addNewPurchaseMaster(purchaseMaster);
		if(pm == null) throw new RuntimeException("Invoice Number Already Existing !!!!");

		List<PurchaseDetails> pdList = purchaseRequest.getPurchaseDetailsList();
		System.out.println(pdList.size());

		Iterator<PurchaseDetails> iterator = pdList.iterator();
		while (iterator.hasNext()) {
			Date date = new Date();
			PurchaseDetails pd = iterator.next();
			pd.setCreatedAt(date);
			pd.setUpdatedAt(date);
			pd.setPurchaseMasterId(pm.getId());

			pds.addNewPurchaseDetails(pd);
			System.out.println(pd);

		}
		System.out.println("Records Added Successfully.");

		return "redirect:/dashboard";
	}

	@PostMapping("/dashboard/saveSale")
	public String saveSales(@ModelAttribute("saleRequest") SaleRequest saleRequest) {

		SaleMaster saleMaster = saleRequest.getSaleMaster();
		Date currentDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFormat.format(currentDate);
		saleMaster.setBillDate(dateString);
		saleMaster.setCreatedAt(currentDate);
		saleMaster.setUpdatedAt(currentDate);
		saleMaster.setBillNo(generateRandomBillNumber());
		System.out.println(saleMaster);
		
		SaleMaster sm = sms.addNewSaleMaster(saleMaster);  // Adding SaleMaster Record
		
		List<SaleDetails> saleList = saleRequest.getSaleDetailsList();
		System.out.println("Size : "+saleList.size());
		
		Iterator<SaleDetails> itr = saleList.iterator();
		while(itr.hasNext()) {
			SaleDetails sd = itr.next();
			sd.setSaleMasterId(sm.getId());
			sd.setCreatedAt(currentDate);
			sd.setUpdatedAt(currentDate);
			SaleDetails addSD = sds.addNewSaleDetails(sd);
			System.out.println(addSD);
		}
		return "redirect:/dashboard";
	}

	/**
	 * Generates a random number between 10000000 and 99999999
	 * @return
	 */
	private String generateRandomBillNumber() {
		Random random = new Random();
	    int randomNumber = random.nextInt(90000000) + 10000000;
	    return String.valueOf(randomNumber);
	}
	
	/**
	 * Check if the invoice number exists in the database
	 * @param invoiceNo
	 * @return
	 */
    @GetMapping("/dashboard/checkInvoiceNo")
    public boolean checkInvoiceNo(@RequestParam("invoiceNo") Long invoiceNo) {
        PurchaseMaster pm = pms.getPurchaseMasterByInvoiceNo(invoiceNo);
        if(pm!=null) {
        	return true;
        }else {
        	return false;
        }
    }
    
	// =================================================== Supplier Controller =================================================================
	
	@PostMapping("/dashboard/add-supplier")
    public String saveEmployee(@RequestParam("name") String name,
                               @RequestParam("contact") Long contact,
                               @RequestParam("address") String address,
                               RedirectAttributes redirectAttributes) {
        Supplier sup = new Supplier();
        sup.setName(name);
        sup.setContact(contact);
        sup.setAddress(address);
        Date currentDate = new Date();
        sup.setCreatedAt(currentDate); 
        sup.setUpdatedAt(currentDate);
        
        
        Supplier existingSupplier = supService.getSupplierByContact(contact);
        if (existingSupplier != null) {
            // Supplier with the same contact already exists
            redirectAttributes.addFlashAttribute("errorMessage", "Contact number already exists.");
            return "redirect:/dashboard/supplier";
        } else {
            supService.addNewSupplier(sup);
            return "redirect:/dashboard/supplier";
        }
    }
	
	@GetMapping("/dashboard/api/suppliers/delete")
	public String deleteSuppliers(@RequestParam Long id) {
		
		System.out.println(id);
		supService.deleteSupplierById(id);
		return "redirect:/dashboard/supplier";
	}
	
	@GetMapping("/dashboard/api/suppliers/update")
	public String updateSupplierForm(@RequestParam Long id,Model model) {
		
		System.out.println(id);
		
		Supplier sup = supService.getSupplierById(id);
	    model.addAttribute("supplier", sup);

		return "update-supplier";
	}
	
	@PostMapping("/api/suppliers/update")
	public String updateSupplier(@RequestParam("id")Long id,
								 @RequestParam("name")String name,
								 @RequestParam("contact")Long contact,
								 @RequestParam("address")String address,
								 RedirectAttributes redirectAttributes){
		
		Supplier sup = new Supplier(); 
		sup.setId(id);
		sup.setName(name);
		sup.setAddress(address);
		sup.setContact(contact);
		sup.setUpdatedAt(new Date());
		
		Supplier existingSupplier = supService.getSupplierByContact(contact);
		System.out.println(existingSupplier);
		
        if (existingSupplier != null && existingSupplier.getId() != id) {
//        	System.out.println("OK1");
//        	System.out.println(8/0);
            // Supplier with the same contact already exists
            redirectAttributes.addFlashAttribute("errorMessage", "Contact number already exists.");
            return "redirect:/dashboard/api/suppliers/update?id=" + id;
        } else {
//        	System.out.println("OK2");
//        	System.out.println(9/0);
        	Supplier supplier = supService.updateSupplierById(sup);
    		System.out.println(supplier );
    		return "redirect:/dashboard/supplier";
		
        }
	}



}
