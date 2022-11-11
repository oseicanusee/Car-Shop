package com.carshop.CarShop.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.carshop.CarShop.dtos.CartItemDTO;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "CART_ITEM")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID")
    private long itemId;

    @ManyToOne
    @JoinColumn(name = "VEHICLE_ID")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "CART_ID")
    private Cart cart;

    public CartItem(CartItemDTO cartItemDTO){
        this.vehicle = cartItemDTO.getVehicle();
        this.cart = cartItemDTO.getCart();
    }

    public CartItem(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItem)) return false;
        CartItem cartItem = (CartItem) o;
        return itemId == cartItem.itemId && Objects.equals(vehicle, cartItem.vehicle) && Objects.equals(cart, cartItem.cart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, vehicle, cart);
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "itemId=" + itemId +
                ", vehicle=" + vehicle +
                ", cart=" + cart +
                '}';
    }

}