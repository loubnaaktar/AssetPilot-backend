package org.example.assetpilotbackend.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.assetpilotbackend.enums.NiveauUrgence;
import org.example.assetpilotbackend.enums.StatutIncident;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncidentUpdateRequest {

    private String description;

    private NiveauUrgence niveauUrgence;

    private StatutIncident statut;

    private String rapportIntervention;

    private Long traiteParId;
}
