package org.example.assetpilotbackend.service;

import org.example.assetpilotbackend.dto.incident.IncidentRequest;
import org.example.assetpilotbackend.dto.incident.IncidentResponse;
import org.example.assetpilotbackend.dto.incident.IncidentUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IncidentService {
    IncidentResponse declarerIncident(long employeId,IncidentRequest request);
    IncidentResponse assignerTechnicien(long incidentId, long technicienId);
    IncidentResponse mettreAJourIncident(long incidentId, IncidentUpdateRequest request);
    Page<IncidentResponse> allIncidents(Pageable pageable);
    Page<IncidentResponse> incidentsParEmploye(long employeId, Pageable pageable);
    Page<IncidentResponse> incidentsParTechnicien(long technicienId, Pageable pageable);
    IncidentResponse chercherById(long id);

    byte[] exporterExcel();
}