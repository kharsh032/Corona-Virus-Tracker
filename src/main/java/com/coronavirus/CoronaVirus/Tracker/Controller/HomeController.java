package com.coronavirus.CoronaVirus.Tracker.Controller;

import com.coronavirus.CoronaVirus.Tracker.Model.LocationStat;
import com.coronavirus.CoronaVirus.Tracker.Service.CoronaVirusDataService;
import com.coronavirus.CoronaVirus.Tracker.constants.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@Slf4j
@RequestMapping(value = "/coronavirus")
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String getAllStatsForCoronaVirus(Model model)
    {
        List<LocationStat> allStats = coronaVirusDataService.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getCurrentActiveCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        model.addAttribute(AppConstants.LOCATION_STATS, allStats);
        model.addAttribute(AppConstants.TOTAL_REPORTED_CASES, totalReportedCases);
        model.addAttribute(AppConstants.TOTAL_NEW_CASES, totalNewCases);
        log.info("Total Reported Cases = {}",totalReportedCases);
        return "Data fetched successfully";
    }
}
