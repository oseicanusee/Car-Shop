package com.carshop.CarShop.Service.Impl;

import com.carshop.CarShop.Service.CartItemService;
import com.carshop.CarShop.configuration.MapStructMapper;
import com.carshop.CarShop.dtos.CartItemDTO;
import com.carshop.CarShop.exceptions.CartErrorException;
import com.carshop.CarShop.exceptions.ResourceNotFoundException;
import com.carshop.CarShop.model.Cart;
import com.carshop.CarShop.model.CartItem;
import com.carshop.CarShop.model.User;
import com.carshop.CarShop.model.Vehicle;
import com.carshop.CarShop.repository.CartItemRepository;
import com.carshop.CarShop.repository.CartRepository;
import com.carshop.CarShop.repository.UserRepository;
import com.carshop.CarShop.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class CartItemServiceImpl implements CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MapStructMapper mapStructMapper;

    @Override
    public CartItemDTO addItemToCart(long vehicleId, long userId) throws CartErrorException {

        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(userId);
        CartItem cartItem = null;

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // get the customer's cart -> then the get their cartItems set and then add the item.
            Vehicle vehicle = vehicleOptional.get();
            Set<CartItem> items = user.getCart().getCartItems();
            for (CartItem item : items) {
                if (item.getVehicle().getTitle().equals(vehicle.getTitle())) {
                    throw new CartErrorException("Cart Item in Cart Already");
                }
            }

            cartItem = new CartItem(vehicleOptional.get());
            Cart cart = userOptional.get().getCart();
            cart.getCartItems().add(cartItem);
            cartItemRepository.save(cartItem);
            cartRepository.save(cart);
        } else {
            throw new ResourceNotFoundException("User", "id", userId);
        }

        CartItemDTO cartItemDTO = mapStructMapper.cartItemToCartItemDTO(cartItem);
        return cartItemDTO;
    }


    public CartItemDTO deleteItemFromCart(long vehicleId, long userId) throws CartErrorException {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(userId);
        CartItem cartItem = null;
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            cartItem = new CartItem(vehicleOptional.get());
            Cart cart = user.getCart();

            for(CartItem item : cart.getCartItems()){
                if(item.equals(cartItem)){
                    cart.getCartItems().remove(item);
                    cartRepository.save(cart);
                    cartItemRepository.delete(cartItem);
                    break;
                }
            }

            // get the customer's cart -> then the get their cartItems set and then add the item.

        } else {
            throw new ResourceNotFoundException("User", "id", userId);
        }

        CartItemDTO cartItemDTO = mapStructMapper.cartItemToCartItemDTO(cartItem);
        return cartItemDTO;
    }


}
