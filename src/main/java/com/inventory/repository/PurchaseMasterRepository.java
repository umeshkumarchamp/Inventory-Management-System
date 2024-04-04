package com.inventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.models.PurchaseMaster;

public interface PurchaseMasterRepository extends JpaRepository<PurchaseMaster, Long> {

    PurchaseMaster findByInvoiceNo(Long invoiceNo);

}
