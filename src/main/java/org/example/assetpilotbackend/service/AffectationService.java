package org.example.assetpilotbackend.service;

import org.example.assetpilotbackend.dto.affectation.AffectationRequest;
import org.example.assetpilotbackend.dto.affectation.AffectationResponse;
import org.example.assetpilotbackend.model.Affectation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AffectationService {
    AffectationResponse creerAffectation(AffectationRequest request);
    AffectationResponse restituerEquipement(long affectationId);
    Page<AffectationResponse> allAffectations(Pageable pageable);
    AffectationResponse chercherById(long id);
}
