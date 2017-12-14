package com.team.killskills.nukvoy_android;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListPopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.core.Base64Variant;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.team.killskills.nukvoy_android.handlers.DBClient;
import com.team.killskills.nukvoy_android.model.Airport;
import com.team.killskills.nukvoy_android.model.InnerJoin;
import com.team.killskills.nukvoy_android.model.InputRoute;
import com.team.killskills.nukvoy_android.model.Inputs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class AirportDetailsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final String TAG = AirportDetailsActivity.class.getSimpleName();
    private DBClient db;
    private Airport airport;
    private TextView tvAirportName, tvAirportRegion;
    private SupportMapFragment mapFragment;
    private Inputs inputs;
    //private UserInputs userInputs;
    //private List<String> iataCodeList;
    private Button btnChoices;
    private Button btnGo;
    private ArrayList<Inputs> myGlobalArray;

    //TODO
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airport_details);
        fetchExtras();
        init();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initViews();
        initMap();
        renderHeaders();
    }

    private void fetchExtras() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            airport = (Airport) extras.getSerializable("airport");
        }
    }

    private void init() {
        db = new DBClient(this);
    }

    void run(ArrayList<Inputs> myGlobalArray ) throws IOException {

        /*myGlobalArray = ((AirportApplication)getApplicationContext()).myGlobalArray;
        for (Inputs inputs : myGlobalArray) {
            Logger.logInfo(TAG, inputs.getIataCode().toString());
        }*/
        OkHttpClient client = new OkHttpClient();
        //firstCity = "MADRID";
        String startDate = "2017-02-03";
        List<String> daysArr = new ArrayList<String>();
        List<String> citiesArr = new ArrayList<String>();
        for(Inputs inputs : myGlobalArray){
            daysArr.add(inputs.getDays());
            citiesArr.add(inputs.getIataCode());
        }
        String daysStr = daysArr.toString().replace("['","");
        daysStr.replace("']","");
        daysStr.replace("','","-");

        String citiesStr = citiesArr.toString().replace("['","");
        citiesStr.replace("']","");
        citiesStr.replace("','","-");

        startDate = "2017-01-01";
        String url = "https://nameless-beach-74913.herokuapp.com/planner/startDate/2017-02-03/cities/MAD-BVA-LIS/days/2-3-5/routes/MAD-BVA,MAD-LIS,BVA-LIS,BVA-MAD,LIS-BVA,LIS-MAD";
        //String url =  "https://nameless-beach-74913.herokuapp.com/planner/startDate/"+startDate+"/cities/"+citiesStr+"/days/"+daysStr+"/routes/LIS-BVA,LIS-BVA,MAD-LIS,MAD-BVA,BVA-LIS,BVA-MAD";
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String myResponse = response.body().string();

                AirportDetailsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(AirportDetailsActivity.this,
                                "Your Message"+myResponse, Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

    }

    private void initViews() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Details");
        tvAirportName = findViewById(R.id.tvAirportName);
        tvAirportRegion = findViewById(R.id.tvAirportRegion);
        btnChoices = findViewById(R.id.btnChoices);
        btnGo = findViewById(R.id.btnGo);
        myGlobalArray = ((AirportApplication)getApplicationContext()).myGlobalArray;
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*new AsyncTask<Void, Void, Void>(){

                    @Override
                    protected Void doInBackground(Void... voids) {
                        List<InputRoute> inputs = db.getInnerJoin();
                        Toast.makeText(AirportDetailsActivity.this, inputs.toString(), Toast.LENGTH_SHORT).show();
                        for (InputRoute input : inputs){
                            Logger.logInfo(TAG, "Joins isInserted: " + input.getDestination().toString());
                        }
                        return null;
                    }
                };*/
                try {
                    run(myGlobalArray);
                } catch (IOException e) {
                    e.printStackTrace();
        }
            }
        });
        btnChoices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToGlobalList();
                new AsyncTask<Void, Void, Void>(){

                    @Override
                    protected Void doInBackground(Void... voids) {
                        boolean isInserted = db.insertJoins(airport.iataCode);
                        Logger.logInfo(TAG, "Joins isInserted: " + isInserted);
                        return null;
                    }
                };
            }
        });

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
    }



    private void addToGlobalList() {


        myGlobalArray = ((AirportApplication)getApplicationContext()).myGlobalArray;
        myGlobalArray.add(new Inputs("1",airport.iataCode.toString()));
        Logger.logError(TAG,myGlobalArray.toString());
        Toast.makeText(this,airport.name.toString()+ " airport successfully added to your choices", Toast.LENGTH_SHORT).show();

    }

    private void initMap() {
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        final float MAX_ZOOMING_VALUE = 5f;
        /*String strLatLng = airport.latlng.replace("[", "");
        strLatLng = strLatLng.replace("]", "");
        String array[] = strLatLng.split(",");*/
        String strlat = airport.lat;
        String strlon = airport.lon;
        double lat = Double.parseDouble(strlat);
        double lon = Double.parseDouble(strlon);
        LatLng latLng = new LatLng(lat, lon);
        googleMap.getUiSettings().setAllGesturesEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(Boolean.TRUE);
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.addMarker(new MarkerOptions().position(latLng)).setVisible(true);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, MAX_ZOOMING_VALUE));
    }

    private void renderHeaders() {

        tvAirportName.setText(airport.name);
        tvAirportRegion.setText(airport.region);

    }


}