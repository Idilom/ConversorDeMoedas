package com.idilom.currencyconverter.service;

import com.google.gson.Gson;
import com.idilom.currencyconverter.model.ExchangeRateResponse;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Services {

    public static double getConversionRate(String from, String to) throws IOException, InterruptedException {
        // Load environment variables from .env file
        Dotenv dotenv = Dotenv.load();
        String apiKey = dotenv.get("API_KEY"); // Ensure your .env has a line: API_KEY=your_actual_key

        // Build the API URL
        String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + from;

        // Create HTTP request and client
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Parse JSON response
        Gson gson = new Gson();
        ExchangeRateResponse rateResponse = gson.fromJson(response.body(), ExchangeRateResponse.class);

        // Return the requested conversion rate
        if (rateResponse.conversion_rates.containsKey(to)) {
            return rateResponse.conversion_rates.get(to);
        }

        // Return -1 if target currency not found
        return -1;
    }
}
