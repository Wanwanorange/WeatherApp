package android.ait.hu.weatherapp.adapter;


import android.ait.hu.weatherapp.MainActivity;
import android.ait.hu.weatherapp.R;
import android.ait.hu.weatherapp.WeatherDetailsActivity;
import android.ait.hu.weatherapp.data.City;
import android.ait.hu.weatherapp.data.WeatherResult;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Wanchen on 11/29/2015.
 */
public class WeatherRecyclerAdapter extends RecyclerView.Adapter<WeatherRecyclerAdapter.ViewHolder> {

    public static final int REQUEST_DETAILS = 101;
    private List<City> cities;
    public Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvCity;
        public Button btnDelete;
        public CardView card_view;

        public ViewHolder(View view){
            super(view);
            tvCity = (TextView) view.findViewById(R.id.tvCity);
            btnDelete = (Button) view.findViewById(R.id.btnDelete);
            card_view = (CardView) view.findViewById(R.id.card_view);
        }
    }


    public WeatherRecyclerAdapter(Context context, List<City> cities) {
        this.context = context;
        this.cities = cities;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_city, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        final City city = cities.get(position);
        viewHolder.tvCity.setText(city.getCity());

        viewHolder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Touched " + viewHolder.tvCity.getText(), Toast.LENGTH_SHORT).show();
                //start new intent to open 2nd activity
                ((MainActivity)context).showDetailActivity(city);
            }
        });

        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeCity(position);
            }
        });

        //include delete
    }


    @Override
    public int getItemCount() {
        return cities.size();
    }

    public void addCity(City city){
        city.save();
        cities.add(city);
        notifyDataSetChanged();
    }

    public void removeCity(int index){
        cities.get(index).delete();
        cities.remove(index);
        notifyDataSetChanged();
    }
}
