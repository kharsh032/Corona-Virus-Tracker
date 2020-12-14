package com.coronavirus.CoronaVirus.Tracker.Model;

public class LocationStat {
    private String state;
    private String country;
    private int latestTotalCases;
    private int recoveredCases;
    private int currentActiveCases;
    private int deathCases;

    public LocationStat() {
    }

    public LocationStat(String state, String country, int latestTotalCases, int recoveredCases, int currentActiveCases, int deathCases) {
        this.state = state;
        this.country = country;
        this.latestTotalCases = latestTotalCases;
        this.recoveredCases = recoveredCases;
        this.currentActiveCases = currentActiveCases;
        this.deathCases = deathCases;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLatestTotalCases() {
        return latestTotalCases;
    }

    public void setLatestTotalCases(int latestTotalCases) {
        this.latestTotalCases = latestTotalCases;
    }

    public int getRecoveredCases() {
        return recoveredCases;
    }

    public void setRecoveredCases(int recoveredCases) {
        this.recoveredCases = recoveredCases;
    }

    public int getCurrentActiveCases() {
        return currentActiveCases;
    }

    public void setCurrentActiveCases(int currentActiveCases) {
        this.currentActiveCases = currentActiveCases;
    }

    public int getDeathCases() {
        return deathCases;
    }

    public void setDeathCases(int deathCases) {
        this.deathCases = deathCases;
    }
}
