package com.henry.noticiero.service;

import com.google.gson.Gson;
import com.henry.noticiero.model.response.Day;
import com.henry.noticiero.model.response.WeatherMeteoredAPIResponse;
import com.henry.noticiero.model.response.WeatherOpenAPIResponse;
import com.henry.noticiero.model.response.WeatherResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@Slf4j
public class ApiCallService {

    private static final HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    private static final String API_KEY = "10a2833db7bfe71cbbcc08efc8124882";
    private static final String URL = "https://api.openweathermap.org/data/2.5/weather?q=montevideo&units=metric&appid=" + API_KEY;
    private static final String URL2 = "http://api.meteored.cl/index.php?api_lang=en&localidad=13027&affiliate_id=4y4r2dl4bvxv&v=3.0";

    @CircuitBreaker(name = "weather", fallbackMethod = "fallback")
    public WeatherResponse callAPI() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        final WeatherOpenAPIResponse weatherResponse = new Gson().fromJson(response.body(), WeatherOpenAPIResponse.class);

        Double tempMax = weatherResponse.getMainResponse().getTemp_max();
        Double tempMin = weatherResponse.getMainResponse().getTemp_min();
        Double temp = weatherResponse.getMainResponse().getTemp();
        String status = weatherResponse.getWeather()[0].getMain();

        WeatherResponse weatherFinalResponse = new WeatherResponse(status, temp, tempMax, tempMin);

        /*if (RandomUtils.nextBoolean()) {
            throw new IOException("Probando el @CircuitBreaker");
        }*/
        return weatherFinalResponse;
    }

    private WeatherResponse fallback(final Throwable t) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL2))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        try {
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            final WeatherMeteoredAPIResponse meteoredResponse = new Gson().fromJson(response.body(), WeatherMeteoredAPIResponse.class);

            Day day = meteoredResponse.getDay().get("1");

            return WeatherResponse
                    .builder()
                    .status(day.getStatus())
                    .temp(day.getHour()[4].getTemp())
                    .tempMin(day.getTempMin())
                    .tempMax(day.getTempMax())
                    .build();

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Esta información no está disponible por el momento.");
        }
    }

}
