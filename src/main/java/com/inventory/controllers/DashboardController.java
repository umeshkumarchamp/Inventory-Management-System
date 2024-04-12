package com.inventory.controllers;

import java.util.Date;
import java.util.List;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.inventory.models.Item;
import com.inventory.models.PurchaseDetails;
import com.inventory.models.SaleDetails;
import com.inventory.models.Supplier;
import com.inventory.services.ItemService;
import com.inventory.services.PurchaseDetailService;
import com.inventory.services.SaleDetailService;
import com.inventory.services.SupplierService;

@Controller
public class DashboardController {

	@Autowired
	private SupplierService supService;

	@Autowired
	private ItemService itemService;

	@Autowired
	private PurchaseDetailService pds;

	@Autowired
	private SaleDetailService sds;

	@GetMapping("/dashboard")
	public String getDashboard(Model model) {
		List<PurchaseDetails> plist = pds.getPurchaseList();
		List<SaleDetails> slist = sds.getSaleList();
		model.addAttribute("purchase_report", plist.size());
		model.addAttribute("sale_report", slist.size());
		return "dashboard";
	}

	@GetMapping("/dashboard/item")
	public String getItem() {
		return "itemaster";
	}

	@GetMapping("/dashboard/supplier")
	public String getSupplier() {
		return "supplier";
	}

	@GetMapping("/dashboard/purchase")
	public String getPurchase(Model model) {
		List<Supplier> suppliers = supService.getAllSupplier();
		List<Item> items = itemService.getAllItems();
		
		model.addAttribute("suppliers", suppliers);
		model.addAttribute("items", items);
		return "purchase";
	}

	@GetMapping("/dashboard/sale")
	public String getSales(Model model) {
		List<Item> items = itemService.getAllItems();
		model.addAttribute("items", items);
		return "sales";
	}

	@GetMapping("/dashboard/reports")
	public String getReports(Model model) {

		List<PurchaseDetails> plist = pds.getPurchaseList();
		List<SaleDetails> slist = sds.getSaleList();
		model.addAttribute("purchase_report", plist.size());
		model.addAttribute("sale_report", slist.size());
		return "reports";
	}

	@GetMapping("/dashboard/setting")
	public String getSettings() {

		return "settings";
	}

	@GetMapping("/dashboard/profile")
	public String getProfile() {
		return "profile";
	}

}
