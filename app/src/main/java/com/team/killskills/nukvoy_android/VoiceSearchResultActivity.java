package com.team.killskills.nukvoy_android;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Filter;
import android.widget.Toast;

import com.team.killskills.nukvoy_android.model.Airport;

import java.util.ArrayList;
import java.util.List;

public class VoiceSearchResultActivity extends AppCompatActivity implements AirportAdapter.ClickListener {
    private static final String TAG = VoiceSearchResultActivity.class.getSimpleName();
    private AirportAdapter adapter;
    private List<Airport> airportList;
    private String query;


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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_search_result);
        fetchExtras();
        initRecyclerView();
        filterResult();
    }

    private void initRecyclerView() {

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView rvAirport = findViewById(R.id.rvAirport);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvAirport.setLayoutManager(mLayoutManager);
        rvAirport.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.space));
        rvAirport.addItemDecoration(dividerItemDecoration);

        adapter = new AirportAdapter(this, airportList);
        rvAirport.setAdapter(adapter);
    }

    private void fetchExtras() {
        airportList = (ArrayList<Airport>) getIntent().getSerializableExtra("list");
        query = getIntent().getStringExtra("query");
    }

    protected void filterResult() {
        adapter.getFilter().filter(query, new Filter.FilterListener() {
            @Override
            public void onFilterComplete(int count) {
                int totalItems = adapter.getItemCount();
                if (totalItems == 0) {
                    finish();
                    Toast.makeText(VoiceSearchResultActivity.this, "No result found!", Toast.LENGTH_SHORT).show();
                } else if (totalItems == 1) {
                    onClick(adapter.getItem(0));
                    finish();
                }
            }
        });
    }

    @Override
    public void onClick(Airport airport) {
        Intent intent = new Intent(this, AirportDetailsActivity.class);
        intent.putExtra("airport", airport);
        startActivity(intent);
    }
}