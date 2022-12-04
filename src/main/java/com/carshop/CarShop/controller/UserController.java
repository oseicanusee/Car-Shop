package com.carshop.CarShop.controller;

import com.carshop.CarShop.Service.Impl.UserServiceImpl;
import com.carshop.CarShop.configuration.JwtTokenHelper;
import com.carshop.CarShop.dtos.RoleDTO;
import com.carshop.CarShop.dtos.RoleToUserForm;
import com.carshop.CarShop.exceptions.ResourceNotFoundException;
import com.carshop.CarShop.payload.SignInDTO;
import com.carshop.CarShop.dtos.UserDTO;
import com.carshop.CarShop.exceptions.UserExistsException;
import com.carshop.CarShop.payload.SignUpDTO;
import com.carshop.CarShop.payload.UserResponse;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;



@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenHelper jwtTokenProvider;
    @Autowired
    private PasswordEncoder encoder;

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return new ResponseEntity<List<UserDTO>>(userServiceImpl.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable long user_id){
        return new ResponseEntity<UserDTO>(userServiceImpl.findUserById(user_id), HttpStatus.OK);
    }

    // all can access.
    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveUser(@Valid @RequestBody SignUpDTO signUpDTO) throws UserExistsException {
        String response = userServiceImpl.createUser(signUpDTO);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @PostMapping(value = "/signin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody SignInDTO signInDTO) throws NoSuchAlgorithmException, InvalidKeyException {
        logger.info("SignUpDTO Username " + signInDTO.getUsername());
        logger.info("SignUpDTO Password " + signInDTO.getPassword());
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInDTO.getUsername(), signInDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

            User user = (User)authentication.getPrincipal();
            String jwtToken = jwtTokenProvider.generateToken(user.getUsername());

            return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken)
                    .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization").build();
        }

//    @PostMapping("/signout")
//    public ResponseEntity<?> logoutUser(){
//        ResponseCookie cookie = jwtService.getCleanJwtCookie();
//        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body("You have successfully signed out!");
//    }


//    @PutMapping("/updateUser/{userId}")
//    public ResponseEntity<UserDTO> updateUserInfo(@PathVariable long userId, @RequestBody UserDTO userDTO) throws UserExistsException {
//        return new ResponseEntity<UserDTO>(userServiceImpl.updateUserInfo(userId, userDTO), HttpStatus.OK);
//    }



    @PutMapping("/updateUser/{userId}")
    public ResponseEntity<UserDTO> patchUserInfo(@PathVariable long userId, @RequestBody UserDTO userDTO) throws UserExistsException {
        return new ResponseEntity<UserDTO>(userServiceImpl.updateUserInfo(userId, userDTO), HttpStatus.OK);
    }

    @PostMapping("updatePassword/{userId}")
    public ResponseEntity<UserDTO> updatePassword(@PathVariable long userId, String password){
        return new ResponseEntity<UserDTO>(userServiceImpl.updateUserPassword(userId, password), HttpStatus.OK);
    }

    @PostMapping("/role/save")
    public ResponseEntity<RoleDTO> saveRole(@RequestBody RoleDTO roleDTO){
        return new ResponseEntity<RoleDTO>(userServiceImpl.saveRole(roleDTO), HttpStatus.OK);
    }

    @PostMapping("role/addToUser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form){
        userServiceImpl.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }




}
