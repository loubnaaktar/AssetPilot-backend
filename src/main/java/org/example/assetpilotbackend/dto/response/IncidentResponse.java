package org.example.assetpilotbackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.assetpilotbackend.enums.NiveauUrgence;
import org.example.assetpilotbackend.enums.StatutIncident;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncidentResponse {

    private Long id;
    private String description;
    private NiveauUrgence niveauUrgence;
    private StatutIncident statut;
    private LocalDateTime dateDeclaration;
    private LocalDateTime dateResolution;
    private String rapportIntervention;
    private Long declareParId;
    private String declareParNom;
    private Long traiteParId;
    private String traiteParNom;
    private Long equipementId;
    private String equipementNumeroSerie;
}
