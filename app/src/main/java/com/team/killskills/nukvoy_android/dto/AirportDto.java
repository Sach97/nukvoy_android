package com.team.killskills.nukvoy_android.dto;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.Arrays;
import java.util.List;

/*
 LoganSquare claims to be the fastest JSON parsing and serializing library available for Android outperforming GSON and Jackson’s Databind library by 400% or more.
By default, LoganSquare assumes that the JSON field name will match your Java variable’s name unless the “name” parameter has been used in the field’s “@JsonField” annotation
*/


@JsonObject(fieldDetectionPolicy = JsonObject.FieldDetectionPolicy.NONPRIVATE_FIELDS_AND_ACCESSORS)
public class AirportDto {
    @JsonField(name = "airports_cityCode") //This is the format of the fields the response of the API
    public String cityCode;
    @JsonField(name = "airports_coordinates_latitude")
    public String lat;
    @JsonField(name = "airports_coordinates_longitude")
    public String lon;
    @JsonField(name = "airports_countryCode")
    public String countryCode;
    @JsonField(name = "airports_iataCode")
    public String iataCode;
    @JsonField(name = "airports_name")
    public String name;
    @JsonField(name = "airports_regionCode")
    public String region;
    public List<RouteDto> routes;


    //toString method for debugging inserting in database
    @Override
    public String toString() {
        return "AirportDto{" +
                "cityCode='" + cityCode + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", iataCode='" + iataCode + '\'' +
                ", name='" + name + '\'' +
                ", region='" + region + '\'' +
                ", routes='" + routes +
                '}';
    }
}
