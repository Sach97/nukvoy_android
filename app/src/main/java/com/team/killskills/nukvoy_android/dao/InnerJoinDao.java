package com.team.killskills.nukvoy_android.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.team.killskills.nukvoy_android.model.InnerJoin;
import com.team.killskills.nukvoy_android.model.InputRoute;
import com.team.killskills.nukvoy_android.model.Route;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by Sacha on 14/12/2017.
 */

/*
  Creating Data Access Object
  DAOs are responsible for defining the methods that access the database
  */

@Dao
public interface InnerJoinDao {

    @Query("SELECT r.iataCode,destination FROM Route r LEFT JOIN innerjoin u ON r.iataCode LIKE '%'+u.iataCode+'%' WHERE r.destination IN (SELECT u.iataCode FROM innerjoin u)AND r.iataCode IN (SELECT u.iataCode FROM innerjoin u)")
    List<InputRoute> getInnerJoin();

    @Insert
    void insertAll(InnerJoin... innerjoins);

    @Update
    void updateAll(InnerJoin... innerjoins);

    @Query("SELECT * FROM innerjoin")
    List<InnerJoin> getAll();

    @Delete
    void deleteAll(InnerJoin... innerJoins);

    @Insert(onConflict = REPLACE)
    void insert(InnerJoin innerJoin);
}
