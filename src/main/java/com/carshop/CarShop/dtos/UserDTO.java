package com.carshop.CarShop.dtos;

import com.carshop.CarShop.model.Cart;
import com.carshop.CarShop.model.Role;
import com.carshop.CarShop.model.User;
import com.carshop.CarShop.model.Vehicle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private Long user_id;
    private String first_name;
    private String last_name;
    private String email;
    private String username;
    private String password;
    private Set<Vehicle> vehicles = new HashSet<>();
    private Collection<Role> roles = new ArrayList<>();
    private Cart cart;
    private Integer points;

    public UserDTO(User user){
        this.user_id = user.getUser_id();
        this.first_name = user.getFirst_name();
        this.last_name = user.getLast_name();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.vehicles = user.getVehicles();
        this.cart = user.getCart();
        this.points = user.getPoints();
    }

    public String getPassword(){
        return this.password;
    }


}
