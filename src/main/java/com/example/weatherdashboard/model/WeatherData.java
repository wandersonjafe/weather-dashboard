package com.example.weatherdashboard.model;

public record WeatherData(
   String city,
   String country,
   double latitude,
   double longitude,
   WeatherDetails weather
) {}
