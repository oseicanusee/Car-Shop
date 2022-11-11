package com.carshop.CarShop.model;


import com.carshop.CarShop.dtos.CartDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "CART")
@Setter
@Getter
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CART_ID")
    private Long cartId;

    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CUSTOMER_ID")
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<CartItem> cartItems;

    @Column(name = "TOTAL_PRICE")
    private double totalPrice;

    public Cart(CartDTO cartDTO){
        this.user = cartDTO.getUser();
        this.totalPrice = cartDTO.getTotalPrice();
        this.cartItems = cartDTO.getCartItems();
    }

    public Cart() {
    }


    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public Set<CartItem> getCartItems() {
        return this.cartItems;
    }

    public double getTotalPrice() {
        if (cartItems.size() == 0) return 0;

        double total = 0;
        total = cartItems.stream().mapToDouble(item -> item.getVehicle().getPrice().doubleValue()).sum();
        return total;
    }
}

