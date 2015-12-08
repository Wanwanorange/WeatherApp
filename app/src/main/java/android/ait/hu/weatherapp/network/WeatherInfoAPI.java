package android.ait.hu.weatherapp.network;


import android.ait.hu.weatherapp.data.City;
import android.ait.hu.weatherapp.data.WeatherResult;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Wanchen on 11/30/2015.
 */
public interface WeatherInfoAPI {
    @GET("/data/2.5/weather?units=metric&appid=af2f2ae37d9e934a4be1e4230e779a8b&")

    void showWeatherDetails(
            @Query("q") String city,
            Callback<WeatherResult> callback);
}
