package org.example.assetpilotbackend.dto.incident;

import jakarta.validation.constraints.NotBlank;
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

    @NotNull(message = "Le statut est obligatoire")
    private StatutIncident statut;

    @NotBlank(message = "Le rapport d'intervention est obligatoire")
    private String rapportIntervention;

    private Long traiteParId;

    private Boolean equipementHorsService;
}
