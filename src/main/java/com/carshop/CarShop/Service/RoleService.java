package com.carshop.CarShop.Service;

import com.carshop.CarShop.dtos.RoleDTO;
import com.carshop.CarShop.model.Role;

import java.util.Collection;

public interface RoleService {
    RoleDTO findByName(String name);
    RoleDTO findById(Long id);
    Collection<Role> findAll();
    Role saveOrUpdate(RoleDTO roleDTO);
    String deleteById(Long id);
}
