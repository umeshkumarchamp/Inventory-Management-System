package com.inventory.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.inventory.models.Item;
import com.inventory.services.ItemService;

@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService; 
	
	@PostMapping("/dashboard/add-item")
	public String addItem(@RequestParam("item_name") String name){
		Item item = new Item(); 
		item.setItem(name); 
		Date currentDate = new Date(); // Current date and time
	    item.setCreatedAt(currentDate); // Set createdAt to current date and time
	    item.setUpdatedAt(currentDate); // Set updatedAt to current date and time
	    itemService.addItem(item); 
		return "redirect:/dashboard/item"; 
	}
	
	
}
