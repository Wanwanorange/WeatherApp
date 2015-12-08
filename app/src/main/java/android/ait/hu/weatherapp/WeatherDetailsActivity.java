package android.ait.hu.weatherapp;

import android.ait.hu.weatherapp.adapter.WeatherPagesAdapter;
import android.ait.hu.weatherapp.data.City;
import android.ait.hu.weatherapp.data.WeatherResult;
import android.ait.hu.weatherapp.fragments.FragmentWeatherDetails;
import android.ait.hu.weatherapp.fragments.FragmentWeatherMain;
import android.ait.hu.weatherapp.network.WeatherInfoAPI;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class WeatherDetailsActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    public static final String SHOW_DETAILS = "SHOW DETAILS";
    LatLng position = new LatLng(0,0);
    String currentCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.title_main_tab));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.title_detail_tab));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new WeatherPagesAdapter(getSupportFragmentManager(), tabLayout.getTabCount()));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        //MAP
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //GET DATA
        currentCity =  getIntent().getStringExtra(MainActivity.PASS_DETAILS);
        getSupportActionBar().setTitle(currentCity);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://api.openweathermap.org/")
                .build();

        WeatherInfoAPI service = restAdapter.create(
                WeatherInfoAPI.class);

        service.showWeatherDetails(currentCity,
                new Callback<WeatherResult>() {
                    @Override
                    public void success(WeatherResult weatherResult,
                                        Response response) {
                        //ADAPTER UPDATES TEXTS
                        setWeatherResults(weatherResult);
                        getSupportActionBar().setTitle(currentCity + ", " + weatherResult.getSys().getCountry());
                        position = new LatLng(weatherResult.getCoord().getLat(),weatherResult.getCoord().getLon());
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
                        mMap.addMarker(
                                new MarkerOptions().position(position).title(currentCity));
                        mMap.animateCamera(CameraUpdateFactory.zoomTo(5.0f));
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(WeatherDetailsActivity.this,
                                "error: " + error.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });


    }


    public void setWeatherResults(WeatherResult weatherResult) {
        String mainFragmentTag = getFragmentTag(R.id.pager, 0); // 0, because it is the index of the first page
        FragmentWeatherMain fragmentMain = (FragmentWeatherMain) getSupportFragmentManager().findFragmentByTag(mainFragmentTag);
        fragmentMain.setMain(weatherResult);

        String detailsFragmentTag = getFragmentTag(R.id.pager, 1); // 1, because it is the index of the second page
        FragmentWeatherDetails fragmentDetails = (FragmentWeatherDetails) getSupportFragmentManager().findFragmentByTag(
                detailsFragmentTag);
        fragmentDetails.setDetails(weatherResult);


    }

    private String getFragmentTag(int viewPagerId, int index) {
        return "android:switcher:" + viewPagerId + ":" + index;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.moveCamera(CameraUpdateFactory.newLatLng(position));

    }

}
