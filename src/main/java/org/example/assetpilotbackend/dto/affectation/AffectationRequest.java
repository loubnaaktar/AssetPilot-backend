package org.example.assetpilotbackend.dto.affectation;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AffectationRequest {

    @NotNull(message = "La date de debut est obligatoire")
    private LocalDate dateDebut;

    private LocalDate dateFin;

    @NotNull(message = "L'employe est obligatoire")
    private Long employeId;

    @NotNull(message = "L'equipement est obligatoire")
    private Long equipementId;
}
