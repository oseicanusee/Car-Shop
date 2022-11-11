/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.carshop.CarShop.repository;

import com.carshop.CarShop.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jeff
 */
public interface VehicleRepository extends JpaRepository<Vehicle, Long>{
    
    
    
}
