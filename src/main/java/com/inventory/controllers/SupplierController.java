package com.inventory.controllers;

import java.util.Date;
import java.util.List;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.models.Supplier;
import com.inventory.services.SupplierService;


@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
    
    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public List<Supplier> getAllSuppliers() {
        return supplierService.getAllSupplier();
    }
    
    @DeleteMapping("/delete2/{id}") // Use @PathVariable instead of @RequestParam
    public ResponseEntity<String> deleteSupplier(@PathVariable Long id){
        try {
            
        	Supplier sup = supplierService.getSupplierById(id);
            System.out.println(sup);
            System.out.println(9/0);
            return ResponseEntity.ok("Supplier deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete supplier: " + e.getMessage());
        }
    }
}
