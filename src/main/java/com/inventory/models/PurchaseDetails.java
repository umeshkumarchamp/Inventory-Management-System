package com.inventory.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "purchase_details")
public class PurchaseDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	private Integer quantity; 
	private Double rate;
	
	@Column(name = "purchase_master_id")
	private Long purchaseMasterId;
	
	@Column(name = "item")
	private Long item;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false, updatable = false)
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", nullable = false)
	private Date updatedAt;

	public PurchaseDetails(Long id, Integer quantity, Double rate, Long purchaseMasterId, Long item, Date createdAt,
			Date updatedAt) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.rate = rate;
		this.purchaseMasterId = purchaseMasterId;
		this.item = item;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public PurchaseDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getPurchaseMasterId() {
		return purchaseMasterId;
	}

	public void setPurchaseMasterId(Long purchaseMasterId) {
		this.purchaseMasterId = purchaseMasterId;
	}

	public Long getItem() {
		return item;
	}

	public void setItem(Long item) {
		this.item = item;
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
		return "PurchaseDetails [id=" + id + ", quantity=" + quantity + ", rate=" + rate + ", purchaseMasterId="
				+ purchaseMasterId + ", item=" + item + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}


	
	
}
