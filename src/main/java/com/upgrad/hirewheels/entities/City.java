package com.upgrad.hirewheels.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class City {

    @Id
    @GeneratedValue
    private int cityId;

    @Column(length = 50)
    private String cityName;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Location> locations;

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}