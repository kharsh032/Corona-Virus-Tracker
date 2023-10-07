package com.coronavirus.CoronaVirus.Tracker.Model;

@Builder
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
}
