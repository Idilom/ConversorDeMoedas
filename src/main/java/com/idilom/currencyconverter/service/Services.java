package com.idilom.currencyconverter.service;

import com.google.gson.Gson;
import com.idilom.currencyconverter.model.ExchangeRateResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Services {

    public static final String API_KEY = "YOUR_API_KEY";

    public static double getConversionRate (String from, String to) throws IOException, InterruptedException {
        String url = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/" + from;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        ExchangeRateResponse rateResponse = gson.fromJson(response.body(), ExchangeRateResponse.class);

        if (rateResponse.conversion_rates.containsKey(to)) {
            return rateResponse.conversion_rates.get(to);
        }
        return -1;


    }

}