package com.team.killskills.nukvoy_android.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.team.killskills.nukvoy_android.dto.AirportDto;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by CropIn-Shailendra on 11/24/2017.
 */

@Entity
public class Airport implements Serializable{
    @PrimaryKey
    @NonNull
    public String cityCode;
    public String lat;
    public String lon;
    public String countryCode;
    public String iataCode;
    public String name;
    public String region;
    public String routes;

    public Airport(){}
    public Airport(AirportDto airportDto) {
        cityCode = airportDto.cityCode;
        name = airportDto.name;
        lat = airportDto.lat;
        lon = airportDto.lon;
        countryCode = airportDto.countryCode;
        iataCode = airportDto.iataCode;
        region = airportDto.region;
        routes = Arrays.toString(airportDto.routes);
    }
}