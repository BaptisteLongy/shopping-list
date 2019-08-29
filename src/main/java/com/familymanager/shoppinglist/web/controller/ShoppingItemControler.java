package com.familymanager.shoppinglist.web.controller;

import com.familymanager.shoppinglist.dao.ShoppingItemDAO;
import com.familymanager.shoppinglist.model.ShoppingItem;
import com.familymanager.shoppinglist.web.exceptions.ShoppingItemNotFoundException;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class ShoppingItemControler {

    @Autowired
    private ShoppingItemDAO aShoppingItemDAO;

    @CrossOrigin
    @GetMapping(value="/ShoppingItems")
    public List<ShoppingItem> listShoppingItems() {
        return aShoppingItemDAO.findAll();
    }

    @CrossOrigin
    @GetMapping(value="/ShoppingItems/{id}")
    public ShoppingItem getShoppingItemByID(@PathVariable int id) {
        ShoppingItem foundShoppingItem = aShoppingItemDAO.findById(id);
        if (foundShoppingItem==null) {
                throw new ShoppingItemNotFoundException("Shopping Item with id " + id + " not found");
        }
        return foundShoppingItem;
    }

    @CrossOrigin
    @PostMapping(value="/ShoppingItems")
    public ResponseEntity<Void> addShoppingItem(@RequestBody ShoppingItem newShoppingItem) {
        ShoppingItem shoppingItemAdded = aShoppingItemDAO.save(newShoppingItem);

        if (shoppingItemAdded == null) {
            return ResponseEntity.noContent().build();
        }

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(shoppingItemAdded.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @CrossOrigin
    @DeleteMapping(value="/ShoppingItems/{id}")
    public void deleteShoppingItems(@PathVariable int id) {
        ShoppingItem shoppingItemToDelete = aShoppingItemDAO.findById(id);
        aShoppingItemDAO.delete(shoppingItemToDelete);
    }

    @CrossOrigin
    @PutMapping(value="/ShoppingItems")
    public ShoppingItem updateShoppingItem(@RequestBody ShoppingItem updatedShoppingItem) {
        aShoppingItemDAO.save(updatedShoppingItem);
        return updatedShoppingItem;
    }

//    @CrossOrigin
//    @PostMapping(value="/ShoppingList")
//    public ResponseEntity<Void> addShoppingList(@RequestBody String shoppingList) {
//        String[] shoppingListAsList = shoppingList.split("\n");
//        for (String shoppingItemAsString : shoppingListAsList
//             ) {
//            ShoppingItem newShoppingItem = new ShoppingItem();
//            newShoppingItem.setName(shoppingItemAsString);
//            aShoppingItemDAO.save(newShoppingItem);
//        }
//
//        if (shoppingListAsList == null) {
//            return ResponseEntity.noContent().build();
//        }
//
//        return ResponseEntity.created(null).build();
//    }
}
