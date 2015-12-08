package android.ait.hu.weatherapp.fragments;

import android.ait.hu.weatherapp.R;
import android.ait.hu.weatherapp.data.WeatherResult;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Wanchen on 12/2/2015.
 */
public class FragmentWeatherDetails extends Fragment {

    private TextView tvTempMax;
    private TextView tvTempMin;
    private TextView tvHumidity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_details, container, false);

        tvTempMax = (TextView) rootView.findViewById(R.id.tvTempMax);
        tvTempMin = (TextView) rootView.findViewById(R.id.tvTempMin);
        tvHumidity = (TextView) rootView.findViewById(R.id.tvHumidity);

        return rootView;
    }

    public void setDetails(WeatherResult weatherResult){
        tvTempMax.setText("Max. Temperature: " + weatherResult.getMain().getTempMax() + "°C");
        tvTempMin.setText("Min. Temperature: " + weatherResult.getMain().getTempMin() + "°C");
        tvHumidity.setText("Humidity: " + weatherResult.getMain().getHumidity());
    }
}
