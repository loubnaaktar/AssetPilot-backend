package org.example.assetpilotbackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.assetpilotbackend.enums.StatutEquipement;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipementResponse {

    private Long id;
    private String numeroSerie;
    private String modele;
    private String marque;
    private LocalDate dateAchat;
    private StatutEquipement statut;
    private Long categorieId;
    private String categorieNom;
}
