package com.inventory.services;

import java.util.List;

import com.inventory.models.SaleMaster;

public interface SaleMasterService {
	
	public SaleMaster addNewSaleMaster(SaleMaster sm);
	
	public List<SaleMaster> getAllSaleMasterList();

	public SaleMaster getSaleMasterByBillNo(String billNo);

	public List<SaleMaster> getSaleMastersByBillDate(String billDate);
	
	public List<SaleMaster> findSaleMasterBetweenDates(String fromDate, String toDate);
	
}
