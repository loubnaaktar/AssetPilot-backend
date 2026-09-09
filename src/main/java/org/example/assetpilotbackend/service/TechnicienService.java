package org.example.assetpilotbackend.service;

import org.example.assetpilotbackend.dto.technicien.TechnicienRequest;
import org.example.assetpilotbackend.dto.technicien.TechnicienResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TechnicienService {
    TechnicienResponse ajouterTechnicien(TechnicienRequest request);
    Page<TechnicienResponse> allTechniciens(Pageable pageable);
    TechnicienResponse chercherById(long id);
    TechnicienResponse modifierTechnicien(long id, TechnicienRequest request);
    void supprimerTechnicien(long id);


}
