/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carshop.CarShop.controller;

import com.carshop.CarShop.Service.Impl.VehicleServiceImpl;
import com.carshop.CarShop.dtos.VehicleDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author jeff
 */

    @RestController
    @RequestMapping("/api/vehicles")
    @CrossOrigin(origins = "http://localhost:3000")
    public class VehicleController {

    @Autowired
    private VehicleServiceImpl vehicleServiceImpl;

    //   /api/vehicles/auth/**

    @GetMapping("/all")
    public ResponseEntity<List<VehicleDTO>> getAllVehicles(){
        return new ResponseEntity<List<VehicleDTO>>(vehicleServiceImpl.getAllVehicles(), HttpStatus.OK);
    }


    @GetMapping("/{vehicleId}")
    public ResponseEntity<VehicleDTO> getVehicleById(@PathVariable long vehicleId){
        return new ResponseEntity<VehicleDTO>(vehicleServiceImpl.getVehicleById(vehicleId), HttpStatus.OK);
    }

   
    @PostMapping("auth/admin/add")
    public ResponseEntity<VehicleDTO> addVehicle(@RequestBody VehicleDTO vehicleDTO){
        return new ResponseEntity<VehicleDTO>(vehicleServiceImpl.saveVehicle(vehicleDTO), HttpStatus.CREATED);
    }


    @DeleteMapping("/auth/admin/{vehicleId}")
    public ResponseEntity<VehicleDTO> deleteVehicle(@PathVariable long vehicleId){
        return new ResponseEntity<VehicleDTO>(vehicleServiceImpl.deleteVehicle(vehicleId), HttpStatus.OK);
    }

    
    @PutMapping("/auth/admin/{id}")
    public ResponseEntity<VehicleDTO> updateVehicle(@PathVariable long id,
                                                    @RequestBody VehicleDTO vehicleDTO){
        return new ResponseEntity<VehicleDTO>(vehicleServiceImpl.updateVehicle(id, vehicleDTO), HttpStatus.OK);
    }

   
    @PatchMapping("/auth/admin/{id}")
    public ResponseEntity<VehicleDTO> patchVehicle(@PathVariable long id, @RequestBody VehicleDTO vehicleDTO){
        return new ResponseEntity<VehicleDTO>(vehicleServiceImpl.updateVehicle(id, vehicleDTO), HttpStatus.OK);
    }
}
