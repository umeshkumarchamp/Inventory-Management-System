package com.inventory.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.inventory.formatter.SaleReportFormatter;
import com.inventory.models.Item;
import com.inventory.models.PurchaseDetailFormater;
import com.inventory.models.PurchaseDetails;
import com.inventory.models.PurchaseMaster;
import com.inventory.models.SaleDetails;
import com.inventory.models.SaleMaster;
import com.inventory.models.Supplier;
import com.inventory.services.ItemService;
import com.inventory.services.SaleDetailService;
import com.inventory.services.SaleMasterService;

@Controller
public class SaleReportController {
	
	@Autowired
	private SaleMasterService sms; 
	
	@Autowired
	private SaleDetailService sds; 
	
	@Autowired
	private ItemService itemService;

	@GetMapping("/dashboard/datewise-sale-reports")
	public String getSaleReport(Model model) {
		List<SaleReportFormatter> spfList = new ArrayList<>(); 

		List<SaleMaster> saleMasterList = sms.getAllSaleMasterList();
		
		Iterator<SaleMaster> itr = saleMasterList.iterator(); 
		while(itr.hasNext()) {
			SaleMaster sm = itr.next();
			SaleReportFormatter spf = new SaleReportFormatter(); 
			spf.setBillDate(sm.getBillDate());
			spf.setBillNo(sm.getBillNo());
			spf.setCustomer(sm.getCustomerName());
			
			Double amount = 0D;
			List<SaleDetails> sdList = sds.getSaleDetailsBySaleMasterId(sm.getId());
			Iterator<SaleDetails> i = sdList.iterator(); 
			while(i.hasNext()) {
				SaleDetails sd = i.next(); 
				amount = amount + (sd.getRate() * sd.getQuantity());
			}
			spf.setAmount(amount);
			spfList.add(spf);
			
		}
		
		model.addAttribute("saleReports",spfList);
		return "salereport";
	}
	
	@GetMapping("/dashboard/datewise-sale-reports/viewDetails")
	public String showSaleReportDetails(@RequestParam("billNo") String billNo, Model model) {

	    SaleMaster sm = sms.getSaleMasterByBillNo(billNo);
	    model.addAttribute("billNo", sm.getBillNo());
	    model.addAttribute("billDate",sm.getBillDate());
		model.addAttribute("customer",sm.getCustomerName());
	    
		List<SaleDetails> sdList = sds.getSaleDetailsBySaleMasterId(sm.getId());
		Double amount = 0D;
		Iterator<SaleDetails> i = sdList.iterator(); 
		List<PurchaseDetailFormater> items = new ArrayList<>(); 
		while(i.hasNext()) {
			SaleDetails sd = i.next(); 
			PurchaseDetailFormater pdf = new PurchaseDetailFormater();
			Item item = itemService.getByItemId(sd.getItem());

			pdf.setItem(item.getItem());
			pdf.setQuantity(sd.getQuantity());
			pdf.setRate(sd.getRate());
			
			Double rate = sd.getRate() * sd.getQuantity();
			pdf.setAmount(rate);
			items.add(pdf);
			amount = amount + (sd.getRate() * sd.getQuantity());
		}
		
	    model.addAttribute("items", items);
	    model.addAttribute("totalAmount",amount);
		return "salereportdetails";
	}
	
}
