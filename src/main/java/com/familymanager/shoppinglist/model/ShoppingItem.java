package com.familymanager.shoppinglist.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ShoppingItem {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    public ShoppingItem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ShoppingItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    // Testing constructor
    public ShoppingItem(int id, String name) {
        this.id=id;
        this.name=name;
    }
}
