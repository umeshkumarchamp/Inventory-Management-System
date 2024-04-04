package com.inventory.services;

import java.util.List;

import com.inventory.models.Item;

public interface ItemService {
	
	public Item addItem(Item item);
	
	public List<Item> getAllItems();

	public boolean existsById(Long id);
	
	public Item getByItemId(Long id);
	
}
