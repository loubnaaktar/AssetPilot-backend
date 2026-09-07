package org.example.assetpilotbackend.dto.affectation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.assetpilotbackend.enums.StatutAffectation;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AffectationResponse {

    private Long id;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private StatutAffectation statut;
    private Long employeId;
    private String employeNom;
    private Long equipementId;
    private String equipementNumeroSerie;
}
