package com.team.killskills.nukvoy_android;

import android.app.Application;

import com.team.killskills.nukvoy_android.model.Inputs;

import java.util.ArrayList;

/**
 * Created by Sacha on 01/11/2017.
 */

public class AirportApplication extends Application {

    public ArrayList<Inputs> myGlobalArray = null;

    public AirportApplication(){
        myGlobalArray = new ArrayList();
    }//AirportApplication constructor



}//AirportApplication
//you can access this ArrayList globally like this ((AirportApplication)getApplicationContext()).myGlobalArray ... etc.

