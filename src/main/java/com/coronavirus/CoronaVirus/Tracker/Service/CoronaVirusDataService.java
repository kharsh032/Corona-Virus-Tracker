package com.coronavirus.CoronaVirus.Tracker.Service;

import com.coronavirus.CoronaVirus.Tracker.Model.LocationStat;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import sun.net.www.http.HttpClient;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.net.URI;

@Service
@Slf4j
public class CoronaVirusDataService {

    RestTemplate restTemplate;
    public CoronaVirusDataService(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }
    private static final String VIRUS_DATA_URL ="https://covid19.mathdro.id/api/deaths";
    private static final String COUNTRY_REGION = "countryRegion";
    private static final String PROVINCE_STATE = "provinceState";
    private static final String CONFIRMED = "confirmed";
    private static final String RECOVERED = "recovered";
    private static final String ACTIVE = "active";
    private static final String DEATH = "deaths";
    private List<LocationStat> allStats=new ArrayList<>();

    public List<LocationStat> getAllStats()
    {
        return allStats;
    }

    @PostConstruct
    public void fetchVirusData() throws IOException,InterruptedException{
        try
        {
       URI uri = new URI(VIRUS_DATA_URL);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> request = new HttpEntity<>(headers);
        JSONArray ja = restTemplate.getForObject(uri, JSONArray.class);

        int n=ja.length();
        for(int i=0;i<n;i++)
        {
            LocationStat locationStat=new LocationStat();
            JSONObject jo = ja.getJSONObject(i);
            locationStat.setCountry(jo.getString(COUNTRY_REGION));
            locationStat.setState(jo.getString(PROVINCE_STATE));
            locationStat.setLatestTotalCases(jo.getInt(CONFIRMED));
            locationStat.setRecoveredCases(jo.getInt(RECOVERED));
            locationStat.setCurrentActiveCases(jo.getInt(ACTIVE));
            locationStat.setDeathCases(jo.getInt(DEATH));
            allStats.add(locationStat);
        }
        }
        catch (URISyntaxException | JSONException e)
        {
             log.error("Exception occured = {}",e);
            throw new RuntimeException("Error occured while fetching data from tracker");
        }
        finally {
            //TODO: to be used when making DB calls to close connections
        }
    }
}
