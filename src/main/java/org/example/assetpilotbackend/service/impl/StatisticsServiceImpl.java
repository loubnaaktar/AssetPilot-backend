package org.example.assetpilotbackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.assetpilotbackend.dto.statistiques.StatisticsResponse;
import org.example.assetpilotbackend.enums.StatutEquipement;
import org.example.assetpilotbackend.enums.StatutIncident;
import org.example.assetpilotbackend.repository.EquipementRepository;
import org.example.assetpilotbackend.repository.EmployeRepository;
import org.example.assetpilotbackend.repository.IncidentRepository;
import org.example.assetpilotbackend.service.StatisticsService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class StatisticsServiceImpl implements StatisticsService {
    private final EquipementRepository equipementRepository;
    private final EmployeRepository employeRepository;
    private final IncidentRepository incidentRepository;
    @Override
    @Cacheable("statistiques")
    public StatisticsResponse getStatistiquesGlobales() {
        long totalEquipements = equipementRepository.count();
        long equipementsEnStock = equipementRepository.countByStatut(StatutEquipement.EN_STOCK);
        long equipementsAffectes = equipementRepository.countByStatut(StatutEquipement.AFFECTE);
        long equipementsEnPanne = equipementRepository.countByStatut(StatutEquipement.EN_PANNE);
        long totalEmployes = employeRepository.count();
        long totalIncidents = incidentRepository.count();
        long incidentsOuverts = incidentRepository.countByStatut(StatutIncident.OUVERT);

        StatisticsResponse response = new StatisticsResponse();
        response.setTotalEquipements(totalEquipements);
        response.setEquipementsEnStock(equipementsEnStock);
        response.setEquipementsAffectes(equipementsAffectes);
        response.setEquipementsEnPanne(equipementsEnPanne);
        response.setTotalEmployes(totalEmployes);
        response.setTotalIncidents(totalIncidents);
        response.setIncidentsOuverts(incidentsOuverts);

        return response;
    }
}
