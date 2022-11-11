/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.carshop.CarShop.Service;

import com.carshop.CarShop.dtos.VehicleDTO;
import java.util.List;


/**
 *
 * @author jeff
 */
public interface VehicleService {
    VehicleDTO saveVehicle(VehicleDTO vehicleDTO);
    VehicleDTO deleteVehicle(long vehicleId);
    VehicleDTO getVehicleById(long vehicleId);
    VehicleDTO updateVehicle(long vehicleId, VehicleDTO vehicleDTO);
    List<VehicleDTO> getAllVehicles();
}
