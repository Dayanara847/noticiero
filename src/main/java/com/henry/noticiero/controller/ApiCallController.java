package com.henry.noticiero.controller;

import com.henry.noticiero.model.response.ChuckNorrisResponse;
import com.henry.noticiero.service.ApiCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RequestMapping("/api")
@RestController
public class ApiCallController {

    @Autowired
    ApiCallService apiCallService;

    @GetMapping
    public ChuckNorrisResponse callAPI() {
        try {
            return apiCallService.callAPI();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/async")
    public List<ChuckNorrisResponse> callListAPI() {
        try {
            return apiCallService.callListAPI();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
