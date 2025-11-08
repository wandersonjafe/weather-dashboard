package com.example.weatherdashboard.service;

import com.example.weatherdashboard.model.WeatherData;
import com.example.weatherdashboard.model.WeatherDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.Normalizer;
import java.util.List;
import java.util.Map;

@Service
public class WeatherService {

    private final RestTemplate restTemplate = new RestTemplate();

    // O método agora retorna o Record WeatherData
    public WeatherData getWeather(String city) {
        try {
            Map<?, ?> geoResponse = callGeoApi(city);

            if (geoResponse == null || !geoResponse.containsKey("results")) {
                geoResponse = callGeoApi(city + ",BR");
            }

            if (geoResponse == null || !geoResponse.containsKey("results")) {
                throw new RuntimeException("Cidade não encontrada!");
            }

            Map<?, ?> firstResult = ((List<Map<?, ?>>) geoResponse.get("results")).get(0);
            double latitude = (double) firstResult.get("latitude");
            double longitude = (double) firstResult.get("longitude");

            String weatherUrl = UriComponentsBuilder
                    .fromHttpUrl("https://api.open-meteo.com/v1/forecast")
                    .queryParam("latitude", latitude)
                    .queryParam("longitude", longitude)
                    .queryParam("current_weather", true)
                    .toUriString();

            Map<String, Object> weatherResponse = restTemplate.getForObject(weatherUrl, Map.class);

            // Extrai o mapa do clima atual
            Map<String, Object> currentWeatherMap = (Map<String, Object>) weatherResponse.get("current_weather");

            // 1. Cria o Record WeatherDetails (usando o construtor gerado)
            WeatherDetails details = new WeatherDetails(
                    (Double) currentWeatherMap.get("temperature"),
                    (Double) currentWeatherMap.get("windspeed"),
                    (String) currentWeatherMap.get("time")
            );

            // 2. Cria e retorna o Record WeatherData (usando o construtor gerado)
            return new WeatherData(
                    (String) firstResult.get("name"),
                    (String) firstResult.get("country"),
                    latitude,
                    longitude,
                    details // Adiciona o Record tipado de detalhes
            );

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar o clima: " + e.getMessage());
        }
    }

    private Map<?, ?> callGeoApi(String cityQuery) {
        String normalizedCity = normalizeCityName(cityQuery);

        String geoUrl = UriComponentsBuilder
                .fromHttpUrl("https://geocoding-api.open-meteo.com/v1/search")
                .queryParam("name", normalizedCity)
                .encode()
                .toUriString();

        return restTemplate.getForObject(geoUrl, Map.class);
    }

    private String normalizeCityName(String city) {
        String normalized = Normalizer.normalize(city, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        normalized = normalized.trim().replaceAll("\\s+", "+");
        return normalized;
    }
}