package com.inventory.serviceimpl;

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

}
