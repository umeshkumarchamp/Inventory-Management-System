package com.inventory.services;

import java.util.List;

import com.inventory.models.Supplier;

public interface SupplierService {

	public Supplier addNewSupplier(Supplier sup);
	
	public List<Supplier> getAllSupplier();
	
	public Supplier getSupplierById(Long id);
	
	public Boolean deleteSupplierById(Long id);
	
	public Supplier updateSupplierById(Supplier sup);
	
	public Supplier getSupplierByContact(Long contactNo);
}
