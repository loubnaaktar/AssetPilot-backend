package org.example.assetpilotbackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.assetpilotbackend.dto.utilisateur.ResetPasswordRequest;
import org.example.assetpilotbackend.dto.utilisateur.UtilisateurRequest;
import org.example.assetpilotbackend.dto.utilisateur.UtilisateurResponse;
import org.example.assetpilotbackend.enums.Role;
import org.example.assetpilotbackend.exception.ResourceNotFoundException;
import org.example.assetpilotbackend.mapper.UtilisateurMapper;
import org.example.assetpilotbackend.model.Utilisateur;
import org.example.assetpilotbackend.repository.UtilisateurRepository;
import org.example.assetpilotbackend.service.AccountService;
import org.example.assetpilotbackend.service.UtilisateurService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository repo;
    private final UtilisateurMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final AccountService accountService;

    @Override
    public UtilisateurResponse ajouterAdmin(UtilisateurRequest request) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setPrenom(request.getPrenom());
        utilisateur.setNom(request.getNom());
        utilisateur.setEmail(request.getEmail());
        utilisateur.setRole(Role.ADMIN);
        utilisateur.setPassword(accountService.encoderEtEnvoyer(utilisateur.getEmail()));
        return mapper.toDTO(repo.save(utilisateur));
    }

    @Override
    public UtilisateurResponse getUtilisateurById(Long id) {
        return mapper.toDTO(getUtilisateurEntity(id));
    }

    @Override
    public UtilisateurResponse getUtilisateurByEmail(String email) {
        Utilisateur utilisateur = repo.findByEmail(email);
        if (utilisateur == null) {
            throw new ResourceNotFoundException("Utilisateur introuvable avec email: " + email);
        }
        return mapper.toDTO(utilisateur);
    }

    @Override
    public Page<UtilisateurResponse> getAllUtilisateurs(Pageable pageable) {
        return repo.findAll(pageable).map(mapper::toDTO);
    }

    @Override
    public UtilisateurResponse modifierUtilisateur(long id, UtilisateurRequest request) {
        Utilisateur utilisateur = getUtilisateurEntity(id);
        utilisateur.setPrenom(request.getPrenom());
        utilisateur.setNom(request.getNom());
        utilisateur.setEmail(request.getEmail());
        utilisateur.setRole(request.getRole());

        return mapper.toDTO(repo.save(utilisateur));
    }

    @Override
    public void changerPassword(String email, ResetPasswordRequest request) {
        Utilisateur utilisateur = repo.findByEmail(email);
        if (utilisateur == null) {
            throw new ResourceNotFoundException("Utilisateur introuvable avec email: " + email);
        }
        utilisateur.setPassword(passwordEncoder.encode(request.getPassword()));
        repo.save(utilisateur);
    }

    @Override
    public void supprimerUtilisateur(Long id) {
        repo.delete(getUtilisateurEntity(id));
    }

    private Utilisateur getUtilisateurEntity(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur introuvable avec id: " + id));
    }
}
