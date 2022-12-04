package com.carshop.CarShop.Service.Impl;

import com.carshop.CarShop.Service.UserService;
import com.carshop.CarShop.configuration.MapStructMapper;
import com.carshop.CarShop.dtos.RoleDTO;
import com.carshop.CarShop.model.Cart;
import com.carshop.CarShop.dtos.UserDTO;
import com.carshop.CarShop.dtos.VehicleDTO;
import com.carshop.CarShop.exceptions.ResourceNotFoundException;
import com.carshop.CarShop.exceptions.UserExistsException;
import com.carshop.CarShop.model.Role;
import com.carshop.CarShop.model.User;
import com.carshop.CarShop.payload.SignUpDTO;
import com.carshop.CarShop.repository.CartRepository;
import com.carshop.CarShop.repository.RoleRepository;
import com.carshop.CarShop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserDetailsService, UserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MapStructMapper mapStructMapper;
    @Lazy
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private CartRepository cartRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if(userOptional.isPresent()){
            return new org.springframework.security.core.userdetails.User(userOptional.get().getUsername(), userOptional.get().getPassword(),
                    getGrantedAuthority(userOptional.get()));
        } else {
            throw new UsernameNotFoundException("Username not found in the database");
        }
    }

    private Collection<GrantedAuthority> getGrantedAuthority(User user) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        if (user.getRole().getName().equalsIgnoreCase("admin")) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return authorities;
    }



    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> mapStructMapper.userToUserDTO(user)).collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUserInfo(long userId, UserDTO userDTO) {
            Optional<User> currUser = userRepository.findById(userId);
            User updatedUser = null;

            if(currUser.isPresent()){
                updatedUser = currUser.get();
                updatedUser.setFirst_name(userDTO.getFirst_name());
                updatedUser.setLast_name(userDTO.getLast_name());
                updatedUser.setEmail(userDTO.getEmail());
                updatedUser.setPoints(userDTO.getPoints());
                updatedUser.setCart(userDTO.getCart());
                updatedUser.setPassword(encoder.encode(userDTO.getPassword()));
                updatedUser.setUsername(userDTO.getUsername());
                userRepository.save(updatedUser);
                return mapStructMapper.userToUserDTO(updatedUser);
            } else {
                throw new ResourceNotFoundException("User", "id", userId);
            }
    }

    public UserDTO updateUserPassword(long userId, String password){
        Optional<User> userOptional = userRepository.findById(userId);

        if(userOptional.isPresent()){
            User user = userOptional.get();
            String hashPassword = encoder.encode(password);
            user.setPassword(hashPassword);
            userRepository.save(user);
            return mapStructMapper.userToUserDTO(user);
        }
        throw new ResourceNotFoundException("User", "id", userId);
    }

    @Override
    public RoleDTO saveRole(RoleDTO roleDTO) {
        Role role = mapStructMapper.roleDTOToRole(roleDTO);
        roleRepository.save(role);
        RoleDTO currRoleDTO = mapStructMapper.roleToRoleDTO(role);
        return currRoleDTO;
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
//            Optional<User> userOptional = userRepository.findByUsername(username);
//            Role role = roleRepository.findByName("f");
//
//            if(userOptional.isPresent()){
//                User user = userOptional.get();
//                user.getRoles().add(role);
//                roleRepository.save(role);
//                userRepository.save(user);
//            }
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if(userOptional.isPresent()){
            UserDTO userDTO = mapStructMapper.userToUserDTO(userOptional.get());
            return userDTO;
        }
        throw new UsernameNotFoundException("Username not found");
    }

    public boolean emailExists(String email) throws UserExistsException {
        Optional<User> user = userRepository.findByEmail(email);

        if(user.isPresent()){
            return true;
        }

        return false;
    }

    public boolean usernameExists(String username) throws UserExistsException {
        Optional<User> user = userRepository.findByUsername(username);

        if(user.isPresent()){
            return true;
        }

        return false;
    }

    @Override
    public String createUser(SignUpDTO signUpDTO) throws UserExistsException {
        //logger.info("User registration");
        JSONObject jsonObject = new JSONObject();

        if (emailExists(signUpDTO.getEmail())) {
            jsonObject.put("Error", "Error: Email exists");
            return jsonObject.toString();
        } else if (usernameExists(signUpDTO.getUsername())) {
            jsonObject.put("Error", "Error: Username exists");
            return jsonObject.toString();
        } else {
            User user = new User();
            user.setFirst_name(signUpDTO.getFirst_name());
            user.setLast_name(signUpDTO.getLast_name());
            user.setEmail(signUpDTO.getEmail());
            user.setUsername(signUpDTO.getUsername());
            user.setPoints(Integer.MAX_VALUE);
            String password = signUpDTO.getPassword();

            String hashedPassword = encoder.encode(password);
            user.setPassword(hashedPassword);


            Role role = new Role("USER");
            roleRepository.save(role);
            user.setRole(role);

            Cart cart = new Cart();
            cart.setUser(user);
            user.setCart(cart);

            userRepository.save(user);
            if (userRepository.findByUsername(user.getUsername()).isPresent()) {
                return "User registered successfully";
            } else {
                return "User registration unsuccessful, Please check input";
            }
        }
    }


    @Override
    public List<VehicleDTO> getCart() {
        return null;
    }

    @Override
    public UserDTO findUserById(long user_id) {
        Optional<User> userOptional = userRepository.findById(user_id);

        if(userOptional.isPresent()){
            UserDTO userDTO = mapStructMapper.userToUserDTO(userOptional.get());
            return userDTO;
        }
        throw new ResourceNotFoundException("User", "id:", user_id);
    }
}
