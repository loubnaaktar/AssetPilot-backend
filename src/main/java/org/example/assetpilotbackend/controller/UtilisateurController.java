package org.example.assetpilotbackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.assetpilotbackend.dto.utilisateur.ProfilResponse;
import org.example.assetpilotbackend.dto.utilisateur.ProfilUpdateRequest;
import org.example.assetpilotbackend.dto.utilisateur.ResetPasswordRequest;
import org.example.assetpilotbackend.dto.utilisateur.UtilisateurRequest;
import org.example.assetpilotbackend.dto.utilisateur.UtilisateurResponse;
import org.example.assetpilotbackend.model.Utilisateur;
import org.example.assetpilotbackend.service.UtilisateurService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/utilisateurs")
@RequiredArgsConstructor
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public UtilisateurResponse ajouterAdmin(@Valid @RequestBody UtilisateurRequest request) {
        return utilisateurService.ajouterAdmin(request);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Page<UtilisateurResponse> getAllUtilisateurs(Pageable pageable) {
        return utilisateurService.getAllUtilisateurs(pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UtilisateurResponse chercherParId(@PathVariable Long id) {
        return utilisateurService.getUtilisateurById(id);
    }

    @GetMapping("/email/{email}")
    @PreAuthorize("hasRole('ADMIN')")
    public UtilisateurResponse chercherParEmail(@PathVariable String email) {
        return utilisateurService.getUtilisateurByEmail(email);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UtilisateurResponse modifierUtilisateur(@PathVariable Long id, @Valid @RequestBody UtilisateurRequest request) {
        return utilisateurService.modifierUtilisateur(id, request);
    }

    @PutMapping("/changer-password")
    public void changerPassword(@AuthenticationPrincipal Utilisateur utilisateur,
                                @Valid @RequestBody ResetPasswordRequest request) {
        utilisateurService.changerPassword(utilisateur, request);
    }

    @GetMapping("/profil")
    public ProfilResponse monProfil(@AuthenticationPrincipal Utilisateur utilisateur) {
        return utilisateurService.getProfil(utilisateur);
    }

    @PutMapping("/profil")
    public ProfilResponse modifierMonProfil(@AuthenticationPrincipal Utilisateur utilisateur,
                                            @Valid @RequestBody ProfilUpdateRequest request) {
        return utilisateurService.mettreAjourProfil(utilisateur, request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void supprimerUtilisateur(@PathVariable Long id) {
        utilisateurService.supprimerUtilisateur(id);
    }
}