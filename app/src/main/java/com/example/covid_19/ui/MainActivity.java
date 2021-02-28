package com.example.covid_19.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GestureDetectorCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.covid_19.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView crv_cases, crv_disease;
    GestureDetectorCompat detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        crv_cases = findViewById(R.id.crv_cases);
        crv_disease = findViewById(R.id.crv_disease);
        crv_cases.setOnClickListener(this);
        crv_disease.setOnClickListener(this);

        detector = new GestureDetectorCompat(this, new MyGestureDetector());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.crv_cases:
                startActivity(new Intent(this, CasesActivity.class));
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
            case R.id.crv_disease:
                startActivity(new Intent(this, DiseaseActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        detector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            Toast.makeText(getBaseContext(), "onDown", Toast.LENGTH_LONG).show();
            Log.d("Main Activity", "onScroll: "+e.toString());
            return true;
        }



        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Toast.makeText(getBaseContext(), "onScroll", Toast.LENGTH_LONG).show();
            Log.d("Main Activity", "onScroll: "+e1.toString()+"\n"+e2.toString());
            return true;
        }
    }

}