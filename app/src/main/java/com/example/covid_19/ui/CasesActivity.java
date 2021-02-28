package com.example.covid_19.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.covid_19.R;
import com.example.covid_19.adapter.DataHolder;
import com.example.covid_19.adapter.MyAdapter;
import com.example.covid_19.api.ApiInterface;
import com.example.covid_19.api.CovidBuilder;
import com.example.covid_19.model.Country;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CasesActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    MyAdapter adapter;
    List<Country> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cases);

        toolbar = findViewById(R.id.cases_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupRecyclerView();
        getDataFromApi();

        moveAndSwiped();

    }

    private void moveAndSwiped() {
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT |
                        ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                final int oldIndex = viewHolder.getAdapterPosition();
                final int newIndex = target.getAdapterPosition();
                Collections.swap(list, oldIndex, newIndex);
                adapter.notifyItemMoved(oldIndex, newIndex);
                Snackbar s = Snackbar.make(toolbar, "A successful switch operation", BaseTransientBottomBar.LENGTH_LONG);
                s.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Collections.swap(list, newIndex, oldIndex);
                        adapter.notifyItemMoved(oldIndex, newIndex);
                    }
                });
                s.show();


                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                final int elementIndex = viewHolder.getAdapterPosition();
                final Country country = list.get(elementIndex);
                list.remove(elementIndex);
                adapter.notifyItemRemoved(elementIndex);
                if (!list.get(elementIndex).equals(country)){
                    Snackbar s = Snackbar.make(toolbar, "A successful switch operation", BaseTransientBottomBar.LENGTH_LONG);
                    s.setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            list.add(elementIndex, country);
                            adapter.notifyItemInserted(elementIndex);
                        }
                    });
                    s.show();
                }
            }
        });
        helper.attachToRecyclerView(recyclerView);
    }

    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.rv_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

    }


    private void getDataFromApi() {
        ApiInterface apiInterface = CovidBuilder.getInstanceRetrofit().create(ApiInterface.class);

        Call<List<Country>> call = apiInterface.getCountry();
        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                list = response.body();
                adapter = new MyAdapter(getBaseContext(), list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cases_option_menu, menu);
        MenuItem item = menu.findItem(R.id.search_country);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setQueryHint("Search for Country name");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    private boolean isConnect(){
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo infoWifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo infoMobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (infoMobile != null || infoWifi != null) {
            boolean wifiConnect = infoWifi.isConnected();
            boolean mobileConnect = infoMobile.isConnected();
            return wifiConnect || mobileConnect;
        }
        return false;
    }
}