package com.inventory.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.inventory.models.Item;
import com.inventory.models.PurchaseDetails;
import com.inventory.models.PurchaseMaster;
import com.inventory.models.Supplier;
import com.inventory.services.ItemService;
import com.inventory.services.PurchaseDetailService;
import com.inventory.services.PurchaseMasterService;
import com.inventory.services.SupplierService;

import jakarta.validation.Valid;

@RestController
public class MasterRestController {

	@Autowired
	private SupplierService supplierService;

	@Autowired
	private ItemService itemService;

	@Autowired
	private PurchaseDetailService pds;

	@Autowired
	private PurchaseMasterService pms;

	@GetMapping("/api/suppliers")
	public List<Supplier> getAllSuppliers() {
		return supplierService.getAllSupplier();
	}

	/**
	 * GET all items
	 * @return
	 */
	@GetMapping("/api/items")
	public List<Item> getAllItems() {
		return itemService.getAllItems();
	}

	@DeleteMapping("/delete2/{id}") // Use @PathVariable instead of @RequestParam
	public ResponseEntity<String> deleteSupplier(@PathVariable Long id) {
		try {
			Supplier sup = supplierService.getSupplierById(id);
			System.out.println(sup);
			System.out.println(9 / 0);
			return ResponseEntity.ok("Supplier deleted successfully.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to delete supplier: " + e.getMessage());
		}
	}

	/**
	 * Validate and Add Supplier Data to the database
	 * @param supplier
	 * @param bindingResult
	 * @return
	 */
	@PostMapping("/submit-supplier-form")
	public ResponseEntity<?> submitSupplierForm(@Valid @RequestBody Supplier supplier, BindingResult bindingResult) {
	    if (bindingResult.hasErrors()) {
	        List<String> errors = new ArrayList<>();
	        for (FieldError error : bindingResult.getFieldErrors()) {
	            errors.add(error.getDefaultMessage());
	        }
	        System.out.println(supplier);
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	    } else {
	    	supplier.setCreatedAt(new Date());
	    	supplier.setUpdatedAt(new Date());
	        Supplier savedSupplier = supplierService.addNewSupplier(supplier); 
	        
	        // Redirect to the supplier page
	        URI location = ServletUriComponentsBuilder
	                        .fromCurrentRequest()
	                        .replacePath("/dashboard/supplier") 
	                        .build()
	                        .toUri();
	        return ResponseEntity.created(location).build();
	    }
	}


	/**
	 * Get Rate according to the productId
	 * @param productId
	 * @return
	 */
	@GetMapping("/api/getRate")
	public ResponseEntity<List<PurchaseDetails>> getRate(@RequestParam Long productId) {
		List<PurchaseDetails> pdList = pds.getPurchaseDetailsByItemId(productId);

		if (pdList.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(pdList);
	}

	/**
	 * Check Invoice number is exists or not
	 * @param invoiceNo
	 * @return
	 */
	@GetMapping("/api/checkInvoiceNo")
	@ResponseBody
	public Map<String, Boolean> checkInvoiceNo(@RequestParam Long invoiceNo) {
		PurchaseMaster pm = pms.getPurchaseMasterByInvoiceNo(invoiceNo);
		Map<String, Boolean> response = new HashMap<>();
		if (pm != null) {
			response.put("exists", true);
			return response;
		}
		return response;
	}
}
