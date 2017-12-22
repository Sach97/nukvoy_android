package com.team.killskills.nukvoy_android.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.team.killskills.nukvoy_android.model.Airport;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/*
  Creating Data Access Object
  DAOs are responsible for defining the methods that access the database
  */

@Dao
public interface AirportDao {

    @Query("select * from Airport order by name asc")
    List<Airport> getAll();

    @Insert(onConflict = REPLACE)
    void insert(Airport airport);

}
