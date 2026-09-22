package org.example.assetpilotbackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.assetpilotbackend.dto.incident.IncidentRequest;
import org.example.assetpilotbackend.dto.incident.IncidentResponse;
import org.example.assetpilotbackend.dto.incident.IncidentUpdateRequest;
import org.example.assetpilotbackend.enums.NiveauUrgence;
import org.example.assetpilotbackend.enums.Role;
import org.example.assetpilotbackend.enums.StatutIncident;
import org.example.assetpilotbackend.model.Utilisateur;
import org.example.assetpilotbackend.service.IncidentService;
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
@RequestMapping("/api/incidents")
@RequiredArgsConstructor
public class IncidentController {

    private final IncidentService incidentService;

    @PostMapping("/declarer/{employeId}")
    @PreAuthorize("hasRole('EMPLOYE')")
    public IncidentResponse declarerIncident(@PathVariable long employeId,
                                             @AuthenticationPrincipal Utilisateur utilisateur,
                                             @Valid @RequestBody IncidentRequest request) {
        verifierEmployeAuthorise(employeId, utilisateur);
        return incidentService.declarerIncident(employeId, request);
    }

    @PutMapping("/{incidentId}/assigner/{technicienId}")
    @PreAuthorize("hasRole('ADMIN')")
    public IncidentResponse assignerTechnicien(@PathVariable long incidentId, @PathVariable long technicienId) {
        return incidentService.assignerTechnicien(incidentId, technicienId);
    }

    @PutMapping("/{incidentId}")
    @PreAuthorize("hasRole('TECHNICIEN')")
    public IncidentResponse mettreAJourIncident(@PathVariable long incidentId,
                                                @Valid @RequestBody IncidentUpdateRequest request,
                                                @AuthenticationPrincipal Utilisateur utilisateur) {
        verifierTechnicienAuthorise(incidentId, utilisateur);
        return incidentService.mettreAJourIncident(incidentId, request);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Page<IncidentResponse> allIncidents(
            @RequestParam(required = false) String mot,
            @RequestParam(required = false) StatutIncident statut,
            @RequestParam(required = false) NiveauUrgence niveauUrgence,
            @RequestParam(required = false) Boolean nonAssigne,
            Pageable pageable) {
        return incidentService.allIncidents(mot, statut, niveauUrgence, nonAssigne, pageable);
    }

    @GetMapping("/employe/{employeId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYE')")
    public Page<IncidentResponse> incidentsParEmploye(@PathVariable long employeId,
                                                      @AuthenticationPrincipal Utilisateur utilisateur,
                                                      Pageable pageable) {
        verifierEmployeAuthorise(employeId, utilisateur);
        return incidentService.incidentsParEmploye(employeId, pageable);
    }

    @GetMapping("/technicien/{technicienId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Page<IncidentResponse> incidentsParTechnicien(@PathVariable long technicienId, Pageable pageable) {
        return incidentService.incidentsParTechnicien(technicienId, pageable);
    }

    @GetMapping("/mes-incidents")
    @PreAuthorize("hasRole('TECHNICIEN')")
    public Page<IncidentResponse> mesIncidents(@AuthenticationPrincipal Utilisateur utilisateur, Pageable pageable) {
        return incidentService.incidentsParTechnicien(utilisateur.getId(), pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TECHNICIEN')")
    public IncidentResponse chercherById(@PathVariable long id, @AuthenticationPrincipal Utilisateur utilisateur) {
        verifierTechnicienAuthorise(id, utilisateur);
        return incidentService.chercherById(id);
    }

    @GetMapping("/excel")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<byte[]> exportExcel() {
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .header("Content-Disposition", "attachment; filename=incidents.xlsx")
                .body(incidentService.exporterExcel());
    }

    private void verifierTechnicienAuthorise(long incidentId, Utilisateur utilisateur) {
        if (utilisateur.getRole() == Role.TECHNICIEN) {
            IncidentResponse incident = incidentService.chercherById(incidentId);
            if (incident.getTraiteParId() == null || !incident.getTraiteParId().equals(utilisateur.getId())) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Cet incident ne vous est pas attribué");
            }
        }
    }

    private void verifierEmployeAuthorise(long employeId, Utilisateur utilisateur) {
        if (utilisateur.getRole() == Role.EMPLOYE && utilisateur.getId() != employeId) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "Vous ne pouvez accéder qu'à vos propres données");
        }
    }
}