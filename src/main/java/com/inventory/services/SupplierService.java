package com.inventory.services;

import java.util.List;

import com.inventory.models.Supplier;

public interface SupplierService {

	public Supplier addNewSupplier(Supplier sup);
	
	public List<Supplier> getAllSupplier();
	
	public Supplier getSupplierById(Long id);
}
