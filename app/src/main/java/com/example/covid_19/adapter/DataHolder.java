package com.example.covid_19.adapter;

import android.content.Context;

import com.example.covid_19.model.Country;

import java.util.List;

public interface DataHolder {
    void data(Context context, List<Country> list);
}
