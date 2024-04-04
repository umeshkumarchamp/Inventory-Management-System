package com.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.models.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
