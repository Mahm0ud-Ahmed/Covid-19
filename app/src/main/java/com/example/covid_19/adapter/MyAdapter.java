package com.example.covid_19.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid_19.R;
import com.example.covid_19.model.Country;
import com.example.covid_19.ui.DetailsActivity;
import com.example.covid_19.ui.DiseaseActivity;
import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements Filterable {

    private List<Country> listCountry;
    private List<Country> listCountryFull;
    private Context context;
    Country country;

    public MyAdapter(Context context, List<Country> listCountry) {
        this.context = context;
        this.listCountry = listCountry;
        listCountryFull = new ArrayList<>(listCountry);
        country = new Country();
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Country> list = new ArrayList<>();
            if (constraint == null || constraint.length() == 0){
             list.addAll(listCountryFull);
            } else {
                String filter = constraint.toString().toLowerCase().trim();
                for (Country country : listCountryFull) {
                    if (country.getNameCountry().toLowerCase().contains(filter)){
                        list.add(country);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = list;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            listCountry.clear();
            listCountry.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        country = listCountry.get(position);
        Date date = new Date(country.getDate());
        String formatDate = dateFormat(date);
        holder.tv_countryName.setText(country.getNameCountry());
        holder.tv_todayCases.setText("Today Cases: " + country.getTodayCases());
        holder.tv_todayDeaths.setText("Today Deaths: " + country.getTodayDeaths());
        holder.tv_date.setText("latest Update: " + formatDate);
        Picasso.get().load(country.getCountryInfo().getFlag()).into(holder.img_flag);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                country = listCountry.get(holder.getAdapterPosition());
                putDataInIntent();

            }
        });
    }

    @Override
    public int getItemCount() {
        return listCountry.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private String dateFormat(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM");
        return dateFormat.format(date);
    }

    private void putDataInIntent() {
        Keys keys = new Keys();
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(keys.getCOUNTRY(), country.getNameCountry());
        intent.putExtra(keys.getTODAY_CASES(), country.getTodayCases());
        intent.putExtra(keys.getTODAY_DEATHS(), country.getTodayDeaths());
        intent.putExtra(keys.getTOTAL_CASES(), country.getTotalCases());
        intent.putExtra(keys.getTOTAL_DEATHS(), country.getTotalDeaths());
        intent.putExtra(keys.getRECOVERED(), country.getRecovered());
        intent.putExtra(keys.getCRITICAL(), country.getCritical());
        intent.putExtra(keys.getDATE(), country.getDate());
        intent.putExtra(keys.getACTIVE(), country.getActive());
        intent.putExtra(keys.getLAT(), country.getCountryInfo().getLatitude());
        intent.putExtra(keys.getLONG(), country.getCountryInfo().getLongitude());
        intent.putExtra(keys.getFLAG(), country.getCountryInfo().getFlag());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        PorterShapeImageView img_flag;
        TextView tv_countryName, tv_todayCases, tv_todayDeaths, tv_date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cav_item);
            img_flag = itemView.findViewById(R.id.img_flag);
            tv_countryName = itemView.findViewById(R.id.tv_country_name);
            tv_todayCases = itemView.findViewById(R.id.tv_cases);
            tv_todayDeaths = itemView.findViewById(R.id.tv_deaths);
            tv_date = itemView.findViewById(R.id.tv_update);
        }
    }

}
