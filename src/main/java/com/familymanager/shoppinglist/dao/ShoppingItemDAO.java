package com.familymanager.shoppinglist.dao;

import com.familymanager.shoppinglist.model.ShoppingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingItemDAO extends JpaRepository<ShoppingItem, Integer> {

        ShoppingItem findById(int id);
}
