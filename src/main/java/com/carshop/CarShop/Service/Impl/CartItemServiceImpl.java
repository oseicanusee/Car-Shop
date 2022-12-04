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
    public void addItemToCart(long vehicleId, long cart_id) throws CartErrorException {

        Optional<Cart> cartOptional = cartRepository.findById(cart_id);
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(vehicleId);
        CartItem cartItem = null;
        Cart cart = null;
        if (cartOptional.isPresent()) {
            cart = cartOptional.get();
            // get the customer's cart -> then the get their cartItems set and then add the item.
            Vehicle vehicle = vehicleOptional.get();
            Set<CartItem> items = cart.getCartItems();
            for (CartItem item : items) {
                if (item.getVehicle().getId() == vehicleId) {
                    throw new CartErrorException("Cart Item in Cart Already");
                }
            }

            cartItem = new CartItem(vehicleOptional.get());
            cart = cartOptional.get();
            cart.getCartItems().add(cartItem);
            cartItemRepository.save(cartItem);
            cartRepository.save(cart);
        } else {
            throw new ResourceNotFoundException("User", "id", cart_id);
        }
    }


    public void deleteItemFromCart(long vehicleId, long cart_id) throws CartErrorException {
        Optional<Cart> cartOptional = cartRepository.findById(cart_id);

        CartItem cartItem = null;
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();

            for(CartItem item : cart.getCartItems()){
                if(item.getVehicle().getId() == vehicleId){
                    cart.getCartItems().remove(item);
                    cartRepository.save(cart);
                    cartItemRepository.delete(cartItem);
                    break;
                }
            }

            // get the customer's cart -> then the get their cartItems set and then add the item.

        } else {
            throw new ResourceNotFoundException("User", "id", vehicleId);
        }
    }


}
