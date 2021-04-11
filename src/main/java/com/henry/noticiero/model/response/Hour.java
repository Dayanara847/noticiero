package com.henry.noticiero.model.response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Hour {

    @SerializedName("temp")
    private Double temp;

}
