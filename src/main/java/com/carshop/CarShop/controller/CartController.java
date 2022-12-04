package com.carshop.CarShop.controller;


import com.carshop.CarShop.Service.Impl.CartServiceImpl;
import com.carshop.CarShop.Service.Impl.UserServiceImpl;
import com.carshop.CarShop.configuration.JwtTokenHelper;
import com.carshop.CarShop.dtos.CartDTO;
import com.carshop.CarShop.dtos.CartItemDTO;
import com.carshop.CarShop.dtos.UserDTO;
import com.carshop.CarShop.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("api/user/cart")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class CartController {

    @Autowired
    private JwtTokenHelper jwtHelper;
    @Autowired
    private CartServiceImpl cartServiceImpl;
    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/")
    public ResponseEntity<List<CartItemDTO>> getUserCart(){
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       System.out.println(authentication.getCredentials());
       String username = authentication.getName();
       UserDTO userDTO = userServiceImpl.getUserByUsername(username);
       long cart_id = userDTO.getCart().getCartId();
       return new ResponseEntity<List<CartItemDTO>>(cartServiceImpl.getUserCartById(cart_id), HttpStatus.OK);
    }



    @GetMapping("/{cart_id}")
    public ResponseEntity<List<CartItemDTO>> getUserCartById(@PathVariable long cart_id){
        return new ResponseEntity<List<CartItemDTO>>(cartServiceImpl.getUserCartById(cart_id), HttpStatus.OK);
    }

}
