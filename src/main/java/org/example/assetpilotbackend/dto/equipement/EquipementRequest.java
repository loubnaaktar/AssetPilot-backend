package org.example.assetpilotbackend.dto.equipement;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.assetpilotbackend.enums.StatutEquipement;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipementRequest {

    @NotBlank(message = "Le numero de serie est obligatoire")
    private String numeroSerie;

    @NotBlank(message = "Le modele est obligatoire")
    private String modele;

    @NotBlank(message = "La marque est obligatoire")
    private String marque;

    private LocalDate dateAchat;

    @NotNull(message = "La categorie est obligatoire")
    private Long categorieId;

    @NotNull(message = "le statut est bligatoire")
    private StatutEquipement statut;
}
