package com.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.models.SaleDetails;

public interface SaleDetailsRepository extends JpaRepository<SaleDetails, Long> {

}
