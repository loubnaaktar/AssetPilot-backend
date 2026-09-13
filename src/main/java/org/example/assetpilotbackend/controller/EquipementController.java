package org.example.assetpilotbackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.assetpilotbackend.dto.equipement.EquipementRequest;
import org.example.assetpilotbackend.dto.equipement.EquipementResponse;
import org.example.assetpilotbackend.enums.StatutEquipement;
import org.example.assetpilotbackend.service.EquipementService;
import org.example.assetpilotbackend.service.QrCodeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/equipements")
public class EquipementController {

    public final EquipementService equipementService;

    private final QrCodeService qrCodeService;

    @GetMapping("/{id}/qr-code")
    public ResponseEntity<byte[]> getQrCode(@PathVariable Long id) {
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(qrCodeService.genererQrCode(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public EquipementResponse ajouterEquipement(@Valid @RequestBody EquipementRequest request){
    return equipementService.ajouterEquipement(request);
}
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'TECHNICIAN')")
    public Page<EquipementResponse> getAllEquipments(Pageable pageable){
        return equipementService.allEquipements(pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TECHNICIAN')")
    public EquipementResponse checherParId(@PathVariable Long id){
        return equipementService.chercherById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public EquipementResponse modifierEquipement(@PathVariable Long id , @Valid @RequestBody EquipementRequest request){
        return equipementService.modifierEquipement(id,request);
    }

    @GetMapping("/statut/{statut}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TECHNICIAN')")
    public Page<EquipementResponse> getEquipementParStatut(@PathVariable StatutEquipement statut, Pageable pageable){
        return equipementService.equipementsParStatut(statut,pageable);
    }

    @GetMapping("Categorie/{categorieId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TECHNICIAN')")
    public Page<EquipementResponse> getEquipementParCategorie(@PathVariable Long categorieId,Pageable pageable){
        return equipementService.equipementsParCategorie(categorieId,pageable);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void supprimerEquipement(@PathVariable Long id){
        equipementService.supprimerEquipement(id);
    }
}
