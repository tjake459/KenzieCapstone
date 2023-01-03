package com.kenzie.capstone.service.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RecipeDao {
    private static final String URL = "https://api.spoonacular.com/recipes/complexSearch?";
    private static final String API_KEY = "&apiKey=ac20e561ee6c4d8db924b44a0b3db6db";
    private static final String INTOLERANCE = "intolerance=";
    private static final String INGREDIENTS = "includeIngredients=";

    public String getRecipesByRestrictions(String dietaryRestriction) {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL.concat(INTOLERANCE + dietaryRestriction).concat(API_KEY)))
                .header("Accept", "application/json")
                .GET()
                .build();

        try {
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = httpResponse.statusCode();
            if (statusCode == 200) {
                return httpResponse.body();
            } else {
                return String.format("GET request failed: %d status code received", statusCode);
            }

        } catch (IOException | InterruptedException e) {
            return e.getMessage();
        }
    }

    public String getRecipesByIngredients(String ingredients) {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL.concat(INGREDIENTS + ingredients).concat(API_KEY)))
                .header("Accept", "application/json")
                .GET()
                .build();

        try {
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = httpResponse.statusCode();
            if (statusCode == 200) {
                return httpResponse.body();
            } else {
                return String.format("GET request failed: %d status code received", statusCode);
            }

        } catch (IOException | InterruptedException e) {
            return e.getMessage();
        }
    }
}