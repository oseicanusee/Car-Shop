package com.carshop.CarShop.dtos;

import com.carshop.CarShop.model.Cart;
import com.carshop.CarShop.model.CartItem;
import com.carshop.CarShop.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
    private Long cartId;
    private User user;
    private Set<CartItem> cartItems;

    @Column(name = "TOTAL_PRICE")
    private double totalPrice;

    public CartDTO(Cart cart){
        this.cartId = cart.getCartId();
        this.user = cart.getUser();
        this.totalPrice = cart.getTotalPrice();
        this.cartItems = cart.getCartItems();
    }
}
