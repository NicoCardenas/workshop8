package edu.eci.models;

import java.io.Serializable;
import java.util.UUID;

public class Car implements Serializable{

    private String licencePlate;
    private String brand;
    private UUID id;

    public Car(String licencePlate, String brand, UUID id) {
        this.licencePlate = licencePlate;
        this.brand = brand;
        this.id = id;
    }    

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    
}
