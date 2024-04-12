package com.inventory.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.inventory.models.Item;
import com.inventory.models.Supplier;
import com.inventory.services.ItemService;

import jakarta.validation.constraints.NotBlank;

@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;

	@PostMapping("/dashboard/add-item")
	public String addItem(@RequestParam("item_name") @NotBlank(message = "Item name is required") String name,
			RedirectAttributes redirectAttributes) {

		// Validate if the item name is not blank
		if (name == null || name.trim().isEmpty()) {
			redirectAttributes.addFlashAttribute("errorMessage", "Item name cannot be blank");
			return "redirect:/dashboard/item";
		}

		Item item = new Item();
		item.setItem(name);
		Date currentDate = new Date(); // Current date and time
		item.setCreatedAt(currentDate); // Set createdAt to current date and time
		item.setUpdatedAt(currentDate); // Set updatedAt to current date and time

		Item checkExisting = itemService.getItemByName(name);
		if (checkExisting != null) {
			// Supplier with the same contact already exists
			redirectAttributes.addFlashAttribute("errorMessage", "Item Name Already Exists !!!");
			return "redirect:/dashboard/item";
		} else {
			itemService.addItem(item);
			return "redirect:/dashboard/item";
		}
	}

	@GetMapping("/dashboard/api/item/delete")
	public String deleteItem(@RequestParam Long id) {

		System.out.println(id);
		itemService.deleteItemById(id);
		return "redirect:/dashboard/item";
	}

	@GetMapping("/dashboard/api/item/update")
	public String showUpdateItemForm(@RequestParam Long id, Model model) {

		Item item = itemService.getByItemId(id);
		model.addAttribute("item", item);
		return "update-item";
	}

	@SuppressWarnings("null")
	@PostMapping("/api/item/update")
	public String updateSupplier(@RequestParam("id") Long id, @RequestParam("item") String itemName,
			RedirectAttributes redirectAttributes) {

		Item item = new Item();
		item.setId(id);
		item.setItem(itemName);
		item.setUpdatedAt(new Date());

		Item checkExisting = itemService.getItemByName(itemName);
		if (checkExisting != null && !checkExisting.getId().equals(id)) {
			// Supplier with the same contact already exists
			redirectAttributes.addFlashAttribute("errorMessage", "Item Name Already Exists !!!");
			return "redirect:/dashboard/api/item/update?id=" + id;

		} else {
			Item updatedItem = itemService.updateItemById(item);
			System.out.println(updatedItem);
			return "redirect:/dashboard/item";

		}

	}

}
