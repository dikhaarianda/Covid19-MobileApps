package com.example.testing.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;


import com.example.testing.API.ApiService;
import com.example.testing.DetailActivity;
import com.example.testing.R;
import com.example.testing.adapter.HomeAdapter;
import com.example.testing.model.ContinentModel;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements View.OnClickListener,
        SearchView.OnQueryTextListener, AdapterView.OnItemClickListener,
        Callback<List<ContinentModel>> {

    private List<ContinentModel> results = null;
    private Comparator<ContinentModel> comparator;
    private HomeAdapter adapter = null;
    private ListView listView;
    private ImageButton button;
    private ProgressBar loading;
    private SearchView searchView;
    private boolean isSorted = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        listView = view.findViewById(R.id.listview);
        loading = view.findViewById(R.id.loading);
        button = view.findViewById(R.id.btSort);
        searchView = view.findViewById(R.id.idSearch);

        ApiService.endPoint().getContinents().enqueue(this);
        comparator = comparator();
        button.setOnClickListener(this);
        searchView.setOnQueryTextListener(this);
        listView.setOnItemClickListener(this);

        return view;
    }

    private void showListview(){
        adapter = new HomeAdapter(getActivity(), results);
        listView.setAdapter(adapter);
    }

    private Comparator comparator(){
        return new Comparator<ContinentModel>() {
            @Override
            public int compare(ContinentModel t1, ContinentModel t2) {
                return t1.getContinent().compareTo(t2.getContinent());
            }
        };
    }

    @Override
    public void onResponse(Call<List<ContinentModel>> call, Response<List<ContinentModel>> response) {
        loading.setVisibility(View.GONE);
        results = response.body();
        Collections.sort(results, comparator);
        showListview();
    }

    @Override
    public void onFailure(Call<List<ContinentModel>> call, Throwable t) {
        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        if (isSorted){
            Collections.sort(results, Collections.reverseOrder(comparator));
        }
        else {
            Collections.sort(results, comparator);
        }
        isSorted = !isSorted;
        showListview();
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.getFilter().filter(s);
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        final String continent = results.get(i).getContinent();
      final List<String>countries = results.get(i).getCountries();

        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("continentTAG", continent);
        intent.putExtra("countriesTAG", String.valueOf(countries));
        startActivity(intent);
    }
}