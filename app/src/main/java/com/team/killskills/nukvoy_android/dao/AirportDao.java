package com.team.killskills.nukvoy_android.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.team.killskills.nukvoy_android.model.Airport;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;


@Dao
public interface AirportDao {

    @Query("select * from Airport order by name asc")
    List<Airport> getAll();

 /*   @Query("select iataCode,routes from Airport where iataCode = :iataCode")
    List<Route> getAirportRoutesByCode(String iataCode);*/


    /*@Query("select * from Airport where name = :name")
    Airport getAirportByName(String name);

    @Query("select routes from Airport where iataCode = :iataCode")
    List<String> getRoutesByCode(String iataCode);*/

    @Insert(onConflict = REPLACE)
    void insert(Airport airport);

}
