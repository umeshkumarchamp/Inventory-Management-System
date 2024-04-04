package com.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.models.SaleMaster;

public interface SaleMasterRepository extends JpaRepository<SaleMaster, Long> {

}
