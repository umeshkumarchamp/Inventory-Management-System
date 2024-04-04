package com.inventory.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.models.Supplier;
import com.inventory.repository.SupplierRepository;
import com.inventory.services.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService{
	
	@Autowired
	private SupplierRepository supRepo;

	@Override
	public Supplier addNewSupplier(Supplier sup) {
		return supRepo.save(sup);
	}

	@Override
	public List<Supplier> getAllSupplier() {
		List<Supplier> supplierList = supRepo.findAll(); 
		return supplierList;
	}

	@Override
	public Supplier getSupplierById(Long id) {
		// TODO Auto-generated method stub
		Supplier supplier = null;
		Optional<Supplier> op =  supRepo.findById(id);
		if(op.isPresent()) {
			supplier = op.get();
			return supplier;
		}
		return supplier;
	}

}
