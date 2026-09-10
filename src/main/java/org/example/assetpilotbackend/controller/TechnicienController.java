package org.example.assetpilotbackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.assetpilotbackend.dto.technicien.TechnicienRequest;
import org.example.assetpilotbackend.dto.technicien.TechnicienResponse;
import org.example.assetpilotbackend.service.TechnicienService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/techniciens")
@RequiredArgsConstructor
public class TechnicienController {

    private final TechnicienService technicienService;

    @PostMapping
    public TechnicienResponse ajouterTechnicien(@Valid @RequestBody TechnicienRequest request){
        return technicienService.ajouterTechnicien(request);
    }

    @GetMapping
    public Page<TechnicienResponse> getAllTechnicien(Pageable pageable){
        return technicienService.allTechniciens(pageable);
    }

    @GetMapping("/{id}")
    public TechnicienResponse checherEmployeParId(@PathVariable Long id){
        return technicienService.chercherById(id);
    }

    @PutMapping("/{id}")
    public TechnicienResponse modofierTechnicien(@PathVariable Long id, @Valid @RequestBody TechnicienRequest request){
        return technicienService.modifierTechnicien(id,request);
    }

    @DeleteMapping("/{id}")
    public void supprimerTechnicien(@PathVariable Long id){
        technicienService.supprimerTechnicien(id);
    }
}
