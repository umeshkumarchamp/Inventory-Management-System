package com.inventory.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "sale_details")
public class SaleDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@Column(name = "sale_master_id")
	private Long saleMasterId; 
	
	@Column(name = "item")
	private Long item; 
	private Integer quantity; 
	private Double rate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false, updatable = false)
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", nullable = false)
	private Date updatedAt;

	public SaleDetails(Long id, Long saleMasterId, Long item, Integer quantity, Double rate, Date createdAt,
			Date updatedAt) {
		super();
		this.id = id;
		this.saleMasterId = saleMasterId;
		this.item = item;
		this.quantity = quantity;
		this.rate = rate;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public SaleDetails() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSaleMasterId() {
		return saleMasterId;
	}

	public void setSaleMasterId(Long saleMasterId) {
		this.saleMasterId = saleMasterId;
	}

	public Long getItem() {
		return item;
	}

	public void setItem(Long item) {
		this.item = item;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "SaleDetails [id=" + id + ", saleMasterId=" + saleMasterId + ", item=" + item + ", quantity=" + quantity
				+ ", rate=" + rate + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
	
	
	
	
	
	
}
