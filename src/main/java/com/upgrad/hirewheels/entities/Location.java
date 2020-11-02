package com.upgrad.hirewheels.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Location {

    @Id
    @GeneratedValue
    private int locationId;

    @Column(length = 50)
    private String locationName;

    @Column(length = 100)
    private String address;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @Column(length = 6)
    private String pincode;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Booking> bookings;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Vehicle> vehicles;

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    @Override
    public String toString() {
        return "Location{" +
                "locationId=" + locationId +
                ", locationName='" + locationName + '\'' +
                ", address='" + address + '\'' +
                ", city=" + city +
                ", pincode='" + pincode + '\'' +
                '}';
    }
}