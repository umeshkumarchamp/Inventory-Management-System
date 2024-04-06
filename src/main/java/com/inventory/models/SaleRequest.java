package com.inventory.models;

import java.util.List;

public class SaleRequest {

	private SaleMaster saleMaster;
    private List<SaleDetails> saleDetailsList;
	public SaleMaster getSaleMaster() {
		return saleMaster;
	}
	
	public void setSaleMaster(SaleMaster saleMaster) {
		this.saleMaster = saleMaster;
	}
	public List<SaleDetails> getSaleDetailsList() {
		return saleDetailsList;
	}
	public void setSaleDetailsList(List<SaleDetails> saleDetailsList) {
		this.saleDetailsList = saleDetailsList;
	}
    
    
	
}
