package org.example.assetpilotbackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.assetpilotbackend.dto.statistiques.StatisticsResponse;
import org.example.assetpilotbackend.service.StatisticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistiques")
@RequiredArgsConstructor
public class StatistiquesController {

    private final StatisticsService statisticsService;

    @GetMapping
    public StatisticsResponse getStatistiquesGlobales() {
        return statisticsService.getStatistiquesGlobales();
    }
}
