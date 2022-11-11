package com.carshop.CarShop.controller;

import com.carshop.CarShop.Service.Impl.UserServiceImpl;
import com.carshop.CarShop.configuration.UserDetailsImpl;
import com.carshop.CarShop.dtos.RoleDTO;
import com.carshop.CarShop.dtos.RoleToUserForm;
import com.carshop.CarShop.payload.SignInDTO;
import com.carshop.CarShop.dtos.UserDTO;
import com.carshop.CarShop.exceptions.UserExistsException;
import com.carshop.CarShop.payload.SignUpDTO;
import com.carshop.CarShop.payload.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;



@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class UserController {


    @Autowired
    private UserServiceImpl userServiceImpl;
//    @Autowired
//    private AuthenticationManager authenticationManager;
//   @Autowired
//    private JwtService jwtService;


    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return new ResponseEntity<List<UserDTO>>(userServiceImpl.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable long user_id){
        return new ResponseEntity<UserDTO>(userServiceImpl.findUserById(user_id), HttpStatus.OK);
    }

    // all can access.
    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@Valid @RequestBody SignUpDTO signUpDTO) throws UserExistsException {
        String response = userServiceImpl.createUser(signUpDTO);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

//    @PostMapping("/signin")
//    public ResponseEntity<?> authenticateUser(@Valid @RequestBody SignInDTO signInDTO) {
//
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInDTO.getUserName(), signInDTO.getPassword()));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//
//        String jwtToken = jwtService.generateTokenFromUsername(authentication.getName());
//
//        List<String> roles = userDetails.getAuthorities().stream()
//                .map(item -> item.getAuthority())
//                .collect(Collectors.toList());
//
//        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, "Bearer "+ jwtToken)
//                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
//                .body(new UserResponse(userDetails.getUser_id(),
//                        userDetails.getFirst_name(),
//                        userDetails.getLast_name(),
//                        userDetails.getEmail(),
//                        userDetails.getUsername(),
//                        userDetails.getPassword(),
//                        userDetails.getVehicles(),
//                        userDetails.getCart(),
//                        userDetails.getPoints(),
//                        roles));
//    }
//
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
