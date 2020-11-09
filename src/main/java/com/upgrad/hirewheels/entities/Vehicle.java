package com.upgrad.hirewheels.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Vehicle {

    @Id
    @GeneratedValue
    private int vehicleId;

    @Column(length = 50)
    private String vehicleModel;

    @Column(length = 10)
    private String vehicleNumber;

    @ManyToOne
    @JoinColumn(name = "vehicle_subcategory_id")
    private VehicleSubcategory vehicleSubcategory;

    @Column(length = 50)
    private String color;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "fuel_type_id")
    private FuelType fuelType;

    @Column(length = 1)
    private int availabilityStatus;

    @Column(length = 500)
    private String vehicleImageUrl;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Booking> bookings;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Vehicle() {
    }

    public Vehicle(String vehicleModel, String vehicleNumber, VehicleSubcategory vehicleSubcategory, String color, Location location, FuelType fuelType, int availabilityStatus, String vehicleImageUrl) {
        this.vehicleModel = vehicleModel;
        this.vehicleNumber = vehicleNumber;
        this.vehicleSubcategory = vehicleSubcategory;
        this.color = color;
        this.location = location;
        this.fuelType = fuelType;
        this.availabilityStatus = availabilityStatus;
        this.vehicleImageUrl = vehicleImageUrl;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public VehicleSubcategory getVehicleSubcategory() {
        return vehicleSubcategory;
    }

    public void setVehicleSubcategory(VehicleSubcategory vehicleSubcategory) {
        this.vehicleSubcategory = vehicleSubcategory;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public int getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(int availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public String getVehicleImageUrl() {
        return vehicleImageUrl;
    }

    public void setVehicleImageUrl(String vehicleImageUrl) {
        this.vehicleImageUrl = vehicleImageUrl;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId=" + vehicleId +
                ", vehicleModel='" + vehicleModel + '\'' +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", vehicleSubcategory=" + vehicleSubcategory +
                ", color='" + color + '\'' +
                ", location=" + location +
                ", fuelType=" + fuelType +
                ", availabilityStatus=" + availabilityStatus +
                ", vehicleImageUrl='" + vehicleImageUrl + '\'' +
                '}';
    }
}
