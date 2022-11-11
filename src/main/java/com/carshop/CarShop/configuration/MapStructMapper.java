/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.carshop.CarShop.configuration;

import com.carshop.CarShop.dtos.*;
import com.carshop.CarShop.model.*;
import com.carshop.CarShop.payload.SignUpDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


/**
 *
 * @author jeff
 */

@Mapper(componentModel = "spring")
@Component
public interface MapStructMapper {

    MapStructMapper INSTANCE = Mappers.getMapper(MapStructMapper.class);
    
    VehicleDTO vehicleToVehicleDTO(Vehicle vehicle);

    Vehicle vehicleDTOToVehicle(VehicleDTO vehicleDTO);

    UserDTO userToUserDTO(User user);

    User userDTOToUser(UserDTO userDTO);

    RoleDTO roleToRoleDTO(Role role);

    Role roleDTOToRole(RoleDTO roleDTO);

    CartDTO cartToCartDTO(Cart cart);

    Cart cartDTOToCart(CartDTO cartDTO);

    CartItemDTO cartItemToCartItemDTO(CartItem cartItem);

    CartItem cartItemToCartDTO(Cart cart);


}
