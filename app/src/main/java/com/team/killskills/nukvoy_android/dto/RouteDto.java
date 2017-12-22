package com.team.killskills.nukvoy_android.dto;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Sacha on 13/12/2017.
 */

/*
 LoganSquare claims to be the fastest JSON parsing and serializing library available for Android outperforming GSON and Jackson’s Databind library by 400% or more.
By default, LoganSquare assumes that the JSON field name will match your Java variable’s name unless the “name” parameter has been used in the field’s “@JsonField” annotation
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
