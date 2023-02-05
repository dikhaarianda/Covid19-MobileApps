package com.example.testing.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.testing.R;
import com.example.testing.model.ContinentModel;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends BaseAdapter implements Filterable {

    private final Context context;
    private List<ContinentModel> continentList;
    private List<ContinentModel> orig;

    public HomeAdapter(Context context, List<ContinentModel> continentList) {
        this.context = context;
        this.continentList = continentList;
    }

    public Filter getFilter(){
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                final FilterResults oReturn = new FilterResults();
                final List<ContinentModel> result = new ArrayList<ContinentModel>();
                if (orig == null){
                    orig = continentList;
                }
                if (charSequence != null){
                    if (orig != null && orig.size() > 0){
                        for (final ContinentModel g : orig){
                            if (g.getContinent().toLowerCase()
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
                continentList = (List<ContinentModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public int getCount() {
        return continentList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if (view == null){
            holder = new ViewHolder();

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item1, null, true);

            holder.continent = (TextView) view.findViewById(R.id.txt_continent);
            holder.cases = (TextView) view.findViewById(R.id.txt_cases);
            holder.death = (TextView) view.findViewById(R.id.txt_death);
            holder.recovered = (TextView) view.findViewById(R.id.txt_recovered);

            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }
        holder.continent.setText(continentList.get(i).getContinent());
        holder.cases.setText(String.valueOf(continentList.get(i).getCases()));
        holder.death.setText(String.valueOf(continentList.get(i).getDeaths()));
        holder.recovered.setText(String.valueOf(continentList.get(i).getRecovered()));
        return view;
    }

    private class ViewHolder {
        protected TextView continent;
        protected TextView cases;
        protected TextView death;
        protected TextView recovered;
    }
}