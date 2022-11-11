package com.carshop.CarShop.dtos;

import com.carshop.CarShop.model.Cart;
import com.carshop.CarShop.model.CartItem;
import com.carshop.CarShop.model.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDTO {
    private long itemId;
    private Vehicle vehicle;
    private Cart cart;

    public CartItemDTO(CartItem cartItem){
        this.itemId = cartItem.getItemId();
        this.vehicle = cartItem.getVehicle();
        this.cart = cartItem.getCart();
    }
}
