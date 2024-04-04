package com.inventory.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.models.PurchaseMaster;
import com.inventory.repository.PurchaseMasterRepository;
import com.inventory.services.PurchaseMasterService;

@Service
public class PurchaseMasterImpl implements PurchaseMasterService {
	
	@Autowired
	private PurchaseMasterRepository pmRepo;

	@Override
	public PurchaseMaster addNewPurchaseMaster(PurchaseMaster pm) {
		return pmRepo.save(pm);
	}

	@Override
	public List<PurchaseMaster> getAllPurchaseMaster() {
		return pmRepo.findAll();
	}

	@Override
	public PurchaseMaster getPurchaseMasterByInvoiceNo(Long invoiceNo) {
		// TODO Auto-generated method stub
		return pmRepo.findByInvoiceNo(invoiceNo);
	}

}
