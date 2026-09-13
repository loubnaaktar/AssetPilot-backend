package org.example.assetpilotbackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.assetpilotbackend.dto.utilisateur.UtilisateurRequest;
import org.example.assetpilotbackend.dto.utilisateur.UtilisateurResponse;
import org.example.assetpilotbackend.service.UtilisateurService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/utilisateurs")
@RequiredArgsConstructor
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public UtilisateurResponse registerUtilisateur(@Valid @RequestBody UtilisateurRequest request) {
        return utilisateurService.registerUtilisateur(request);
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

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void supprimerUtilisateur(@PathVariable Long id) {
        utilisateurService.supprimerUtilisateur(id);
    }
}