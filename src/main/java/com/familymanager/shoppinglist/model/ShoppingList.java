package com.familymanager.shoppinglist.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class ShoppingList {

    @Id
    @GeneratedValue
    private int id;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<ShoppingItem> ListOfItems;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ShoppingItem> getListOfItems() {
        return ListOfItems;
    }

    public void setListOfItems(List<ShoppingItem> listOfItems) {
        ListOfItems = listOfItems;
    }

    public ShoppingList() {
    }

    @Override
    public String toString() {
        return "ShoppingList{" +
                "id=" + id +
                ", list of items='" + ListOfItems.toString() + '\'' +
                '}';
    }
}
