package com.carshop.CarShop.model;

import com.carshop.CarShop.dtos.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long user_id;
    @Column(name = "first_name")
    private String first_name;
    @Column(name = "last_name")
    private String last_name;
    @Column(name = "email")
    private String email;
    @Column(name = "username")
    private String username;

    @JsonIgnoreProperties
    @Column(name = "password")
    private String password;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "CART_ID")
    private Cart cart;


    @Column(name = "points")
    private Integer points;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Vehicle> vehicles = new HashSet<>();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    public User(UserDTO userDTO){
        this.first_name = userDTO.getFirst_name();
        this.last_name= userDTO.getLast_name();
        this.email = userDTO.getEmail();
        this.username = userDTO.getUsername();
        this.password = userDTO.getPassword();
        this.vehicles = userDTO.getVehicles();
        this.cart = userDTO.getCart();
        this.points = userDTO.getPoints();
        this.role = userDTO.getRole();
    }


    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }


    public Role getRole(){
        return this.role;
    }


}
