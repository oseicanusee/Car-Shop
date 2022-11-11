/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carshop.CarShop.Service.Impl;


import com.carshop.CarShop.Service.VehicleService;
import com.carshop.CarShop.configuration.MapStructMapper;
import com.carshop.CarShop.dtos.VehicleDTO;
import com.carshop.CarShop.exceptions.ResourceNotFoundException;
import com.carshop.CarShop.model.Vehicle;
import com.carshop.CarShop.repository.VehicleRepository;
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jeff
 */

@Service
@RequiredArgsConstructor
@Transactional
public class VehicleServiceImpl implements VehicleService {
//       
    @Autowired
    private VehicleRepository vehicleRepository;
    
    @Autowired
    private MapStructMapper mapStructMapper ;
    
    
    @Override
    public List<VehicleDTO> getAllVehicles() {
        List<Vehicle> vehicleList = vehicleRepository.findAll();
        return vehicleList.stream().map(vehicle -> mapStructMapper.vehicleToVehicleDTO(vehicle)).collect(Collectors.toList());
    }
    
    @Override
    public VehicleDTO saveVehicle(VehicleDTO vehicleDTO) {        
       Vehicle vehicle = mapStructMapper.vehicleDTOToVehicle(vehicleDTO);
        vehicleRepository.save(vehicle);
        return new VehicleDTO(vehicle);
    }

    @Override
    public VehicleDTO deleteVehicle(long id){
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(id);

        if(vehicleOptional.isPresent()){
            VehicleDTO vehicleDTO = mapStructMapper.vehicleToVehicleDTO(vehicleOptional.get());
            vehicleRepository.deleteById(id);
            return vehicleDTO;
        } else {
            throw new ResourceNotFoundException("Vehicle", "id", id);
        }
    }

    @Override
    public VehicleDTO getVehicleById(long vehicleId){
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(vehicleId);

        if(vehicleOptional.isPresent()){
            VehicleDTO vehicleDTO = mapStructMapper.vehicleToVehicleDTO(vehicleOptional.get());
            return vehicleDTO;
        } else {
            throw new ResourceNotFoundException("Vehicle", "id", vehicleId);
        }
    }

    @Override
    public VehicleDTO updateVehicle(long vehicleId, VehicleDTO vehicleDTO){
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(vehicleId);

        if(vehicleOptional.isPresent()){
            Vehicle vehicle = vehicleOptional.get();
            vehicle.setCylinder(vehicleDTO.getCylinder());
            vehicle.setEngine_size(vehicleDTO.getEngine_size());
            vehicle.setMileage(vehicleDTO.getMileage());
            vehicle.setImageURL(vehicleDTO.getImageURL());
            vehicle.setModel(vehicleDTO.getModel());
            vehicle.setMake(vehicleDTO.getMake());
            vehicle.setTrim(vehicle.getTrim());
            vehicle.setDrive_wheels(vehicle.getDrive_wheels());
            vehicle.setFuel_Type(vehicle.getFuel_Type());
            vehicle.setTransmission(vehicle.getTransmission());
            vehicle.setYear(vehicleDTO.getYear());
            vehicleRepository.save(vehicle);
            return new VehicleDTO(vehicle);
        } else throw new ResourceNotFoundException("Vehicle", "id", vehicleId);
    }

    
}
