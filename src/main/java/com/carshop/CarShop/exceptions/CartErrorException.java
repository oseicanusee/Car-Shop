package com.carshop.CarShop.exceptions;

public class CartErrorException extends Exception{
    public CartErrorException(String message) {
        super(message);
    }

    public CartErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
