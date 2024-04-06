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
		PurchaseMaster purchaseMaster = pmRepo.findByInvoiceNo(pm.getInvoiceNo());
		if(purchaseMaster != null) {
			return null;
		}
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

	@Override
	public List<PurchaseMaster> getPurchaseMastersByDate(String data) {
		// TODO Auto-generated method stub
		return pmRepo.findByDate(data);
	}
	
	@Override
	public List<PurchaseMaster> findPurchasesBetweenDates(String fromDate, String toDate) {
        return pmRepo.findByDateBetween(fromDate, toDate);
    }

}
