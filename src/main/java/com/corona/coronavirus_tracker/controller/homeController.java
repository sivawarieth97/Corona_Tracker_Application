package com.corona.coronavirus_tracker.controller;

import com.corona.coronavirus_tracker.models.locationStats;
import com.corona.coronavirus_tracker.services.CoronavirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class homeController {


    @Autowired
    CoronavirusDataService ser;
    @GetMapping("/")
    public String home(Model model){
        List<locationStats> loc=ser.getLoc();
        int total=loc.stream().mapToInt(s -> s.getLatestTotalCases()).sum();
        model.addAttribute("locstat" , loc);
        model.addAttribute("totalCases", total);
        return "home";}
}
