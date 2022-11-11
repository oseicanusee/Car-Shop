package com.carshop.CarShop.Service;

import com.carshop.CarShop.dtos.CartDTO;

import java.util.List;

public interface CartService {
    List<CartDTO> getAllCarts();
    CartDTO getUserCart(long userId);
    CartDTO deleteCart(long userId);
}
