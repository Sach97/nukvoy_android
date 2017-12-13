package com.team.killskills.nukvoy_android.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.team.killskills.nukvoy_android.dto.RouteDto;

/**
 * Created by CropIn-Shailendra on 11/24/2017.
 */

@Entity
public class Route {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @NonNull
    public String iataCode;
    @NonNull
    public String destination;

    public Route() {
    }

    public Route(@NonNull String IataCode, RouteDto routeDto){
        iataCode = IataCode;
        destination = routeDto.destination;
    }
}
