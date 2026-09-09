package org.example.assetpilotbackend.service;

import org.example.assetpilotbackend.dto.utilisateur.UtilisateurRequest;
import org.example.assetpilotbackend.dto.utilisateur.UtilisateurResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UtilisateurService {
UtilisateurResponse registerUtilisateur(UtilisateurRequest request);
UtilisateurResponse getUtilisateurById(Long id);
UtilisateurResponse getUtilisateurByEmail(String email);
Page<UtilisateurResponse>  getAllUtilisateurs(Pageable pageable);
UtilisateurResponse modifierUtilisateur(long id,UtilisateurRequest request);
void supprimerUtilisateur(Long id);
}