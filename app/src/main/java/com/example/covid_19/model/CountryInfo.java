package com.example.covid_19.model;

import com.google.gson.annotations.SerializedName;

public class CountryInfo{

    @SerializedName("lat")
    private float latitude;
    @SerializedName("long")
    private float longitude;
    @SerializedName("flag")
    private String flag;

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public String getFlag() {
        return flag;
    }
}
