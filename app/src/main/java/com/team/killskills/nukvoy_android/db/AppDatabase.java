/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.team.killskills.nukvoy_android.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.team.killskills.nukvoy_android.dao.AirportDao;
import com.team.killskills.nukvoy_android.dao.InnerJoinDao;
import com.team.killskills.nukvoy_android.dao.RouteDao;
import com.team.killskills.nukvoy_android.model.Airport;
import com.team.killskills.nukvoy_android.model.InnerJoin;
import com.team.killskills.nukvoy_android.model.Route;

/*
So far, we have defined our Users table and its corresponding queries,
but we haven’t yet created the database that brings these other pieces of Room together.
To do this, we need to define an abstract class that extends RoomDatabase.
This class is annotated with @Database, lists the entities contained in the database, and the DAOs which access them.
The database version has to be increased by 1, from the initial value, so in our case, it will be 24.*/

@Database(entities = {Airport.class, Route.class, InnerJoin.class}, version = 24,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;
    private static String DATABASE_NAME = "airports.db"; //This is the name of the database as it will be stored in the device

    //This abstract methods are required to for making DAO to work
    public abstract AirportDao airportModel();
    public abstract RouteDao routeModel();
    public abstract InnerJoinDao innerjoinModel();

    public static AppDatabase getInMemoryDatabase(Context context) {
        if (INSTANCE == null) {
            //Builder for RoomDatabase
            INSTANCE =Room.databaseBuilder(context,AppDatabase.class,DATABASE_NAME).fallbackToDestructiveMigration().build();
            //fallbackToDestructiveMigration method Allows Room to destructively recreate database tables if Migrations that would migrate old database schemas to the latest schema version are not found.
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}