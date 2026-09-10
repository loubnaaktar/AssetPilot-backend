package org.example.assetpilotbackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.assetpilotbackend.dto.utilisateur.UtilisateurRequest;
import org.example.assetpilotbackend.dto.utilisateur.UtilisateurResponse;
import org.example.assetpilotbackend.service.UtilisateurService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/utilisateurs")
@RequiredArgsConstructor
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @PostMapping
    public UtilisateurResponse registerUtilisateur(@Valid @RequestBody UtilisateurRequest request) {
        return utilisateurService.registerUtilisateur(request);
    }

    @GetMapping
    public Page<UtilisateurResponse> getAllUtilisateurs(Pageable pageable) {
        return utilisateurService.getAllUtilisateurs(pageable);
    }

    @GetMapping("/{id}")
    public UtilisateurResponse chercherParId(@PathVariable Long id) {
        return utilisateurService.getUtilisateurById(id);
    }

    @GetMapping("/email/{email}")
    public UtilisateurResponse chercherParEmail(@PathVariable String email) {
        return utilisateurService.getUtilisateurByEmail(email);
    }

    @PutMapping("/{id}")
    public UtilisateurResponse modifierUtilisateur(@PathVariable Long id, @Valid @RequestBody UtilisateurRequest request) {
        return utilisateurService.modifierUtilisateur(id, request);
    }

    @DeleteMapping("/{id}")
    public void supprimerUtilisateur(@PathVariable Long id) {
        utilisateurService.supprimerUtilisateur(id);
    }
}