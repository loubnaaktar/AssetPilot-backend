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

@Mapper(config = MapStructConfig.class)
public interface IncidentMapper {

    @Mapping(source = "declarePar.id", target = "declareParId")
    @Mapping(source = "declarePar.nom", target = "declareParNom")
    @Mapping(source = "traitePar.id", target = "traiteParId")
    @Mapping(source = "traitePar.nom", target = "traiteParNom")
    @Mapping(source = "equipement.id", target = "equipementId")
    @Mapping(source = "equipement.numeroSerie", target = "equipementNumeroSerie")
    IncidentResponse toDto(Incident incident);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "statut", ignore = true)
    @Mapping(target = "dateDeclaration", ignore = true)
    @Mapping(target = "dateResolution", ignore = true)
    @Mapping(target = "rapportIntervention", ignore = true)
    @Mapping(target = "declarePar", ignore = true)
    @Mapping(target = "traitePar", ignore = true)
    @Mapping(target = "equipement", ignore = true)
    Incident toEntity(IncidentRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dateDeclaration", ignore = true)
    @Mapping(target = "dateResolution", ignore = true)
    @Mapping(target = "declarePar", ignore = true)
    @Mapping(target = "traitePar", ignore = true)
    @Mapping(target = "equipement", ignore = true)
    void updateEntity(IncidentUpdateRequest request, @MappingTarget Incident incident);
}
