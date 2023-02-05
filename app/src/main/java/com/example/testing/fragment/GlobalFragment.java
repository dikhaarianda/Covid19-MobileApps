package com.example.testing.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.graphics.Color;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.testing.API.ApiService;
import com.example.testing.R;
import com.example.testing.model.PieChartModel;
import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import androidx.fragment.app.Fragment;

public class GlobalFragment extends Fragment  implements Callback<PieChartModel> {

    private ProgressBar loading;
    private TextView cases;
    private TextView death;
    private TextView recovered;
    private PieChart pieChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_global, container, false);

        loading = view.findViewById(R.id.loading);
        cases = view.findViewById(R.id.txtCases);
        death = view.findViewById(R.id.txtDeath);
        recovered = view.findViewById(R.id.txtRecovered);
        pieChart = view.findViewById(R.id.piechart);

        ApiService.endPoint().getCov19Worldwide().enqueue(this);

        return view;
    }

    @Override
    public void onResponse(Call<PieChartModel> call, Response<PieChartModel> response) {
        loading.setVisibility(View.GONE);
        PieChartModel results = response.body();
        PieChart(results);
    }

    @Override
    public void onFailure(Call<PieChartModel> call, Throwable t) {
        Toast.makeText(getActivity(), t.getMessage(),Toast.LENGTH_LONG).show();
    }

    private void PieChart(PieChartModel results){
        cases.setText(results.getCases().toString());
        death.setText(results.getDeaths().toString());
        recovered.setText(results.getRecovered().toString());

        pieChart.addPieSlice(
                new PieModel(
                        "Cases",
                        results.getCases(),
                        Color.parseColor("#FFA726")));
        pieChart.addPieSlice(
                new PieModel(
                        "Recovered",
                        results.getRecovered(),
                        Color.parseColor("#66BB6A")));
        pieChart.addPieSlice(
                new PieModel(
                        "Death",
                        results.getDeaths(),
                        Color.parseColor("#EF5350")));
        pieChart.startAnimation();
    }
}


