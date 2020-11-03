package com.upgrad.hirewheels.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class VehicleSubcategory {

    @Id
    @GeneratedValue
    private int vehicleSubcategoryId;

    @Column(length = 50, unique = true)
    private String vehicleSubcategoryName;

    @Column(nullable = false)
    private double pricePerDay;

    @ManyToOne
    @JoinColumn(name = "vehicle_category_id")
    private VehicleCategory vehicleCategory;

    @OneToMany(mappedBy = "vehicleSubcategory", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Vehicle> vehicles;

    public VehicleSubcategory(int vehicleSubcategoryId, String vehicleSubcategoryName, double pricePerDay, VehicleCategory vehicleCategory) {
        this.vehicleSubcategoryId = vehicleSubcategoryId;
        this.vehicleSubcategoryName = vehicleSubcategoryName;
        this.pricePerDay = pricePerDay;
        this.vehicleCategory = vehicleCategory;
    }

    public VehicleSubcategory() {
    }

    public int getVehicleSubcategoryId() {
        return vehicleSubcategoryId;
    }

    public void setVehicleSubcategoryId(int vehicleSubcategoryId) {
        this.vehicleSubcategoryId = vehicleSubcategoryId;
    }

    public String getVehicleSubcategoryName() {
        return vehicleSubcategoryName;
    }

    public void setVehicleSubcategoryName(String vehicleSubcategoryName) {
        this.vehicleSubcategoryName = vehicleSubcategoryName;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public VehicleCategory getVehicleCategory() {
        return vehicleCategory;
    }

    public void setVehicleCategory(VehicleCategory vehicleCategory) {
        this.vehicleCategory = vehicleCategory;
    }

    @Override
    public String toString() {
        return "VehicleSubcategory{" +
                "vehicleSubcategoryId=" + vehicleSubcategoryId +
                ", vehicleSubcategoryName='" + vehicleSubcategoryName + '\'' +
                ", pricePerDay=" + pricePerDay +
                ", vehicleCategory=" + vehicleCategory +
                '}';
    }
}