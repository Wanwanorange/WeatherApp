package android.ait.hu.weatherapp.fragments;

import android.ait.hu.weatherapp.R;
import android.ait.hu.weatherapp.data.WeatherResult;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Wanchen on 12/2/2015.
 */
public class FragmentWeatherMain extends Fragment  {


    private ImageView imageIcon;
    private TextView tvTemp;
    private TextView tvDesc;
    private String link = "http://openweathermap.org/img/w/";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        imageIcon = (ImageView) rootView.findViewById(R.id.imageIcon);
        tvTemp = (TextView) rootView.findViewById(R.id.tvTemp);
        tvDesc = (TextView) rootView.findViewById(R.id.tvDesc);

        return rootView;
    }

    public void setMain(WeatherResult weatherResult){
        tvTemp.setText(weatherResult.getMain().getTemp() + "°C");
        tvDesc.setText("" + weatherResult.getWeather().get(0).getDescription());
        link = link + weatherResult.getWeather().get(0).getIcon() + ".png";
        Glide.with(this).load(link).into(imageIcon);
    }


}
