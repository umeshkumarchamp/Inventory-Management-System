package com.inventory.services;

import java.util.List;

import com.inventory.models.Item;
import com.inventory.models.Supplier;

public interface ItemService {
	
	public Item addItem(Item item);
	
	public List<Item> getAllItems();

	public boolean existsById(Long id);
	
	public Item getByItemId(Long id);
	
	public Boolean deleteItemById(Long id);
	
	public Item updateItemById(Item item);

	public Item getItemByName(String itemName);
	
}
