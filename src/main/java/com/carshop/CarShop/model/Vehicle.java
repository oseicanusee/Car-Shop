/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carshop.CarShop.model;

import com.carshop.CarShop.dtos.VehicleDTO;
import java.util.Objects;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

/**
 *
 * @author jeff
 */
@Entity
@Table(name = "VEHICLE")
@Getter
@Builder
@Setter
@AllArgsConstructor
public class Vehicle {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @javax.persistence.Id
    private Long id;

    @Column(name = "TITLE")
    private String title;


    @Column(name = "YEAR")
    private int year;

    @Column(name = "MAKE")
    private String make;

    @Column(name = "TRIM")
    private String trim;

    @Column(name = "MODEL")
    private String model;

    @Column(name = "IMAGE")
    private String imageURL;

    @Column(name = "CYLINDER")
    private String cylinder;

    @Column(name = "ENGINE_SIZE")
    private double engine_size;


    @Column(name = "TRANSMISSION")
    private String transmission;

    @Column(name ="DRIVE_WHEELS")
    private String drive_wheels;

    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "FUEL_TYPE")
    private String fuel_Type;


    public Vehicle(){}

    public Vehicle(VehicleDTO vehicleDTO){
        this.year = vehicleDTO.getYear();
        this.make = vehicleDTO.getMake();
        this.model = vehicleDTO.getModel();
        this.imageURL = vehicleDTO.getImageURL();
        this.cylinder = vehicleDTO.getCylinder();
        this.engine_size = vehicleDTO.getEngine_size();
        this.transmission = vehicleDTO.getTransmission();
        this.drive_wheels = vehicleDTO.getDrive_wheels();
        this.price = vehicleDTO.getPrice();
        this.title = vehicleDTO.getTitle();
        this.trim = vehicleDTO.getTrim();
        this.fuel_Type = vehicleDTO.getFuel_Type();
    }

    public Vehicle(int year, String model, String make){
        super();
        this.year = year;
        this.model = model;
        this.make = make;
    }


    public Vehicle(int year, String make, String model,String imageURL,
                   String cylinder, double engine_size, String drive_wheels,
                   Integer price, String fuel_Type) {
        super();
        this.year = year;
        this.model = model;
        this.make = make;
        this.imageURL = imageURL;
        this.cylinder = cylinder;
        this.engine_size = engine_size;
        this.drive_wheels = drive_wheels;
        this.price = price;
        this.title = getTitle();
        this.fuel_Type = fuel_Type;
    }

    public String getTitle(){
        return this.year + " " + this.make + " "  + this.model + " " + this.trim;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) o;
        return year == vehicle.year && cylinder == vehicle.cylinder &&
                Double.compare(vehicle.engine_size, engine_size) == 0 && Objects.equals(id, vehicle.id)
                && Objects.equals(title, vehicle.title) && Objects.equals(make, vehicle.make)
                && Objects.equals(trim, vehicle.trim) && Objects.equals(model, vehicle.model) && Objects.equals(imageURL, vehicle.imageURL)  &&
                Objects.equals(transmission, vehicle.transmission) && Objects.equals(drive_wheels, vehicle.drive_wheels) && Objects.equals(price, vehicle.price) && Objects.equals(fuel_Type, vehicle.fuel_Type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, year, make, trim, model, imageURL, cylinder, engine_size, transmission, drive_wheels, price, fuel_Type);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicle_id=" + id +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", make='" + make + '\'' +
                ", trim='" + trim + '\'' +
                ", model='" + model + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", cylinder=" + cylinder +
                ", engine_size=" + engine_size +
                ", transmission='" + transmission + '\'' +
                ", drive_wheels='" + drive_wheels + '\'' +
                ", price=" + price +
                ", fuel_Type='" + fuel_Type + '\'' +
                '}';
    }
}
