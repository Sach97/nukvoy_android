package com.team.killskills.nukvoy_android.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.team.killskills.nukvoy_android.dto.RouteDto;

public class InputRoute {
    private String iataCode;
    private String destination;

    public InputRoute() {
    }

    public InputRoute(String iataCode, String destination){
        this.iataCode = iataCode;
        this.destination = destination;
    }

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
