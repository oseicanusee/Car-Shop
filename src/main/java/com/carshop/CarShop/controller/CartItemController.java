package com.carshop.CarShop.controller;


import com.carshop.CarShop.Service.Impl.CartItemServiceImpl;
import com.carshop.CarShop.Service.Impl.UserServiceImpl;
import com.carshop.CarShop.configuration.JwtTokenHelper;
import com.carshop.CarShop.dtos.UserDTO;
import com.carshop.CarShop.exceptions.CartErrorException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user/cart")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class CartItemController {

    @Autowired
    private CartItemServiceImpl cartItemServiceImpl;
    @Autowired
    private JwtTokenHelper jwtTokenHelper;
    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("/add/{vehicle_id}")
    public void addVehicleToCart(@PathVariable long vehicle_id) throws CartErrorException {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        long cart_id = userServiceImpl.getUserByUsername(username).getCart().getCartId();
        cartItemServiceImpl.addItemToCart(vehicle_id, cart_id);
    }

    @DeleteMapping("/delete/{vehicle_id}")
    public void deleteVehicleFromCart(@PathVariable long vehicle_id) throws CartErrorException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        long cart_id = userServiceImpl.getUserByUsername(username).getCart().getCartId();
        cartItemServiceImpl.deleteItemFromCart(vehicle_id, cart_id);
    }

}
