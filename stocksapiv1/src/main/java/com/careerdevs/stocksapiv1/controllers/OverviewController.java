package com.careerdevs.stocksapiv1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/stocks")
public class OverviewController {

    // This code has been shortened and adjusted by me from the code shown in the instructions. The route has been tested with multiple stock symbols and works.

    @Autowired
    private Environment env;

    String BASE_URL = "https://www.alphavantage.co/query?function=OVERVIEW&symbol=";

    @GetMapping("/overview")
    public ResponseEntity<?> dynamicOverview(@RequestParam(required = false) String symbol, RestTemplate restTemplate) {

        // FINAL LINK SHOULD LOOK LIKE: https://www.alphavantage.co/query?function=OVERVIEW&symbol=IBM&apikey=demo

        // LOCALHOST EXAMPLE LINK: http://localhost:4000/api/stocks/overview?symbol=GOOGL

        try {

            return ResponseEntity.ok(restTemplate.getForObject(BASE_URL + symbol + "&apikey=" + env.getProperty("AV_API_KEY"), Object.class));

        } catch (Exception e) {

            return ResponseEntity.internalServerError().body(e.getMessage());
            
        }

    }

    // cannot be run directly from here because there's no main method. annotations aren't allowed within main methods. therefore, this must be run from the main class.

}
