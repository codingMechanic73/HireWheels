package com.upgrad.hirewheels.entities;

import javax.persistence.*;

@Entity
@Table
public class VehicleCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int vehicleCategoryId;

    @Column(length = 50, nullable = false, unique = true)
    private String vehicleCategoryName;


    public int getVehicleCategoryId() {
        return vehicleCategoryId;
    }

    public void setVehicleCategoryId(int vehicleCategoryId) {
        this.vehicleCategoryId = vehicleCategoryId;
    }

    public String getVehicleCategoryName() {
        return vehicleCategoryName;
    }

    public void setVehicleCategoryName(String vehicleCategoryName) {
        this.vehicleCategoryName = vehicleCategoryName;
    }

    @Override
    public String toString() {
        return "VehicleCategory{" +
                "vehicleCategoryId=" + vehicleCategoryId +
                ", vehicleCategoryName='" + vehicleCategoryName + '\'' +
                '}';
    }
}