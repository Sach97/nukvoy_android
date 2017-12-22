package com.team.killskills.nukvoy_android.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


import java.io.Serializable;

/*Set the primary key by adding the @PrimaryKey annotation to the correct fields — in our case, this is the ID of the User.*/

@Entity(tableName = "innerjoin")
public class InnerJoin implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private long id;
    @NonNull
    private String iataCode;

    //Getters and Setters

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

    //Constructors
    public InnerJoin(@NonNull String IataCode){
        iataCode = IataCode;
    }
    public InnerJoin(){}
}