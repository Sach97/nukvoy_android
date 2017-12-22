package com.team.killskills.nukvoy_android.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.team.killskills.nukvoy_android.dto.RouteDto;

//Simple model
public class InputRoute {
    private String iataCode;
    private String destination;

    public InputRoute() {
    }

    //Constructor
    public InputRoute(String iataCode, String destination){
        this.iataCode = iataCode;
        this.destination = destination;
    }

    //Getter and Setters
    public String getIataCode() {
        return iataCode;
    }

    public String getDestination() {
        return destination;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
