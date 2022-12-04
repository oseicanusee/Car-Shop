package com.carshop.CarShop.Service;

import com.carshop.CarShop.dtos.CartDTO;
import com.carshop.CarShop.dtos.CartItemDTO;
import com.carshop.CarShop.model.Cart;

import java.util.List;

public interface CartService {
    List<CartDTO> getAllCarts();
    List<CartItemDTO> getUserCart(String username);
    CartDTO deleteCart(long userId);
    List<CartItemDTO> getUserCartById(long user_id);
}
