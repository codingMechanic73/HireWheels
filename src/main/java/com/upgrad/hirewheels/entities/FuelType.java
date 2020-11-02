package com.upgrad.hirewheels.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class FuelType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int fuelTypeId;

    @Column(length = 50, unique = true)
    private String fuelType;

    @OneToMany(mappedBy = "fuelType", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Vehicle> vehicles;

    public int getFuelTypeId() {
        return fuelTypeId;
    }

    public void setFuelTypeId(int fuelTypeId) {
        this.fuelTypeId = fuelTypeId;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    @Override
    public String toString() {
        return "FuelType{" +
                "fuelTypeId=" + fuelTypeId +
                ", fuelType='" + fuelType + '\'' +
                '}';
    }
}
