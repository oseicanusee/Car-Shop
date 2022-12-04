package com.carshop.CarShop.payload;

import com.carshop.CarShop.model.Cart;
import com.carshop.CarShop.model.Role;
import com.carshop.CarShop.model.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse {
    private Long user_id;
    private String first_name;
    private String last_name;
    private String email;
    private String username;
    private String password;
    private Set<Vehicle> vehicles = new HashSet<>();
    private Cart cart;
    private Integer points;
    private Role role;

    public UserResponse(Long user_id, String first_name, String last_name, String email, String username, Set<Vehicle> vehicles, Cart cart, Integer points, Role role){
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.username = username;
        this.vehicles = vehicles;
        this.cart = cart;
        this.points = points;
        this.role = role;
    }

    public UserResponse(Long user_id){
        this.user_id = user_id;
    }

}
