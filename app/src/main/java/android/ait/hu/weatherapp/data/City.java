package android.ait.hu.weatherapp.data;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by Wanchen on 11/30/2015.
 */
public class City extends SugarRecord<City> implements Serializable {
    private String city;

    public City(){}

    public City(String city){
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

