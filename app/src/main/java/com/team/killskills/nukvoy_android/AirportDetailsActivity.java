package com.team.killskills.nukvoy_android;

import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.team.killskills.nukvoy_android.handlers.DBClient;
import com.team.killskills.nukvoy_android.model.Airport;

import java.util.List;


/**
 * Created by CropIn-Shailendra on 11/25/2017.
 */

public class AirportDetailsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private DBClient dbClient;
    private Airport airport;
    private TextView tvCountryName, tvCapital;
    private SupportMapFragment mapFragment;

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
        setContentView(R.layout.activity_country_details);
        fetchExtras();
        init();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initViews();
        initMap();
        renderViews();
    }

    private void fetchExtras() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            airport = (Airport) extras.getSerializable("airport");
        }
    }

    private void init() {
        dbClient = new DBClient(this);
    }

    private void initViews() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Details");
        tvCountryName = findViewById(R.id.tvCountryName);
        tvCapital = findViewById(R.id.tvCapitalName);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
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

    private void renderViews() {
        renderHeaders();
    }

    private void renderHeaders() {
        tvCountryName.setText(airport.name);
        tvCapital.setText(airport.region);
    }


}