package com.familymanager.shoppinglist.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ShoppingItemNotFoundException extends RuntimeException {
    public ShoppingItemNotFoundException(String s) {
        super(s);
    }
}
