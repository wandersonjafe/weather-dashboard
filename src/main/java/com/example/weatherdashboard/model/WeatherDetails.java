package com.example.weatherdashboard.model;

public record WeatherDetails(
        double temperature,
        double windspeed,
        String time
) {}
