package org.example.assetpilotbackend.dto.incident;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.assetpilotbackend.enums.NiveauUrgence;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncidentRequest {

    @NotBlank(message = "La description est obligatoire")
    private String description;

    @NotNull(message = "Le niveau d'urgence est obligatoire")
    private NiveauUrgence niveauUrgence;

    @NotNull(message = "L'equipement est obligatoire")
    private Long equipementId;

    @NotNull(message = "L'employe declarant est obligatoire")
    private Long declareParId;
}
