package org.example.assetpilotbackend.mapper;

import org.example.assetpilotbackend.dto.affectation.AffectationRequest;
import org.example.assetpilotbackend.dto.affectation.AffectationResponse;
import org.example.assetpilotbackend.model.Affectation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AffectationMapper {

    @Mapping(source = "employe.id", target = "employeId")
    @Mapping(target = "employeNom", expression = "java(nomEmploye(affectation))")
    @Mapping(source = "equipement.id", target = "equipementId")
    @Mapping(source = "equipement.numeroSerie", target = "equipementNumeroSerie")
    AffectationResponse toDTO(Affectation affectation);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "statut", ignore = true)
    @Mapping(target = "employe", ignore = true)
    @Mapping(target = "employeNom", ignore = true)
    @Mapping(target = "equipement", ignore = true)
    Affectation toEntity(AffectationRequest request);

    default String nomEmploye(Affectation affectation) {
        if (affectation.getEmploye() != null) {
            return affectation.getEmploye().getNom();
        }
        return affectation.getEmployeNom();
    }
}
