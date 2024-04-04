package com.inventory.models;

import java.util.List;

public class PurchaseRequest {
	
	private PurchaseMaster purchaseMaster;
    private List<PurchaseDetails> purchaseDetailsList;
    

	public PurchaseMaster getPurchaseMaster() {
		return purchaseMaster;
	}
	public void setPurchaseMaster(PurchaseMaster purchaseMaster) {
		this.purchaseMaster = purchaseMaster;
	}
	public List<PurchaseDetails> getPurchaseDetailsList() {
		return purchaseDetailsList;
	}
	public void setPurchaseDetailsList(List<PurchaseDetails> purchaseDetailsList) {
		this.purchaseDetailsList = purchaseDetailsList;
	}

    
}
