package com.coronavirus.CoronaVirus.Tracker.Controller;

import com.coronavirus.CoronaVirus.Tracker.Model.LocationStat;
import com.coronavirus.CoronaVirus.Tracker.Service.CoronaVirusDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/coronavirus") 
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model) { 
        List<LocationStat> allStats = coronaVirusDataService.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(LocationStat::getCurrentActiveCases).sum();
        int totalNewCases = allStats.stream().mapToInt(LocationStat::getLatestTotalCases).sum();
          int totalDeathCases = allStats.stream().mapToInt(LocationStat::getLatestTotalCases).sum()-totalNewCases;

        model.addAttribute("locationStats", allStats); 
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases); 
        model.addAttribute("totalDeathCases",totalDeathCases);

        log.info("Total Reported Cases = {}", totalReportedCases);
        return "home";
    }
}
