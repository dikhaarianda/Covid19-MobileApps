package com.example.testing.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContinentModel {

    @SerializedName("cases")
    @Expose
    private Integer cases;
    @SerializedName("deaths")
    @Expose
    private Integer deaths;
    @SerializedName("recovered")
    @Expose
    private Integer recovered;
    @SerializedName("continent")
    @Expose
    private String continent;

    public Integer getCases() {
        return cases;
    }

    public void setCases(Integer cases) {
        this.cases = cases;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    public Integer getRecovered() {
        return recovered;
    }

    public void setRecovered(Integer recovered) {
        this.recovered = recovered;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    @Override
    public String toString() {
        return "ContinentModel{" +
                "cases=" + cases +
                ", deaths=" + deaths +
                ", recovered=" + recovered +
                ", continent='" + continent + '\'' +
                '}';
    }
}
