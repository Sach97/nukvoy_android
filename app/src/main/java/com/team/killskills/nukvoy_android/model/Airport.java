package com.team.killskills.nukvoy_android.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.team.killskills.nukvoy_android.dto.AirportDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class Airport implements Serializable{
    /*@PrimaryKey(autoGenerate = true)
    public int _id;*/
    @PrimaryKey
    @NonNull
    public String iataCode;
    public String cityCode;
    public String lat;
    public String lon;
    public String countryCode;
    public String name;
    public String region;

    public Airport(){}
    public Airport(AirportDto airportDto) {
        cityCode = airportDto.cityCode;
        name = airportDto.name;
        lat = airportDto.lat;
        lon = airportDto.lon;
        countryCode = airportDto.countryCode;
        iataCode = airportDto.iataCode;
        region = airportDto.region.replace("_"," ");
    }
}
