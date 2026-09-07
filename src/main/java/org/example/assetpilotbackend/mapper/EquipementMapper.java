package org.example.assetpilotbackend.mapper;

import org.example.assetpilotbackend.dto.equipement.EquipementRequest;
import org.example.assetpilotbackend.dto.equipement.EquipementResponse;
import org.example.assetpilotbackend.model.Equipement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapStructConfig.class)
public interface EquipementMapper {

    @Mapping(source = "categorie.id", target = "categorieId")
    @Mapping(source = "categorie.nom", target = "categorieNom")
    EquipementResponse toDto(Equipement equipement);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "statut", ignore = true)
    @Mapping(target = "categorie", ignore = true)
    @Mapping(target = "affectations", ignore = true)
    @Mapping(target = "incidents", ignore = true)
    Equipement toEntity(EquipementRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "statut", ignore = true)
    @Mapping(target = "categorie", ignore = true)
    @Mapping(target = "affectations", ignore = true)
    @Mapping(target = "incidents", ignore = true)
    void updateEntity(EquipementRequest request, @MappingTarget Equipement equipement);
}
