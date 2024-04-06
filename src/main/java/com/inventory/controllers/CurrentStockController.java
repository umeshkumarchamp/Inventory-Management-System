package com.inventory.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.inventory.formatter.CurrentStockFormatter;
import com.inventory.models.Item;
import com.inventory.models.PurchaseDetails;
import com.inventory.models.SaleDetails;
import com.inventory.services.ItemService;
import com.inventory.services.PurchaseDetailService;
import com.inventory.services.SaleDetailService;

@Controller
public class CurrentStockController {

	@Autowired
	private ItemService itemService;
	
	@Autowired
	private PurchaseDetailService pds; 
	
	@Autowired
	private SaleDetailService sds;
	
	@GetMapping("/dashboard/current-stock")
	public String getCurrentStockReport(Model model) {
		List<CurrentStockFormatter> csfList = new ArrayList<>();
		
		List<Item> items = itemService.getAllItems();
		
		Iterator<Item> itr = items.iterator();
		while(itr.hasNext()) {
			Item item = itr.next();
			
			CurrentStockFormatter csf = new CurrentStockFormatter(); 
			
			csf.setItemName(item.getItem());
			
			Integer purchaseQty = 0;
			Integer saleQty = 0;
			Integer availableQty = 0;
			
			List<PurchaseDetails> pdList = pds.getPurchaseDetailsByItemId(item.getId());
			Iterator<PurchaseDetails> i = pdList.iterator(); 
			while(i.hasNext()) {
				PurchaseDetails pd = i.next(); 
				purchaseQty = purchaseQty + pd.getQuantity();
			}
			
			List<SaleDetails> sdList = sds.getSaleDetailsByItemId(item.getId());
			Iterator<SaleDetails> i2 = sdList.iterator(); 
			while(i2.hasNext()) {
				SaleDetails sd = i2.next(); 
				saleQty = saleQty + sd.getQuantity();
			}
			availableQty = purchaseQty - saleQty;
			csf.setPurchaseQuantity(purchaseQty);
			csf.setSaleQuantity(saleQty);
			csf.setAvailableQty(availableQty);
			csfList.add(csf);
			
		}		
		
		model.addAttribute("currentStock",csfList);
		return "currentstock";
	}
}
