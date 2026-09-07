package org.example.assetpilotbackend.mapper;

import org.example.assetpilotbackend.dto.affectation.AffectationRequest;
import org.example.assetpilotbackend.dto.affectation.AffectationResponse;
import org.example.assetpilotbackend.model.Affectation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapStructConfig.class)
public interface AffectationMapper {

    @Mapping(source = "employe.id", target = "employeId")
    @Mapping(source = "employe.nom", target = "employeNom")
    @Mapping(source = "equipement.id", target = "equipementId")
    @Mapping(source = "equipement.numeroSerie", target = "equipementNumeroSerie")
    AffectationResponse toDto(Affectation affectation);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "statut", ignore = true)
    @Mapping(target = "employe", ignore = true)
    @Mapping(target = "equipement", ignore = true)
    Affectation toEntity(AffectationRequest request);
}
