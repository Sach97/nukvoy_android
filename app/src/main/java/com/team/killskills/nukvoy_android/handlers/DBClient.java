package com.team.killskills.nukvoy_android.handlers;

import android.content.Context;

import com.team.killskills.nukvoy_android.Logger;
import com.team.killskills.nukvoy_android.db.AppDatabase;
import com.team.killskills.nukvoy_android.dto.AirportDto;
import com.team.killskills.nukvoy_android.dto.RouteDto;
import com.team.killskills.nukvoy_android.model.Airport;
import com.team.killskills.nukvoy_android.model.InnerJoin;
import com.team.killskills.nukvoy_android.model.InputRoute;
import com.team.killskills.nukvoy_android.model.Route;

import java.util.List;

public class DBClient {
    private static final String TAG = DBClient.class.getSimpleName();
    private Context context;
    private AppDatabase db;
    private Route route;
    private Airport airport;

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
            insertRoutes(airportDto);
        }
        return true;
    }

    private boolean insertRoutes(AirportDto airportDto) {
        for (RouteDto routeDto : airportDto.routes){
                db.routeModel().insert(new Route(airportDto.iataCode, routeDto));
        }
        return true;
    }

    public List<InputRoute> getInnerJoin(){
        return db.innerjoinModel().getInnerJoin();

    }

    public boolean insertJoins(String iataCode) {
        db.innerjoinModel().insert(new InnerJoin(iataCode));
        return true;
    }

}
