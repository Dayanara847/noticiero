package com.henry.noticiero.controller;

import com.henry.noticiero.model.response.WeatherResponse;
import com.henry.noticiero.service.ApiCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequestMapping("/api")
@RestController
public class ApiCallController {

    @Autowired
    ApiCallService apiCallService;

    @GetMapping
    public WeatherResponse callAPI() {
        try {
            return apiCallService.callAPI();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

/*    @GetMapping("/async")
    public WeatherResponse callAPI() {
        try {
            return apiCallService.callAPI();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }*/
}
