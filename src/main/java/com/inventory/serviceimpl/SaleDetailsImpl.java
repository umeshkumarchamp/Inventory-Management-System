package com.inventory.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.models.SaleDetails;
import com.inventory.repository.SaleDetailsRepository;
import com.inventory.services.SaleDetailService;

@Service
public class SaleDetailsImpl implements SaleDetailService{
	
	@Autowired
	private SaleDetailsRepository sdr; 

	@Override
	public SaleDetails addNewSaleDetails(SaleDetails sd) {
		return sdr.save(sd);	
	}

	@Override
	public List<SaleDetails> getSaleList() {
		return sdr.findAll();
	}

	@Override
	public List<SaleDetails> getSaleDetailsBySaleMasterId(Long id) {
		return sdr.findAllBySaleMasterId(id);
	}

	@Override
	public List<SaleDetails> getSaleDetailsByItemId(Long itemId) {
		return sdr.findAllByItem(itemId);
	}

}
