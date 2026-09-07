package org.example.assetpilotbackend.dto.statistiques;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsResponse {

    private long totalEquipements;
    private long equipementsAffectes;
    private long equipementsEnStock;
    private long equipementsEnPanne;
    private long totalEmployes;
    private long totalIncidents;
    private long incidentsOuverts;
}
