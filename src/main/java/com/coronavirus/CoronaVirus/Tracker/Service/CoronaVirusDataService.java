package com.coronavirus.CoronaVirus.Tracker.Service;

import com.coronavirus.CoronaVirus.Tracker.Model.LocationStat;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sun.deploy.net.HttpResponse;
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
public class CoronaVirusDataService {

    RestTemplate restTemplate;
    public CoronaVirusDataService(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }
    private static final String VIRUS_DATA_URL ="https://covid19.mathdro.id/api/deaths";
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
            locationStat.setCountry(jo.getString("countryRegion"));
            locationStat.setState(jo.getString("provinceState"));
            locationStat.setLatestTotalCases(jo.getInt("confirmed"));
            locationStat.setRecoveredCases(jo.getInt("recovered"));
            locationStat.setCurrentActiveCases(jo.getInt("active"));
            locationStat.setDeathCases(jo.getInt("deaths"));
            allStats.add(locationStat);
        }
        }
        catch (URISyntaxException | JSONException e)
        {
       // e.printStackTrace();
        }
        finally {
           // this.allStats = newStat;
//            for(LocationStat lo:allStats)
//            {
//                System.out.println(lo.getCountry());
//                System.out.println(lo.getState());
//                System.out.println(lo.getCurrentActiveCases());
//            }
        }
    }
}
