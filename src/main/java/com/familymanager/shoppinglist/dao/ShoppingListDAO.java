package com.familymanager.shoppinglist.dao;

import com.familymanager.shoppinglist.model.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingListDAO extends JpaRepository<ShoppingList, Integer> {

    ShoppingList findById(int id);
}
