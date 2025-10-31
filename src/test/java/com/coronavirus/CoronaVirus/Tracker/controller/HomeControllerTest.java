package com.coronavirus.CoronaVirus.Tracker.controller;

import com.coronavirus.CoronaVirus.Tracker.Controller.HomeController;
import com.coronavirus.CoronaVirus.Tracker.Model.LocationStat;
import com.coronavirus.CoronaVirus.Tracker.Service.CoronaVirusDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomeControllerTest {

    @Mock
    private CoronaVirusDataService coronaVirusDataService;

    @InjectMocks
    private HomeController homeController;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testHome_ReturnsHomePageWithStats() throws Exception {
        // Mock data
        List<LocationStat> mockStats = Arrays.asList(new LocationStat("CA","US", 100, 10,5,2),
                new LocationStat("KA","IN", 50, 5,2,2));
        // Mock service behavior
        Mockito.when(coronaVirusDataService.getAllStats()).thenReturn(mockStats);
        Model model = Mockito.mock(Model.class);
       String op = homeController.home(model);
        assertEquals("home", op);
          assertEquals(1, mockStats.size());
    }
}
