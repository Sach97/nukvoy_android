package com.team.killskills.nukvoy_android;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import com.team.killskills.nukvoy_android.dto.AirportDto;
import com.team.killskills.nukvoy_android.handlers.DBClient;
import com.team.killskills.nukvoy_android.model.Airport;
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

public class MainActivity extends AppCompatActivity implements AirportAdapter.ClickListener {
    public String url;
    public String firstCity;
    public String satartDate;
    public String cities;
    public String days;
    public String routes;
    private static final String TAG = MainActivity.class.getSimpleName();
    private RestClient restClient;
    private DBClient dbClient;
    private RecyclerView rvAirport;
    private AirportAdapter adapter;
    private ArrayList<Airport> airportList = new ArrayList<>();
    private ArrayList<Inputs> myGlobalArray;
    private SearchView svSearch;
    private ProgressBar pbLoading;
    private LinearLayout llNoData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();
        initViews();

        new pullAirportTask().execute();
    }


    @Override
    protected void onResume(){
        super.onResume();

        myGlobalArray = ((AirportApplication)getApplicationContext()).myGlobalArray;
        for (Inputs inputs : myGlobalArray) {
            Logger.logInfo(TAG, inputs.getIataCode().toString());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        svSearch.setFocusable(false);
        hideSoftKeyboard();
    }

    //Instantiate Database helper class and OkHttp helper class
    private void init() {
        restClient = new RestClient(this);
        dbClient = new DBClient(this);
    }

    //Initiating views
    private void initViews() {
        svSearch = findViewById(R.id.svSearch);
        pbLoading = findViewById(R.id.pbLoading);
        llNoData = findViewById(R.id.llNoData);
        rvAirport = findViewById(R.id.rvAirport);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvAirport.setLayoutManager(mLayoutManager);
        rvAirport.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.space));
        rvAirport.addItemDecoration(dividerItemDecoration);

        adapter = new AirportAdapter(this, airportList); //Initiating the adapter
        rvAirport.setAdapter(adapter);
    }



    private void configureSearchView() {
        svSearch.setVisibility(View.VISIBLE);
        svSearch.setQueryHint(getString(R.string.search_here));

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        if (searchManager != null) {
            svSearch.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        }
        svSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                adapter.getFilter().filter(query); //SearchView filtering method invoked from adapter
                return true;
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
        }

    }


    @Override
    public void onClick(Airport airport) {
        Intent intent = new Intent(this, AirportDetailsActivity.class);
        intent.putExtra("airport", airport); //Passing the extra to the second Activity
        startActivity(intent);
    }

    public void onTryAgainClicked(View view) {
        new pullAirportTask().execute();
    }

    class pullAirportTask extends AsyncTask<Void, Void, Void> {

        private void showProgress() {
            pbLoading.setVisibility(View.VISIBLE);
            rvAirport.setVisibility(View.GONE);
            llNoData.setVisibility(View.GONE);
        }

        private void showNoData() {
            pbLoading.setVisibility(View.GONE);
            rvAirport.setVisibility(View.GONE);
            llNoData.setVisibility(View.VISIBLE);
        }

        private void showData() {
            pbLoading.setVisibility(View.GONE);
            llNoData.setVisibility(View.GONE);
            rvAirport.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgress();
        }

        //Async method to request the API and inserting all of the data inside our local SQL database
        @Override
        protected Void doInBackground(Void... voids) {
            if (restClient.isOnline()) {
                List<AirportDto> airportDtoList = (List<AirportDto>) restClient.get(BuildConfig.url, AirportDto.class, Boolean.TRUE);
                if(airportDtoList!=null) {
                    boolean isInserted = dbClient.insertAirport(airportDtoList);
                    Logger.logInfo(TAG, "isInserted: " + isInserted);
                }
            }
            airportList.clear();
            airportList.addAll(dbClient.getAirportList());

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter.notifyDataSetChanged();

            if (airportList.isEmpty()) {
                showNoData();
            } else {
                showData();
                configureSearchView();
            }
        }
    }

    public void hideSoftKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    //Reseting database to null on each run for better debugging
    @Override
    protected void onDestroy() {
        super.onDestroy();
        restClient = null;
        dbClient = null;
    }
}
