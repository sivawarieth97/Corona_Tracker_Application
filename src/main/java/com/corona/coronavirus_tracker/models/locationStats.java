package com.corona.coronavirus_tracker.models;

public class locationStats {
    public String state;
    private String Country;
    private int latestTotalCases;
    private int change;

    public int getChange() {
        return change;
    }

    public void setChange(int change) {
        this.change = change;
    }

    public String getState(String s) {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public int getLatestTotalCases() {
        return latestTotalCases;
    }

    public void setLatestTotalCases(int latestTotalCases) {
        this.latestTotalCases = latestTotalCases;
    }

    @Override
    public String toString() {
        return "locationStats{" +
                "state='" + state + '\'' +
                ", Country='" + Country + '\'' +
                ", latestTotalCases=" + latestTotalCases +
                '}';
    }
}
