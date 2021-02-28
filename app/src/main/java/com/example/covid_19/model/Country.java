package com.example.covid_19.model;

import com.google.gson.annotations.SerializedName;

public class Country{

    @SerializedName("cases")
    private int totalCases;
    @SerializedName("deaths")
    private int totalDeaths;
    @SerializedName("recovered")
    private int recovered;
    @SerializedName("critical")
    private int critical;
    @SerializedName("active")
    private int active;
    @SerializedName("country")
    private String nameCountry;
    @SerializedName("todayCases")
    private int todayCases;
    @SerializedName("todayDeaths")
    private int todayDeaths;
    @SerializedName("updated")
    private long date;

    private CountryInfo countryInfo;

    public int getTotalCases() {
        return totalCases;
    }

    public int getTotalDeaths() {
        return totalDeaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public int getCritical() {
        return critical;
    }

    public int getActive() {
        return active;
    }

    public String getNameCountry() {
        return nameCountry;
    }

    public int getTodayCases() {
        return todayCases;
    }

    public int getTodayDeaths() {
        return todayDeaths;
    }

    public long getDate() {
        return date;
    }

    public CountryInfo getCountryInfo() {
        return countryInfo;
    }
}
