package com.carshop.CarShop.Service;

import com.carshop.CarShop.dtos.CartItemDTO;
import com.carshop.CarShop.exceptions.CartErrorException;

public interface CartItemService {
        CartItemDTO addItemToCart(long vehicleId, long customerId) throws CartErrorException;
        CartItemDTO deleteItemFromCart(long vehicleId, long customerId) throws CartErrorException;
}
