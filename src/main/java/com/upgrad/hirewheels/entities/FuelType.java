package com.upgrad.hirewheels.entities;

import javax.persistence.*;

@Entity
@Table
public class FuelType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int fuelTypeId;

    @Column(length = 50, unique = true, nullable = false)
    private String fuelType;

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
