package com.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.models.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long>{

	public Supplier findSupplierByContact(Long contact);
}
