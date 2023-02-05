package com.example.testing.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountryInfo {

    @SerializedName("flag")
    @Expose
    private String flag;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "CountryInfo{" +
                "flag='" + flag + '\'' +
                '}';
    }
}
