package org.example.assetpilotbackend.mapper;

import org.example.assetpilotbackend.dto.incident.IncidentRequest;
import org.example.assetpilotbackend.dto.incident.IncidentResponse;
import org.example.assetpilotbackend.dto.incident.IncidentUpdateRequest;
import org.example.assetpilotbackend.model.Incident;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.BeanMapping;

@Mapper(componentModel = "spring")
public interface IncidentMapper {

    @Mapping(source = "declarePar.id", target = "declareParId")
    @Mapping(target = "declareParNom", expression = "java(nomDeclarePar(incident))")
    @Mapping(source = "traitePar.id", target = "traiteParId")
    @Mapping(target = "traiteParNom", expression = "java(nomTraitePar(incident))")
    @Mapping(source = "equipement.id", target = "equipementId")
    @Mapping(source = "equipement.numeroSerie", target = "equipementNumeroSerie")
    @Mapping(source = "equipement.modele", target = "equipementModele")
    IncidentResponse toDTO(Incident incident);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "statut", ignore = true)
    @Mapping(target = "dateDeclaration", ignore = true)
    @Mapping(target = "dateResolution", ignore = true)
    @Mapping(target = "rapportIntervention", ignore = true)
    @Mapping(target = "declarePar", ignore = true)
    @Mapping(target = "declareParNom", ignore = true)
    @Mapping(target = "traitePar", ignore = true)
    @Mapping(target = "traiteParNom", ignore = true)
    @Mapping(target = "equipement", ignore = true)
    Incident toEntity(IncidentRequest request);

    default String nomDeclarePar(Incident incident) {
        if (incident.getDeclarePar() != null) {
            return incident.getDeclarePar().getNom();
        }
        return incident.getDeclareParNom();
    }

    default String nomTraitePar(Incident incident) {
        if (incident.getTraitePar() != null) {
            return incident.getTraitePar().getNom();
        }
        return incident.getTraiteParNom();
    }

}
