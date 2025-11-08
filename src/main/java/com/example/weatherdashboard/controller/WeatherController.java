package com.example.weatherdashboard.controller;

import com.example.weatherdashboard.model.WeatherData; // NOVO IMPORT
import com.example.weatherdashboard.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/")
    public String index (){
        return "index";
    }

    @GetMapping("/weather")
    public String getWeather (@RequestParam("city") String city, Model model) {
        try {

            if (city == null || city.trim().isEmpty()) {
                model.addAttribute("error", "Por favor, informe o nome de uma cidade.");
                return "index";
            }

            // Agora usa o tipo explícito WeatherData (em vez de 'var' ou 'Map')
            WeatherData weatherData = weatherService.getWeather(city);

            model.addAttribute("weatherData", weatherData);
            model.addAttribute("error", null);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "index";
    }
}