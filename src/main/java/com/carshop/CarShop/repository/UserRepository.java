package com.carshop.CarShop.repository;

import com.carshop.CarShop.dtos.UserDTO;
import com.carshop.CarShop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}
