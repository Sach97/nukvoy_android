package com.team.killskills.nukvoy_android.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.team.killskills.nukvoy_android.dto.RouteDto;

import java.io.Serializable;


@Entity(tableName = "innerjoin")
public class InnerJoin implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private long id;
    @NonNull
    private String iataCode;

    @NonNull
    public long getId() {
        return id;
    }

    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(@NonNull String iataCode) {
        this.iataCode = iataCode;
    }

    public void setId(@NonNull long id) {
        this.id = id;
    }

    public InnerJoin(@NonNull String IataCode){
        iataCode = IataCode;
    }
    public InnerJoin(){}
}