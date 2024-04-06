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
@Table(name = "sale_masters")
public class SaleMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@Column(name = "customer_name")
	private String customerName; 
	
	@Column(name = "contact_no")
	private Long contactNo; 
	
	@Column(name = "bill_no", unique = true)
	private String billNo;
	
	@Column(name = "bill_date")
	private String billDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false, updatable = false)
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", nullable = false)
	private Date updatedAt;

	public SaleMaster(Long id, String customerName, Long contactNo, String billNo, String billDate, Date createdAt,
			Date updatedAt) {
		super();
		this.id = id;
		this.customerName = customerName;
		this.contactNo = contactNo;
		this.billNo = billNo;
		this.billDate = billDate;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public SaleMaster() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Long getContactNo() {
		return contactNo;
	}

	public void setContactNo(Long contactNo) {
		this.contactNo = contactNo;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String string) {
		this.billNo = string;
	}

	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
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
		return "SaleMaster [id=" + id + ", customerName=" + customerName + ", contactNo=" + contactNo + ", billNo="
				+ billNo + ", billDate=" + billDate + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
	
	
	
	
}
