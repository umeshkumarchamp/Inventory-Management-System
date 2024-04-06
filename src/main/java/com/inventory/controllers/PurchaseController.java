package com.inventory.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.formatter.PurchaseReport;
import com.inventory.formatter.SaleReportFormatter;
import com.inventory.models.PurchaseDetails;
import com.inventory.models.PurchaseMaster;
import com.inventory.models.SaleDetails;
import com.inventory.models.SaleMaster;
import com.inventory.models.Supplier;
import com.inventory.services.PurchaseDetailService;
import com.inventory.services.PurchaseMasterService;
import com.inventory.services.SaleDetailService;
import com.inventory.services.SaleMasterService;
import com.inventory.services.SupplierService;

@RestController
public class PurchaseController {
	
	@Autowired
	private PurchaseMasterService pms;
	
	@Autowired
	private PurchaseDetailService pds; 
	
	@Autowired
	private SaleMasterService sms; 
	
	@Autowired
	private SaleDetailService sds;
	
	@Autowired
	private SupplierService supplierService;

	@GetMapping("/dashboard/purchaseReports")
	public List<PurchaseReport> getPurchaseReportsByDate(@RequestParam("fromDate") String fromDate,
														 @RequestParam("toDate") String toDate) {
		System.out.println(fromDate+ " to "+toDate);
		List<PurchaseReport> prList = new ArrayList<>();
		List<PurchaseMaster> pmList = pms.findPurchasesBetweenDates(fromDate, toDate);
		
		System.out.println(pmList.size());
//		System.out.println(3/0);
		Iterator<PurchaseMaster> itr = pmList.iterator(); 
		while (itr.hasNext()) {
			PurchaseMaster pm = itr.next();
			PurchaseReport pr = new PurchaseReport();
			pr.setInvoiceDate(pm.getDate());
			pr.setInvoiceNo(pm.getInvoiceNo());
			Supplier supplier = supplierService.getSupplierById(pm.getSupplierId());
			if(supplier == null) {
				continue;
			}
			pr.setSupplier(supplier.getName());

			List<PurchaseDetails> pdList = pds.getPurchaseDetailsByPurchaseMasterId(pm.getId());
			Double amount = 0D;
			Iterator<PurchaseDetails> i = pdList.iterator();
			while (i.hasNext()) {
				PurchaseDetails pd = i.next();
				amount = amount + (pd.getRate() * pd.getQuantity());
			}
			pr.setAmount(amount);
			prList.add(pr);
		}

		return prList;
		
	}
	
	@GetMapping("/dashboard/saleReports")
	public List<SaleReportFormatter> getSaleReportsByDate(@RequestParam("fromDate") String fromDate,
														  @RequestParam("toDate") String toDate) {
		System.out.println(fromDate + " to " + toDate);

		List<SaleReportFormatter> prList = new ArrayList<>();
//		List<SaleMaster> pmList = sms.getSaleMastersByBillDate(fromDate);
		List<SaleMaster> pmList = sms.findSaleMasterBetweenDates(fromDate, toDate);
		
		System.out.println(pmList.size());
//		System.out.println(3/0);

		Iterator<SaleMaster> itr = pmList.iterator(); 
		while(itr.hasNext()) {
			SaleMaster sm = itr.next();
			SaleReportFormatter spf = new SaleReportFormatter(); 
			spf.setBillDate(sm.getBillDate());
			spf.setBillNo(sm.getBillNo());
			spf.setCustomer(sm.getCustomerName());
			
			Double amount = 0D;
			List<SaleDetails> sdList = sds.getSaleDetailsBySaleMasterId(sm.getId());
			Iterator<SaleDetails> i = sdList.iterator(); 
			while(i.hasNext()) {
				SaleDetails sd = i.next(); 
				amount = amount + (sd.getRate() * sd.getQuantity());
			}
			spf.setAmount(amount);
			prList.add(spf);
			
		}

		return prList;
		
	}
}
