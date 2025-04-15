package com.revature.yolp.utils.custom_exceptions;

public class DuplicateRestaurantNameException extends RuntimeException {
    public DuplicateRestaurantNameException(String message) {
        super(message);
    }
}
