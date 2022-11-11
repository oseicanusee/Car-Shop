package com.carshop.CarShop.Service;

import com.carshop.CarShop.dtos.RoleDTO;
import com.carshop.CarShop.payload.SignInDTO;
import com.carshop.CarShop.dtos.UserDTO;
import com.carshop.CarShop.dtos.VehicleDTO;
import com.carshop.CarShop.exceptions.UserExistsException;
import com.carshop.CarShop.payload.SignUpDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO updateUserInfo(long userId, UserDTO userDTO);
    String createUser(SignUpDTO signUpDTO) throws UserExistsException;
    List<VehicleDTO> getCart();
    UserDTO updateUserPassword(long userId, String password);
    RoleDTO saveRole(RoleDTO roleDTO);
    void addRoleToUser(String username, String roleName);
    UserDTO getUserByUsername(String username);
    UserDTO findUserById(long user_id);
}
