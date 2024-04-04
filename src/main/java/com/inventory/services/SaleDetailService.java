package com.inventory.services;

import java.util.List;

import com.inventory.models.SaleDetails;

public interface SaleDetailService {
	
	public SaleDetails addNewSaleDetails(SaleDetails sd);
	
	public List<SaleDetails> getSaleList();

}
