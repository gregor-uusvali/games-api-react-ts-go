package com.example.backend.dailyRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping(path = "api/v1/dailyRandom")
public class DailyRandomController {

    private final DailyRandomService dailyRandomService;

    @Autowired
    public DailyRandomController(DailyRandomService dailyRandomService) {
        this.dailyRandomService = dailyRandomService;
    }

    @GetMapping
    public String getDailyRandomNumber() throws URISyntaxException, IOException, InterruptedException {
        int rn = dailyRandomService.getDailyRandomNumber();
        String rp = dailyRandomService.getDailyRandomPlant(rn);
        return rp;

    }
}
