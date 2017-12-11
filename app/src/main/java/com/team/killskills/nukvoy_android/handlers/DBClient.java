package com.team.killskills.nukvoy_android.handlers;

import android.content.Context;

import com.team.killskills.nukvoy_android.Logger;
import com.team.killskills.nukvoy_android.db.AppDatabase;
import com.team.killskills.nukvoy_android.dto.AirportDto;
import com.team.killskills.nukvoy_android.model.Airport;

import java.util.List;

/**
 * Created by CropIn-Shailendra on 11/24/2017.
 */

public class DBClient {
    private static final String TAG = DBClient.class.getSimpleName();
    private Context context;
    private AppDatabase db;

    public DBClient(Context context) {
        this.context = context;
        db = AppDatabase.getInMemoryDatabase(context);
    }


    public List<Airport> getAirportList() {
        return db.airportModel().getAll();
    }


    public boolean insertAirport(List<AirportDto> airportDtoList) {
        for (AirportDto airportDto : airportDtoList) {
            Logger.logInfo(TAG, airportDto.toString());
            db.airportModel().insert(new Airport(airportDto));
        }
        return true;
    }


}
