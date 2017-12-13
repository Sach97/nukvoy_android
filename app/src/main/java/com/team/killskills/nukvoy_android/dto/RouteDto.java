package com.team.killskills.nukvoy_android.dto;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Sacha on 13/12/2017.
 */

@JsonObject(fieldDetectionPolicy = JsonObject.FieldDetectionPolicy.NONPRIVATE_FIELDS_AND_ACCESSORS)
public class RouteDto {

    public String iataCode;
    public String destination;

    @Override
    public String toString() {
        return "RouteDto{" +
                ", destination='" + destination + '\'' +
                '}';
    }
}
