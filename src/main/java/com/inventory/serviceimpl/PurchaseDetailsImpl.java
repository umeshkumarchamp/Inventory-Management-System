package com.inventory.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.models.PurchaseDetails;
import com.inventory.models.PurchaseMaster;
import com.inventory.repository.PurchaseDetailRepository;
import com.inventory.repository.PurchaseMasterRepository;
import com.inventory.services.PurchaseDetailService;

@Service
public class PurchaseDetailsImpl implements PurchaseDetailService {
	
	@Autowired
	private PurchaseDetailRepository pdRepo;
	
	@Autowired
	private PurchaseMasterRepository pmRepo;

	

	@Override
	public void savePurchase(PurchaseMaster purchaseMaster, List<PurchaseDetails> purchaseDetailsList) {

		// Save PurchaseMaster
        PurchaseMaster savedPurchaseMaster = pmRepo.save(purchaseMaster);

        // Associate PurchaseDetails with the saved PurchaseMaster and save each PurchaseDetails
//        for (PurchaseDetails purchaseDetails : purchaseDetailsList) {
//            purchaseDetails.setPurchaseMasterId(savedPurchaseMaster);
//            pdRepo.save(purchaseDetails);
//        }
	}



	@Override
	public PurchaseDetails addNewPurchaseDetails(PurchaseDetails pd) {
		return pdRepo.save(pd);
	}
	

	@Override
	public List<PurchaseDetails> getPurchaseList() {
		return pdRepo.findAll();
	}



	@Override
    public List<PurchaseDetails> getPurchaseDetailsByPurchaseMasterId(Long purchaseMasterId) {
        return pdRepo.findAllByPurchaseMasterId(purchaseMasterId);
    }



	@Override
	public List<PurchaseDetails> getPurchaseDetailsByItemId(Long itemId) {
		return pdRepo.findAllByItem(itemId);
	}

}
