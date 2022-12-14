package com.carshop.CarShop.Service.Impl;

import com.carshop.CarShop.Service.CartService;
import com.carshop.CarShop.configuration.MapStructMapper;
import com.carshop.CarShop.dtos.CartDTO;
import com.carshop.CarShop.dtos.CartItemDTO;
import com.carshop.CarShop.dtos.UserDTO;
import com.carshop.CarShop.model.Cart;
import com.carshop.CarShop.model.CartItem;
import com.carshop.CarShop.model.User;
import com.carshop.CarShop.repository.CartRepository;
import com.carshop.CarShop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MapStructMapper mapStructMapper;
    @Autowired
    private UserServiceImpl userServiceImpl;
    // get all cart items.
      @Override
      public List<CartDTO> getAllCarts() {
          List<Cart> carts = cartRepository.findAll();
          return carts.stream().map(cart -> mapStructMapper.cartToCartDTO(cart)).collect(Collectors.toList());
      }

    @Override
    public List<CartItemDTO> getUserCart(String username) {
        UserDTO userDTO =  userServiceImpl.getUserByUsername(username);
        List<CartItem> items = userDTO.getCart().getCartItems().stream().collect(Collectors.toList());
        List<CartItemDTO> cartItemDTO = items.stream().map(item -> mapStructMapper.cartItemToCartItemDTO(item)).collect(Collectors.toList());
        return cartItemDTO;
    }

    @Override
      public CartDTO deleteCart(long userId) {
          Optional<User> userOptional = userRepository.findById(userId);
          CartDTO cartDTO = null;
          if(userOptional.isPresent()){
              Cart cart = userOptional.get().getCart();
              cartDTO = mapStructMapper.cartToCartDTO(cart);
              cartRepository.delete(cart);
              userRepository.save(userOptional.get());
          }
        return cartDTO;
   }

    @Override
    public List<CartItemDTO> getUserCartById(long cart_id) {
        Optional<Cart> cartOptional =  cartRepository.findById(cart_id);
        if(cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            List<CartItem> items = cart.getCartItems().stream().collect(Collectors.toList());
            List<CartItemDTO> cartItemDTO = items.stream().map(item -> mapStructMapper.cartItemToCartItemDTO(item)).collect(Collectors.toList());
            return cartItemDTO;
        }
        return null;
    }



}