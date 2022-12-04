/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carshop.CarShop.dtos;

import com.carshop.CarShop.model.Vehicle;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author jeff
 */
    @Data
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
public class VehicleDTO {
       
    private Long id;

    @NotNull
    private int year;

    @NotNull
    private String make;

    @NotNull
    private String model;

    @NotNull
    private long mileage;

    private String imageURL;
    private String cylinder;
    private double engine_size;
    private String fuel_Type;

    @NotNull
    private String transmission;

    private String drive_wheels;

    @NotNull
    private Integer price;

    @NotNull
    private String title;

    private String trim;

    public VehicleDTO(Vehicle vehicle) {
        this.id = vehicle.getId();
        this.year = vehicle.getYear();
        this.make = vehicle.getMake();
        this.model = vehicle.getModel();
        this.imageURL = vehicle.getImageURL();
        this.cylinder = vehicle.getCylinder();
        this.engine_size = vehicle.getEngine_size();
        this.transmission = vehicle.getTransmission();
        this.drive_wheels = vehicle.getDrive_wheels();
        this.price = vehicle.getPrice();
        this.title = vehicle.getTitle();
        this.trim = vehicle.getTrim();
        this.fuel_Type = vehicle.getFuel_Type();
    }

    public String getTitle(){
        return this.year + " " + this.make + " "  + this.model + " " + this.trim;
    }

}
