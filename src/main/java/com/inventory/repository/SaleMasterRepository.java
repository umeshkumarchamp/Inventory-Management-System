package com.inventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.models.PurchaseMaster;
import com.inventory.models.SaleMaster;

public interface SaleMasterRepository extends JpaRepository<SaleMaster, Long> {

	SaleMaster findSaleMasterDetailsByBillNo(String billNo);
	
	List<SaleMaster> findSaleMasterDetailsByBillDate(String billDate);
	
    List<SaleMaster> findByBillDateBetween(String fromDate, String toDate);

}
