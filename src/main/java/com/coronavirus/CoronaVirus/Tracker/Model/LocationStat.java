package com.coronavirus.CoronaVirus.Tracker.Model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationStat {
    private String state;
    private String country;
    private int latestTotalCases;
    private int recoveredCases;
    private int currentActiveCases;
    private int deathCases;
}
