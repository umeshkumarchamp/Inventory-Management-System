package com.inventory.services;

import java.util.List;

import com.inventory.models.PurchaseDetails;
import com.inventory.models.PurchaseMaster;

public interface PurchaseDetailService {

	public PurchaseDetails addNewPurchaseDetails(PurchaseDetails pd);

	public void savePurchase(PurchaseMaster purchaseMaster, List<PurchaseDetails> purchaseDetailsList);

	public List<PurchaseDetails> getPurchaseList();

	public List<PurchaseDetails> getPurchaseDetailsByPurchaseMasterId(Long purchaseMasterId);
	
	public List<PurchaseDetails> getPurchaseDetailsByItemId(Long itemId);

}
