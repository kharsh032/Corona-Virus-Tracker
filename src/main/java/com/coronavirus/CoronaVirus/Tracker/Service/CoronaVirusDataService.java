package com.coronavirus.CoronaVirus.Tracker.Service;

import com.coronavirus.CoronaVirus.Tracker.Model.LocationStat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class CoronaVirusDataService {

    private static final String VIRUS_DATA_URL = "https://covid19.mathdro.id/api/deaths";
    private List<LocationStat> allStats = new ArrayList<>();

    @Autowired
    private RestTemplate restTemplate;

    public List<LocationStat> getAllStats() {
        return allStats;
    }

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *") // Run every hour
    public void fetchVirusData() {
        try {
            LocationStat[] locationStats = restTemplate.getForObject(VIRUS_DATA_URL, LocationStat[].class);
            if (locationStats != null) {
                allStats = Arrays.asList(locationStats);
            }
        } catch (Exception e) {
            log.error("Exception occurred = {}", e);
            throw new RuntimeException("Error occurred while fetching data from tracker");
        }
    }
}
