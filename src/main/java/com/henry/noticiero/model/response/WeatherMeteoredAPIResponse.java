package com.henry.noticiero.model.response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class WeatherMeteoredAPIResponse {

    @SerializedName("day")
    private Map<String, Day> day;

}
