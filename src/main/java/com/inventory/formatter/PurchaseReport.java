package com.inventory.formatter;

import java.util.Date;

public class PurchaseReport {

	private Long invoiceNo; 
	private String invoiceDate; 
	private String supplier; 
	private Double amount;
	public Long getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(Long invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	} 
	
	
}
