package android.ait.hu.weatherapp.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Wanchen on 12/4/2015.
 */

public class Coord implements Serializable {
    @SerializedName("lon")
    @Expose
    private Double lon;
    @SerializedName("lat")
    @Expose
    private Double lat;

    /**
     *
     * @return
     * The lon
     */
    public Double getLon() {
        return lon;
    }

    /**
     *
     * @param lon
     * The lon
     */
    public void setLon(Double lon) {
        this.lon = lon;
    }

    /**
     *
     * @return
     * The lat
     */
    public Double getLat() {
        return lat;
    }

    /**
     *
     * @param lat
     * The lat
     */
    public void setLat(Double lat) {
        this.lat = lat;
    }

}

