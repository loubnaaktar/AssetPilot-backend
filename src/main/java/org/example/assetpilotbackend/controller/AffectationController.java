package org.example.assetpilotbackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.assetpilotbackend.dto.affectation.AffectationRequest;
import org.example.assetpilotbackend.dto.affectation.AffectationResponse;
import org.example.assetpilotbackend.service.AffectationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/affectations")
@RequiredArgsConstructor
public class AffectationController {
    private final AffectationService affectationService;

    @PostMapping
    public AffectationResponse creerAffectations(@Valid @RequestBody AffectationRequest request){
        return affectationService.creerAffectation(request);
    }

    @PutMapping("/{id}/restituer")
    public AffectationResponse restituerEquipement(@PathVariable Long id){
        return affectationService.restituerEquipement(id);
    }

    @GetMapping
    public Page<AffectationResponse> getAllAffectations(Pageable pageable){
        return affectationService.allAffectations(pageable);
    }

    @GetMapping("/{id}")
    public AffectationResponse chercherParId(@PathVariable Long id){
        return affectationService.chercherById(id);
    }
}
