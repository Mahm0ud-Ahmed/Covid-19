package com.example.covid_19.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.covid_19.R;
import com.example.covid_19.adapter.Keys;
import com.example.covid_19.adapter.MyAdapter;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbar;
    FloatingActionButton actionButton;
    ImageView img_flag;
    TextView tv_todayCases, tv_totalCases, tv_active, tv_critical, tv_recovered, tv_todayDeaths, tv_totalDeaths;
    Bundle bundle = null;
    Keys keys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

        toolbar = findViewById(R.id.details_toolbar);
        collapsingToolbar = findViewById(R.id.collaps_toolbar);
        actionButton = findViewById(R.id.floating);
        img_flag = findViewById(R.id.img_appbar);
        tv_active = findViewById(R.id.tv_details_active);
        tv_critical = findViewById(R.id.tv_details_critical);
        tv_recovered = findViewById(R.id.tv_details_recovered);
        tv_todayCases = findViewById(R.id.tv_details_today_cases);
        tv_todayDeaths = findViewById(R.id.tv_details_today_deaths);
        tv_totalCases = findViewById(R.id.tv_details_total_cases);
        tv_totalDeaths = findViewById(R.id.tv_details_total_deaths);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        keys = new Keys();

        if (bundle == null){
            bundle = getIntent().getExtras();
            getDataFromIntent(bundle);
        }

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String map = bundle.get(keys.getLAT())+","+bundle.get(keys.getLONG());
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:"+map));
                intent.setPackage("com.google.android.apps.maps");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void getDataFromIntent(Bundle bundle) {
        if (bundle != null){
            tv_active.setText("Active: "+bundle.get(keys.getACTIVE()));
            tv_critical.setText("Critical: "+bundle.get(keys.getCRITICAL()));
            tv_recovered.setText("Recovered: "+bundle.get(keys.getRECOVERED()));
            tv_todayCases.setText("Today Cases: "+bundle.get(keys.getTODAY_CASES()));
            tv_todayDeaths.setText("Today Deaths: "+bundle.get(keys.getTODAY_DEATHS()));
            tv_totalCases.setText("Total Cases: "+bundle.get(keys.getTOTAL_CASES()));
            tv_totalDeaths.setText("Total Deaths: "+bundle.get(keys.getTOTAL_DEATHS()));
            collapsingToolbar.setTitle(bundle.getString(keys.getCOUNTRY()));
            String flag = bundle.getString(keys.getFLAG());

            Picasso.get().load(flag).into(img_flag);

        }

    }

}