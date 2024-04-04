package com.inventory.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.inventory.models.Supplier;
import com.inventory.services.SupplierService;

@Controller
public class SupplierController {

	@Autowired
	private SupplierService supService;
	
	@PostMapping("/dashboard/add-supplier")
    public String saveEmployee(@RequestParam("name") String name,
                               @RequestParam("contact") Long contact,
                               @RequestParam("address") String address) {
        Supplier sup = new Supplier();
        sup.setName(name);
        sup.setContact(contact);
        sup.setAddress(address);
        Date currentDate = new Date();
        sup.setCreatedAt(currentDate); 
        sup.setUpdatedAt(currentDate);
        supService.addNewSupplier(sup);
        
        return "redirect:/dashboard/supplier"; 
    }
	
	
	
}
