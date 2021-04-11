package com.henry.noticiero.model.response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WeatherOpenAPIResponse {

    @SerializedName("weather")
    private WeatherElement[] weather;

    @SerializedName("main")
    private MainResponse mainResponse;

}
