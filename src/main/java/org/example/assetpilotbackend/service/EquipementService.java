package org.example.assetpilotbackend.service;

import org.example.assetpilotbackend.dto.equipement.EquipementRequest;
import org.example.assetpilotbackend.dto.equipement.EquipementResponse;
import org.example.assetpilotbackend.enums.StatutEquipement;
import org.example.assetpilotbackend.model.Equipement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EquipementService {
    EquipementResponse ajouterEquipement(EquipementRequest request);
    Page<EquipementResponse> allEquipements(Pageable pageable);
    EquipementResponse chercherById(long id);
    EquipementResponse modifierEquipement(long id, EquipementRequest request);
    Page<EquipementResponse> equipementsParStatut(StatutEquipement statut, Pageable pageable);
    Page<EquipementResponse> equipementsParCategorie(long categorieId, Pageable pageable);
    void supprimerEquipement(long id);
}
