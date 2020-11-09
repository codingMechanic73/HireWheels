package com.upgrad.hirewheels.dto;

import com.fasterxml.jackson.annotation.JsonAlias;


public class VehicleDTO {

    private int vehicleId;
    private String vehicleModel;
    private String vehicleNumber;
    private int vehicleSubCategoryId;
    private String color;
    private int locationId;
    private String fuelType;
    private int fuelTypeId;
    private int availabilityStatus;
    @JsonAlias("carImageUrl")
    private String vehicleImageUrl;

    @Override
    public String toString() {
        return "VehicleDTO{" +
                "vehicleId=" + vehicleId +
                ", vehicleModel='" + vehicleModel + '\'' +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", vehicleSubCategoryId=" + vehicleSubCategoryId +
                ", color='" + color + '\'' +
                ", locationId=" + locationId +
                ", fuelType='" + fuelType + '\'' +
                ", fuelTypeId='" + fuelTypeId + '\'' +
                ", availabilityStatus=" + availabilityStatus +
                ", vehicleImageUrl='" + vehicleImageUrl + '\'' +
                '}';
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

    public int getVehicleSubCategoryId() {
        return vehicleSubCategoryId;
    }

    public void setVehicleSubCategoryId(int vehicleSubCategoryId) {
        this.vehicleSubCategoryId = vehicleSubCategoryId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public int getFuelTypeId() {
        return fuelTypeId;
    }

    public void setFuelTypeId(int fuelTypeId) {
        this.fuelTypeId = fuelTypeId;
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
}
