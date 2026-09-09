package org.example.assetpilotbackend.service.impl;

import org.example.assetpilotbackend.dto.utilisateur.UtilisateurRequest;
import org.example.assetpilotbackend.dto.utilisateur.UtilisateurResponse;
import org.example.assetpilotbackend.service.UtilisateurService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    @Override
    public UtilisateurResponse registerUtilisateur(UtilisateurRequest request) {
        return null;
    }

    @Override
    public UtilisateurResponse getUtilisateurById(Long id) {
        return null;
    }

    @Override
    public UtilisateurResponse getUtilisateurByEmail(String email) {
        return null;
    }

    @Override
    public Page<UtilisateurResponse> getAllUtilisateurs(Pageable pageable) {
        return null;
    }

    @Override
    public UtilisateurResponse modifierUtilisateur(long id, UtilisateurRequest request) {
        return null;
    }

    @Override
    public void supprimerUtilisateur(Long id) {

    }
}
