package com.upgrad.hirewheels.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class VehicleCategory {

    @Id
    @GeneratedValue
    private int vehicleCategoryId;

    @Column(length = 50, unique = true)
    private String vehicleCategoryName;

    @OneToMany(mappedBy = "vehicleCategory", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<VehicleSubcategory> vehicleSubcategories;


    public VehicleCategory(int vehicleCategoryId, String vehicleCategoryName) {
        this.vehicleCategoryId = vehicleCategoryId;
        this.vehicleCategoryName = vehicleCategoryName;
    }

    public VehicleCategory() {

    }

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