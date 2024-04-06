package com.inventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.models.SaleDetails;

public interface SaleDetailsRepository extends JpaRepository<SaleDetails, Long> {

	List<SaleDetails> findAllBySaleMasterId(Long saleMasterId);
	
	List<SaleDetails> findAllByItem(Long item);
	
}
