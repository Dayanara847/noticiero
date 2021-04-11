package com.henry.noticiero.model.response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Day {

    @SerializedName("symbol_description")
    private String status;

    @SerializedName("tempmin")
    private Double tempMin;

    @SerializedName("tempmax")
    private Double tempMax;

    @SerializedName("hour")
    private Hour[] hour;

}
