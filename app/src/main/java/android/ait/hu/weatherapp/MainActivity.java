package android.ait.hu.weatherapp;

import android.ait.hu.weatherapp.adapter.WeatherRecyclerAdapter;
import android.ait.hu.weatherapp.data.City;
import android.ait.hu.weatherapp.fragments.FragmentCreate;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity implements IMainFragmentHandler {

    static final String API_ID = "af2f2ae37d9e934a4be1e4230e779a8b";
    public static final String PASS_DETAILS = "PASS_DETAILS";
    private DrawerLayout drawerLayout;
    private CoordinatorLayout layoutContent;
    private WeatherRecyclerAdapter adapter;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<City> cityList = City.listAll(City.class);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        adapter = new WeatherRecyclerAdapter(this, cityList);

        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager mLayoutManager =
                new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

        layoutContent = (CoordinatorLayout) findViewById(
                R.id.layoutContent);

        FloatingActionButton fabAdd = (FloatingActionButton) findViewById(R.id.btnAdd);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FragmentCreate().show(getSupportFragmentManager(), FragmentCreate.TAG);
            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        switch (menuItem.getItemId()) {
                            case R.id.action_about:
                                showSnackBarMessage(getString(R.string.snkbr_about));
                                drawerLayout.closeDrawer(GravityCompat.START);
                                break;
                            case R.id.action_add:
                                new FragmentCreate().show(getSupportFragmentManager(), FragmentCreate.TAG);
                                showSnackBarMessage(getString(R.string.snkbr_add));
                                drawerLayout.closeDrawer(GravityCompat.START);
                                break;
                        }
                        return false;
                    }
                });
        setUpToolBar();
    }

    private void setUpToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //toolbar.setNavigationIcon(R.drawable.shoprite);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }

    private void showSnackBarMessage(String message) {
        Snackbar.make(layoutContent,
                message,
                Snackbar.LENGTH_LONG
        ).setAction(R.string.action_cancel, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //...
            }
        }).show();
    }

    @Override
    public void addCity(String city) {
        City newCity = new City(city);
        adapter.addCity(newCity);
    }

    public void showDetailActivity(City city){
        Intent details = new Intent(MainActivity.this, WeatherDetailsActivity.class);
        details.putExtra(PASS_DETAILS, city.getCity());
        startActivity(details);
    }


}
