package com.inventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.models.PurchaseDetails;

public interface PurchaseDetailRepository extends JpaRepository<PurchaseDetails, Long> {

    List<PurchaseDetails> findAllByPurchaseMasterId(Long purchaseMasterId);
    
    List<PurchaseDetails> findAllByItem(Long item);
    
    
}
