package com.team.killskills.nukvoy_android.dto;

import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.Arrays;
import java.util.HashMap;


@JsonObject(fieldDetectionPolicy = JsonObject.FieldDetectionPolicy.NONPRIVATE_FIELDS_AND_ACCESSORS)
public class AirportDto {
    public String cityCode;
    public String lat;
    public String lon;
    public String countryCode;
    public String iataCode;
    public String name;
    public String region;
    public String[] routes;


    @Override
    public String toString() {
        return "AirportDto{" +
                "airports_cityCode='" + cityCode + '\'' +
                ", airports_coordinates_latitude='" + lat + '\'' +
                ", airports_coordinates_longitude='" + lon + '\'' +
                ", airports_countryCode='" + countryCode + '\'' +
                ", airports_iataCode='" + iataCode + '\'' +
                ", airports_name='" + name + '\'' +
                ", airports_regionCode='" + region + '\'' +
                ", routes='" + Arrays.toString(routes) + '\'' +
                '}';
    }
}
