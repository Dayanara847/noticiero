package com.henry.noticiero.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WeatherElement {

    private long id;
    private String main;
    private String description;
    private String icon;

}
