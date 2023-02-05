package com.example.testing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testing.API.ApiService;
import com.example.testing.adapter.DetailAdapter;
import com.example.testing.model.CountriesModel;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity implements Callback<List<CountriesModel>>,
        View.OnClickListener, SearchView.OnQueryTextListener {

    private List<CountriesModel> results = null;
    private Comparator<CountriesModel> comparator;
    private DetailAdapter adapter = null;
    private TextView textView;
    private ProgressBar loading;
    private RecyclerView recyclerView;
    private ImageButton sort;
    private SearchView searchView;
    private boolean isSorted = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        textView = findViewById(R.id.txtContinent);
        loading = findViewById(R.id.loading);
        recyclerView = findViewById(R.id.cycler);
        searchView = findViewById(R.id.idSearch);
        sort = findViewById(R.id.btSort);

        Intent intent = getIntent();
        String continent = intent.getStringExtra("continentTAG");
        String countries = intent.getStringExtra("countriesTAG");

        textView.setText(continent);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        ApiService.endPoint().getCountries(countries).enqueue(this);
        comparator = comparator();
        sort.setOnClickListener(this);
        searchView.setOnQueryTextListener(this);
    }

    private Comparator<CountriesModel> comparator() {
        return new Comparator<CountriesModel>() {
            @Override
            public int compare(CountriesModel t1, CountriesModel t2) {
                return t1.getCountry().compareTo(t2.getCountry());
            }
        };
    }

    private void showRecyclerview(){
        adapter = new DetailAdapter(this, results);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResponse(Call<List<CountriesModel>> call, Response<List<CountriesModel>> response) {
        loading.setVisibility(View.GONE);
        results = response.body();
        Collections.sort(results,comparator);
        showRecyclerview();
    }

    @Override
    public void onFailure(Call<List<CountriesModel>> call, Throwable t) {
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_LONG).show();
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
        showRecyclerview();
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
}