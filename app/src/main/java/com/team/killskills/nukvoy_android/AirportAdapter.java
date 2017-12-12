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



public class AirportAdapter extends RecyclerView.Adapter<AirportAdapter.MyViewHolder> implements Filterable {

    interface ClickListener {
        void onClick(Airport airport);
    }

    private Context context;
    private List<Airport> airportList;
    private List<Airport> filteredAirportList;
    private ClickListener clickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        View rootView;
        TextView tvAirportName, tvAirportRegion;
        //ImageView ivFlag;

        public MyViewHolder(View view) {
            super(view);
            rootView = view;
            tvAirportName = view.findViewById(R.id.tvAirportName);
            tvAirportRegion = view.findViewById(R.id.tvAirportRegion);
        }
    }


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

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Airport airport = filteredAirportList.get(position);
        holder.tvAirportName.setText(airport.name);
        holder.tvAirportRegion.setText(airport.region);

        //loadFlag(holder.ivFlag, country.flag);

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
    public int getItemCount() {
        return filteredAirportList.size();
    }


    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString().toLowerCase();

                if (charString.isEmpty()) {

                    filteredAirportList = airportList;
                } else {
                    List<Airport> filteredList = new ArrayList<>();
                    for (Airport airport : airportList) {
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
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredAirportList = (List<Airport>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}