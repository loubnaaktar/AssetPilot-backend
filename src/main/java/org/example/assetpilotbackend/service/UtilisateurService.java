package org.example.assetpilotbackend.service;

import org.example.assetpilotbackend.dto.utilisateur.ProfilResponse;
import org.example.assetpilotbackend.dto.utilisateur.ProfilUpdateRequest;
import org.example.assetpilotbackend.dto.utilisateur.ResetPasswordRequest;
import org.example.assetpilotbackend.dto.utilisateur.UtilisateurRequest;
import org.example.assetpilotbackend.dto.utilisateur.UtilisateurResponse;
import org.example.assetpilotbackend.model.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UtilisateurService {

    UtilisateurResponse ajouterAdmin(UtilisateurRequest request);

    UtilisateurResponse getUtilisateurById(Long id);

    UtilisateurResponse getUtilisateurByEmail(String email);

    Page<UtilisateurResponse> getAllUtilisateurs(Pageable pageable);

    UtilisateurResponse modifierUtilisateur(long id, UtilisateurRequest request);

    void changerPassword(Utilisateur utilisateur, ResetPasswordRequest request);

    void supprimerUtilisateur(Long id);

    ProfilResponse getProfil(Utilisateur utilisateur);

    ProfilResponse mettreAjourProfil(Utilisateur utilisateur, ProfilUpdateRequest request);
}