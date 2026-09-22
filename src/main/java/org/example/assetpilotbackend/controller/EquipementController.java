package org.example.assetpilotbackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.assetpilotbackend.dto.equipement.EquipementRequest;
import org.example.assetpilotbackend.dto.equipement.EquipementResponse;
import org.example.assetpilotbackend.enums.Role;
import org.example.assetpilotbackend.enums.StatutEquipement;
import org.example.assetpilotbackend.model.Utilisateur;
import org.example.assetpilotbackend.service.EquipementService;
import org.example.assetpilotbackend.service.QrCodeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/equipements")
public class EquipementController {

    private final EquipementService equipementService;

    private final QrCodeService qrCodeService;

    @GetMapping("/{id}/qr-code")
    public ResponseEntity<byte[]> getQrCode(@PathVariable Long id) {
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(qrCodeService.genererQrCode(id));
    }

    @GetMapping("/employe/{employeId}")
    @PreAuthorize("hasAnyRole('EMPLOYE', 'ADMIN')")
    public Page<EquipementResponse> equipementsParEmploye(@PathVariable long employeId,
                                                          @AuthenticationPrincipal Utilisateur utilisateur,
                                                          Pageable pageable) {
        verifierEmployeAuthorise(employeId, utilisateur);
        return equipementService.equipementsActifsEmploye(employeId, pageable);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public EquipementResponse ajouterEquipement(@Valid @RequestBody EquipementRequest request){
    return equipementService.ajouterEquipement(request);
}
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'TECHNICIEN')")
    public Page<EquipementResponse> getAllEquipments(@RequestParam(required = false) String mot,
                                                     @RequestParam(required = false) StatutEquipement statut,
                                                     @RequestParam(required = false) Long categorieId,
                                                     Pageable pageable) {
        return equipementService.rechercherEquipements(mot, statut, categorieId, pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TECHNICIEN')")
    public EquipementResponse checherParId(@PathVariable Long id){
        return equipementService.chercherById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public EquipementResponse modifierEquipement(@PathVariable Long id , @Valid @RequestBody EquipementRequest request){
        return equipementService.modifierEquipement(id,request);
    }

    @GetMapping("/statut/{statut}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TECHNICIEN')")
    public Page<EquipementResponse> getEquipementParStatut(@PathVariable StatutEquipement statut, Pageable pageable){
        return equipementService.equipementsParStatut(statut,pageable);
    }

    @GetMapping("Categorie/{categorieId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TECHNICIEN')")
    public Page<EquipementResponse> getEquipementParCategorie(@PathVariable Long categorieId,Pageable pageable){
        return equipementService.equipementsParCategorie(categorieId,pageable);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void supprimerEquipement(@PathVariable Long id){
        equipementService.supprimerEquipement(id);
    }

    private void verifierEmployeAuthorise(long employeId, Utilisateur utilisateur) {
        if (utilisateur.getRole() == Role.EMPLOYE && utilisateur.getId() != employeId) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "Vous ne pouvez consulter que vos propres équipements");
        }
    }
}
