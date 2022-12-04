package com.carshop.CarShop.Service;

import com.carshop.CarShop.dtos.CartItemDTO;
import com.carshop.CarShop.exceptions.CartErrorException;

public interface CartItemService {
        void addItemToCart(long vehicleId, long cart_id) throws CartErrorException;
        void deleteItemFromCart(long vehicleId, long ccart_Id) throws CartErrorException;
}
