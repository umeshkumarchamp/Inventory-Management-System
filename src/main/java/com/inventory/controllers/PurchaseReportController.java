package com.inventory.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.inventory.formatter.PurchaseReport;
import com.inventory.models.Item;
import com.inventory.models.PurchaseDetailFormater;
import com.inventory.models.PurchaseDetails;
import com.inventory.models.PurchaseMaster;
import com.inventory.models.Supplier;
import com.inventory.services.ItemService;
import com.inventory.services.PurchaseDetailService;
import com.inventory.services.PurchaseMasterService;
import com.inventory.services.SupplierService;


@Controller
public class PurchaseReportController {

	@Autowired
	private PurchaseMasterService pms;

	@Autowired
	private PurchaseDetailService pds;

	@Autowired
	private SupplierService supplierService;

	@Autowired
	private ItemService itemService;

	@GetMapping("/dashboard/datewise-purchase-reports2")
	public String getPurchaseReport1(Model model) {

		List<PurchaseReport> prList = new ArrayList<>();

		List<PurchaseMaster> pmList = pms.getAllPurchaseMaster();

		Iterator<PurchaseMaster> itr = pmList.iterator();
		while (itr.hasNext()) {
			PurchaseMaster pm = itr.next();
			PurchaseReport pr = new PurchaseReport();
			pr.setInvoiceDate(pm.getDate());
			pr.setInvoiceNo(pm.getInvoiceNo());
			Supplier supplier = supplierService.getSupplierById(pm.getSupplierId());
			pr.setSupplier(supplier.getName());

			List<PurchaseDetails> pdList = pds.getPurchaseDetailsByPurchaseMasterId(pm.getId());
			Double amount = 0D;
			Iterator<PurchaseDetails> i = pdList.iterator();
			while (i.hasNext()) {
				PurchaseDetails pd = i.next();
				amount = amount + (pd.getRate() * pd.getQuantity());
			}
			pr.setAmount(amount);
			prList.add(pr);
		}
		model.addAttribute("purchaseReports", prList);
		return "purchasereport";
	}

	@GetMapping("/dashboard/datewise-purchase-reports")
	public String getPurchaseReport(Model model) {

		return "purchasereport";
	}

	@GetMapping("/dashboard/datewise-purchase-reports/viewDetails")
	public String showPurchaseDetails(@RequestParam("invoiceNo") Long invoiceNo, Model model) {

		PurchaseMaster pm = pms.getPurchaseMasterByInvoiceNo(invoiceNo);
		model.addAttribute("invoiceNo", pm.getInvoiceNo());
		model.addAttribute("invoiceDate", pm.getDate());
		Supplier supplier = supplierService.getSupplierById(pm.getSupplierId());
		model.addAttribute("supplier", supplier.getName());

		List<PurchaseDetails> pdList = pds.getPurchaseDetailsByPurchaseMasterId(pm.getId());
		Double amount = 0D;
		Iterator<PurchaseDetails> i = pdList.iterator();
		List<PurchaseDetailFormater> items = new ArrayList<>();
		while (i.hasNext()) {
			PurchaseDetails pd = i.next();
			PurchaseDetailFormater pdf = new PurchaseDetailFormater();
			Item item = itemService.getByItemId(pd.getItem());

			pdf.setItem(item.getItem());
			pdf.setQuantity(pd.getQuantity());
			pdf.setRate(pd.getRate());

			Double rate = pd.getRate() * pd.getQuantity();
			pdf.setAmount(rate);
			items.add(pdf);

			amount = amount + (pd.getRate() * pd.getQuantity());

		}

		model.addAttribute("items", items);
		model.addAttribute("totalAmount", amount);
		return "purchasedetails";
	}

}
