package com.carshop.CarShop.repository;

import com.carshop.CarShop.model.Enum_Role;
import com.carshop.CarShop.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(Enum_Role name);
}
