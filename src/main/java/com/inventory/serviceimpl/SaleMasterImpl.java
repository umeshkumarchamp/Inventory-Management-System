package com.inventory.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.models.SaleMaster;
import com.inventory.repository.SaleMasterRepository;
import com.inventory.services.SaleMasterService;

@Service
public class SaleMasterImpl implements SaleMasterService {
	
	@Autowired
	private SaleMasterRepository smp;

	@Override
	public SaleMaster addNewSaleMaster(SaleMaster sm) {
		return smp.save(sm);
	}

	@Override
	public List<SaleMaster> getAllSaleMasterList() {
		
		return smp.findAll();
	}

	@Override
	public SaleMaster getSaleMasterByBillNo(String billNo) {
		// TODO Auto-generated method stub
		return smp.findSaleMasterDetailsByBillNo(billNo);
	}

	@Override
	public List<SaleMaster> getSaleMastersByBillDate(String billDate) {
		// TODO Auto-generated method stub
		return smp.findSaleMasterDetailsByBillDate(billDate);
	}

	@Override
	public List<SaleMaster> findSaleMasterBetweenDates(String fromDate, String toDate) {
		// TODO Auto-generated method stub
		return smp.findByBillDateBetween(fromDate, toDate);
	}

}
