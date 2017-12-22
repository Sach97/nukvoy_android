package com.team.killskills.nukvoy_android;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.team.killskills.nukvoy_android.model.Airport;

import java.util.ArrayList;
import java.util.List;


/*
Class responsible for associating the list of content/objects with the corresponding view.
Where each object in the list will become an item in the list. This is also where you define whether an item should be displayed or not.
* */
public class AirportAdapter extends RecyclerView.Adapter<AirportAdapter.MyViewHolder> implements Filterable {

    interface ClickListener {
        void onClick(Airport airport);
    }

    private Context context;
    private List<Airport> airportList;
    private List<Airport> filteredAirportList;
    private ClickListener clickListener;

    /*
    * It is the reference to the view that is the visual part of each list item,
    * which will be replicated to all elements (In the structure above, it would be inside the Adapter)
    * */

    public class MyViewHolder extends RecyclerView.ViewHolder {
        View rootView;
        TextView tvAirportName, tvAirportRegion;

        public MyViewHolder(View view) {
            super(view);
            rootView = view;
            tvAirportName = view.findViewById(R.id.tvAirportName);
            tvAirportRegion = view.findViewById(R.id.tvAirportRegion);
        }
    }

    //Constructor
    public AirportAdapter(Context context, List<Airport> airportList) {
        this.context = context;
        this.airportList = airportList;
        this.filteredAirportList = airportList;
        clickListener = (ClickListener) context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_country, parent, false);

        return new MyViewHolder(itemView);
    }

    /*
    * Called by RecyclerView to display the data at the specified position.
    * */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Airport airport = filteredAirportList.get(position);
        holder.tvAirportName.setText(airport.name);
        holder.tvAirportRegion.setText(airport.region);

        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onClick(airport);
            }
        });
    }


    public Airport getItem(int position) {
        return filteredAirportList.get(position);
    }


    @Override
    public int getItemCount() { //Returns the total number of items in the data set held by the adapter.
        return filteredAirportList.size();
    }


    /*
    * A filter constrains data with a filtering pattern.
    * Filtering operations performed by calling filter(CharSequence) or filter(CharSequence, android.widget.Filter.FilterListener) are performed asynchronously.
    * When these methods are called, a filtering request is posted in a request queue and processed later.
    * Any call to one of these methods will cancel any previous non-executed filtering request.
    * */
    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) { //Invoked in a worker thread to filter the data according to the constraint

                String charString = charSequence.toString().toLowerCase();

                if (charString.isEmpty()) {

                    filteredAirportList = airportList;
                } else {
                    List<Airport> filteredList = new ArrayList<>();
                    for (Airport airport : airportList) {
                        //This if statement is responsible of our Autocomplete on two fields
                        if (airport.name.toLowerCase().contains(charString)
                                || airport.name.toLowerCase().contains(charString)
                                || airport.region.toLowerCase().contains(charString)) {

                            filteredList.add(airport);
                        }
                    }

                    filteredAirportList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredAirportList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) { //Invoked in the UI thread to publish the filtering results in the user interface.
                filteredAirportList = (List<Airport>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}