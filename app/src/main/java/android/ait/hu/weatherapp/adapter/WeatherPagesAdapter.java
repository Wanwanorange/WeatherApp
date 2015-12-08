package android.ait.hu.weatherapp.adapter;

import android.ait.hu.weatherapp.data.WeatherResult;
import android.ait.hu.weatherapp.fragments.FragmentWeatherDetails;
import android.ait.hu.weatherapp.fragments.FragmentWeatherMain;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Wanchen on 12/2/2015.
 */
public class WeatherPagesAdapter extends FragmentPagerAdapter {
    int numTabs;

    public WeatherPagesAdapter(FragmentManager fragmentManager, int numTabs)
        {super(fragmentManager);
        this.numTabs = numTabs;
        }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "General Info";
            case 1:
                return "Details";
            default:
                return "General Info";
        }
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentWeatherMain();
            case 1:
                return new FragmentWeatherDetails();
            default:
                return new FragmentWeatherMain();
        }
    }

    @Override
    public int getCount() {
        return numTabs;
    }


}
