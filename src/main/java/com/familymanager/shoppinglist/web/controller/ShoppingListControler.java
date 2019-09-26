package com.familymanager.shoppinglist.web.controller;

import com.familymanager.shoppinglist.dao.ShoppingItemDAO;
import com.familymanager.shoppinglist.dao.ShoppingListDAO;
import com.familymanager.shoppinglist.model.ShoppingItem;
import com.familymanager.shoppinglist.model.ShoppingList;
import com.familymanager.shoppinglist.web.exceptions.ShoppingListNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class ShoppingListControler {

    @Autowired
    private ShoppingListDAO aShoppingListDAO;

    @Autowired
    private ShoppingItemDAO aShoppingItemDAO;

    @CrossOrigin
    @GetMapping(value="/ShoppingLists/{id}")
    public ShoppingList getShoppingListById(@PathVariable int id) {
        ShoppingList foundShoppingList = aShoppingListDAO.findById(id);

        if (foundShoppingList==null) {
            throw new ShoppingListNotFoundException("Shopping List with id " + id + " not found");
        }

        return foundShoppingList;
    }

    @CrossOrigin
    @GetMapping(value="/ShoppingLists/")
    public List<ShoppingList> getShoppingListById() {
        List<ShoppingList> foundShoppingLists = aShoppingListDAO.findAll();

        return foundShoppingLists;
    }

    @CrossOrigin
    @PostMapping(value="/ShoppingLists/")
    public ResponseEntity<Void> addShoppingList(@RequestBody ShoppingList newShoppingList) {
        ShoppingList shoppingListAdded = aShoppingListDAO.save(newShoppingList);

        if (shoppingListAdded == null) {
            return ResponseEntity.noContent().build();
        }

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(shoppingListAdded.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @CrossOrigin
    @PutMapping(value="/ShoppingLists/{id}/AddItem")
    public ShoppingList addShoppingItemToShoppingList(@PathVariable int id, @RequestBody ShoppingItem newShoppingItem) {
        ShoppingList foundShoppingList = getShoppingListById(id);
        boolean success = foundShoppingList.getListOfItems().add(newShoppingItem);
        aShoppingListDAO.save(foundShoppingList);

        return  foundShoppingList;
    }

    @CrossOrigin
    @PutMapping(value="/ShoppingLists/{id_list}/DeleteItem/{id_item}")
    public ShoppingList addShoppingItemToShoppingList(@PathVariable int id_list, @PathVariable int id_item) {
        ShoppingList foundShoppingList = getShoppingListById(id_list);
        int item_position = foundShoppingList.getListOfItems().indexOf(aShoppingItemDAO.findById(id_item));
        if (item_position != -1) {
            foundShoppingList.getListOfItems().remove(item_position);
            aShoppingListDAO.save(foundShoppingList);
        }

        return  foundShoppingList;
    }
}
