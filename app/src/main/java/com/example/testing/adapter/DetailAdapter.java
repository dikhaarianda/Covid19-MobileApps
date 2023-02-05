package com.example.testing.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testing.R;
import com.example.testing.model.CountriesModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> implements Filterable {

    private List<CountriesModel> list;
    private Context context;
    private List<CountriesModel> orig;

    public DetailAdapter(Context context, List<CountriesModel> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Picasso.get()
                .load( list.get(position).getCountryInfo().getFlag() )
                .fit().centerCrop()
                .into(holder.flag);

        holder.countries.setText(list.get(position).getCountry());
        holder.cases.setText(String.valueOf(list.get(position).getCases()));
        holder.death.setText(String.valueOf(list.get(position).getDeaths()));
        holder.recovered.setText(String.valueOf(list.get(position).getRecovered()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView flag;
        public TextView countries;
        public TextView cases;
        public TextView death;
        public TextView recovered;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            flag = itemView.findViewById(R.id.imageView);
            countries = itemView.findViewById(R.id.txt_countries);
            cases = itemView.findViewById(R.id.txt_cases);
            death = itemView.findViewById(R.id.txt_death);
            recovered = itemView.findViewById(R.id.txt_recovered);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                final FilterResults oReturn = new FilterResults();
                final List<CountriesModel> result = new ArrayList<CountriesModel>();
                if (orig == null){
                    orig = list;
                }
                if (charSequence != null){
                    if (orig != null && orig.size() > 0){
                        for (final CountriesModel g : orig){
                            if (g.getCountry().toLowerCase()
                                    .contains(charSequence.toString().toLowerCase()))
                                result.add(g);
                        }
                    }
                    oReturn.values = result;
                }
                return oReturn;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                list = (List<CountriesModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
