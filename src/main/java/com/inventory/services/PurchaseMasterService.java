package com.inventory.services;

import java.util.List;

import com.inventory.models.PurchaseMaster;

public interface PurchaseMasterService {

	public PurchaseMaster addNewPurchaseMaster(PurchaseMaster pm);
	
	public List<PurchaseMaster> getAllPurchaseMaster();
	
	public PurchaseMaster getPurchaseMasterByInvoiceNo(Long invoiceNo);
	
	public List<PurchaseMaster> getPurchaseMastersByDate(String data);
	
	public List<PurchaseMaster> findPurchasesBetweenDates(String fromDate , String toDate);
	
}
