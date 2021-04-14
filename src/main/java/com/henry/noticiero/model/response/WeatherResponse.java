package com.henry.noticiero.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class WeatherResponse {

    private String status;
    private Double temp;
    private Double tempMax;
    private Double tempMin;

}
