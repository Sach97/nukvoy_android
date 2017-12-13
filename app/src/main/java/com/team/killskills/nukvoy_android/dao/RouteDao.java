package com.team.killskills.nukvoy_android.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.team.killskills.nukvoy_android.model.Airport;
import com.team.killskills.nukvoy_android.model.Route;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by Sacha on 13/12/2017.
 */

@Dao
public interface RouteDao {

    @Query("select * from Route where iataCode = :iataCode")
    List<Route> getRoutecByIataCode(String iataCode);

    @Insert(onConflict = REPLACE)
    void insert(Route route);

}

