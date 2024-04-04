package com.inventory.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inventory.models.Item;
import com.inventory.repository.ItemRepository;
import com.inventory.services.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemRepository itemRepo;

	@Override
	public Item addItem(Item item) {
		return itemRepo.save(item);
	}

	@Override
	public List<Item> getAllItems() {
		List<Item> items = itemRepo.findAll(); 
		return items;
	}

	@Override
	public boolean existsById(Long id) {
		Optional<Item> item =  itemRepo.findById(id); 
		if(item.isPresent()) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Item getByItemId(Long id) {
		Optional<Item> op =  itemRepo.findById(id); 
		if(op.isPresent()) {
			Item item = op.get();
			return item;
		}
		return null;
		
	}

}
