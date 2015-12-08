package android.ait.hu.weatherapp.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Wanchen on 12/4/2015.
 */

public class Wind implements Serializable {
    @SerializedName("speed")
    @Expose
    private Double speed;
    @SerializedName("deg")
    @Expose
    private Double deg;

    /**
     *
     * @return
     * The speed
     */
    public Double getSpeed() {
        return speed;
    }

    /**
     *
     * @param speed
     * The speed
     */
    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    /**
     *
     * @return
     * The deg
     */
    public Double getDeg() {
        return deg;
    }

    /**
     *
     * @param deg
     * The deg
     */
    public void setDeg(Double deg) {
        this.deg = deg;
    }
}
