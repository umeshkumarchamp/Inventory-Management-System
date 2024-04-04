package com.inventory.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.models.PurchaseDetails;
import com.inventory.models.PurchaseMaster;
import com.inventory.models.PurchaseRequest;
import com.inventory.models.Supplier;
import com.inventory.services.PurchaseDetailService;
import com.inventory.services.PurchaseMasterService;
import com.inventory.services.SupplierService;

@RestController
public class TestController {

	@Autowired
	private SupplierService supService;
	

	@Autowired
	private PurchaseDetailService pdService; 
	
	@GetMapping("/supplier")
	public List<Supplier> test() {
		List<Supplier> list = supService.getAllSupplier();
		
		return list;
	}
	
	
	
	@PostMapping("/purchase")
	public PurchaseMaster addPurchase(@RequestBody PurchaseRequest purchaseRequest) {
	    PurchaseMaster pm = purchaseRequest.getPurchaseMaster();
	    Date date = new Date();
	    
	    List<PurchaseDetails> pdList = purchaseRequest.getPurchaseDetailsList();
	    
	    // Save purchase details to the database
	    pdService.savePurchase(pm, pdList);
	    return pm;
	}
	
	
	
}
