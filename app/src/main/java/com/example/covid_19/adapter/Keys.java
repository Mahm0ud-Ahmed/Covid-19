package com.example.covid_19.adapter;


import android.os.Parcel;
import android.os.Parcelable;

public class Keys implements Parcelable {

    public String COUNTRY = "country";
    public String TODAY_CASES = "todayCases";
    public String TODAY_DEATHS = "todayDeaths";
    public String TOTAL_CASES = "totalCases";
    public String TOTAL_DEATHS = "totalDeaths";
    public String RECOVERED = "recovered";
    public String CRITICAL = "critical";
    public String DATE = "updated";
    public String ACTIVE = "active";
    public String LAT = "lat";
    public String LONG = "long";
    public String FLAG = "flag";

    public String getCOUNTRY() {
        return COUNTRY;
    }

    public String getTODAY_CASES() {
        return TODAY_CASES;
    }

    public String getTODAY_DEATHS() {
        return TODAY_DEATHS;
    }

    public String getTOTAL_CASES() {
        return TOTAL_CASES;
    }

    public String getTOTAL_DEATHS() {
        return TOTAL_DEATHS;
    }

    public String getRECOVERED() {
        return RECOVERED;
    }

    public String getCRITICAL() {
        return CRITICAL;
    }

    public String getDATE() {
        return DATE;
    }

    public String getACTIVE() {
        return ACTIVE;
    }

    public String getLAT() {
        return LAT;
    }

    public String getLONG() {
        return LONG;
    }

    public String getFLAG() {
        return FLAG;
    }

    public static Creator<Keys> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.COUNTRY);
        dest.writeString(this.TODAY_CASES);
        dest.writeString(this.TODAY_DEATHS);
        dest.writeString(this.TOTAL_CASES);
        dest.writeString(this.TOTAL_DEATHS);
        dest.writeString(this.RECOVERED);
        dest.writeString(this.CRITICAL);
        dest.writeString(this.DATE);
        dest.writeString(this.ACTIVE);
        dest.writeString(this.LAT);
        dest.writeString(this.LONG);
        dest.writeString(this.FLAG);
    }

    public Keys() {
    }

    protected Keys(Parcel in) {
        this.COUNTRY = in.readString();
        this.TODAY_CASES = in.readString();
        this.TODAY_DEATHS = in.readString();
        this.TOTAL_CASES = in.readString();
        this.TOTAL_DEATHS = in.readString();
        this.RECOVERED = in.readString();
        this.CRITICAL = in.readString();
        this.DATE = in.readString();
        this.ACTIVE = in.readString();
        this.LAT = in.readString();
        this.LONG = in.readString();
        this.FLAG = in.readString();
    }

    public static final Parcelable.Creator<Keys> CREATOR = new Parcelable.Creator<Keys>() {
        @Override
        public Keys createFromParcel(Parcel source) {
            return new Keys(source);
        }

        @Override
        public Keys[] newArray(int size) {
            return new Keys[size];
        }
    };
}
