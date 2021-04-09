package com.henry.noticiero.service;

import com.google.gson.Gson;
import com.henry.noticiero.model.response.ChuckNorrisResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

@Service
@Slf4j
public class ApiCallService {

    private static final HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    private static final String URL = "http://webcode.me";

    @CircuitBreaker(name = "ChuckNorris", fallbackMethod = "fallback")
    public ChuckNorrisResponse callAPI() throws IOException, InterruptedException {


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://matchilling-chuck-norris-jokes-v1.p.rapidapi.com/jokes/random"))
                .header("accept", "application/json")
                .header("x-rapidapi-key", "be3e242d52msh019478af3718bf5p1c359cjsnaa0936525cfb")
                .header("x-rapidapi-host", "matchilling-chuck-norris-jokes-v1.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());


        final ChuckNorrisResponse chuckNorrisResponse = new Gson().fromJson(response.body(), ChuckNorrisResponse.class);

        System.out.println(chuckNorrisResponse);

        if (RandomUtils.nextBoolean()) {
            throw new IOException("Probando Circuit Breaker");
        }

        return chuckNorrisResponse;
    }

    public List<ChuckNorrisResponse> callListAPI() throws IOException, InterruptedException, CompletionException {
        List<ChuckNorrisResponse> norrisResponses = new ArrayList<>();

        CompletableFuture<HttpResponse<String>> chuck1 = chuckNorrisAsync();
        CompletableFuture<HttpResponse<String>> chuck2 = chuckNorrisAsync();

        final ChuckNorrisResponse chuckNorrisResponse1 = new Gson().fromJson(chuck1.join().body(), ChuckNorrisResponse.class);
        final ChuckNorrisResponse chuckNorrisResponse2 = new Gson().fromJson(chuck2.join().body(), ChuckNorrisResponse.class);

        norrisResponses.add(chuckNorrisResponse1);
        norrisResponses.add(chuckNorrisResponse2);

        return norrisResponses;
    }

    private ChuckNorrisResponse fallback(final Throwable t) {
        log.error(t.getStackTrace().toString());
        return ChuckNorrisResponse
                .builder()
                .id("asd")
                .iconUrl("someurl")
                .url("someurl")
                .value("someValue")
                .updated("updated")
                .categories(new HashMap<>())
                .build();
    }

    private CompletableFuture<ChuckNorrisResponse> fallbackAsync(final Throwable t) {
        log.error(t.getStackTrace().toString());
        return CompletableFuture.supplyAsync(() -> fallback(t));
    }

    @Async("taskExecutor")
    @CircuitBreaker(name = "ChuckNorris", fallbackMethod = "fallbackAsync")
    public CompletableFuture<HttpResponse<String>> chuckNorrisAsync() throws IllegalArgumentException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://matchilling-chuck-norris-jokes-v1.p.rapidapi.com/jokes/random"))
                .header("accept", "application/json")
                .header("x-rapidapi-key", "be3e242d52msh019478af3718bf5p1c359cjsnaa0936525cfb")
                .header("x-rapidapi-host", "matchilling-chuck-norris-jokes-v1.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        return HttpClient.newHttpClient().sendAsync(request, HttpResponse.BodyHandlers.ofString());

    }
}
