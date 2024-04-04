package com.inventory.models;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

public class PurchaseDetailFormater {

	private String item;
	private Double rate;
	private Integer quantity;
	private Double amount;
	public PurchaseDetailFormater(String item, Double rate, Integer quantity, Double amount) {
		super();
		this.item = item;
		this.rate = rate;
		this.quantity = quantity;
		this.amount = amount;
	}
	public PurchaseDetailFormater() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "PurchaseDetailFormater [item=" + item + ", rate=" + rate + ", quantity=" + quantity + ", amount="
				+ amount + "]";
	}
	
	
	
}
