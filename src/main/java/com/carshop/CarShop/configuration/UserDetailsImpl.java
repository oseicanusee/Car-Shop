package com.carshop.CarShop.configuration;

import com.carshop.CarShop.model.Cart;
import com.carshop.CarShop.model.Role;
import com.carshop.CarShop.model.User;
import com.carshop.CarShop.model.Vehicle;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserDetailsImpl implements UserDetails {
    private Long user_id;
    private String first_name;
    private String last_name;
    private String email;
    private String username;

    @JsonIgnore
    private String password;
    private Set<Vehicle> vehicles = new HashSet<>();
    private Cart cart;
    private Integer points;



    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String first_name, String last_name, String username, String email, String password, Set<Vehicle> vehicles, Cart cart, Integer points,
                           Collection<? extends GrantedAuthority> authorities) {
        this.user_id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.vehicles = vehicles;
        this.cart = cart;
        this.points = points;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }


    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getUser_id(),
                user.getFirst_name(),
                user.getLast_name(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getVehicles(),
                user.getCart(),
                user.getPoints(),
                authorities);
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(user_id, user.user_id);
    }
}
