package org.example.assetpilotbackend.mapper;

import org.example.assetpilotbackend.dto.categorie.CategorieRequest;
import org.example.assetpilotbackend.dto.categorie.CategorieResponse;
import org.example.assetpilotbackend.model.Categorie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategorieMapper {

    CategorieResponse toDto(Categorie categorie);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "equipements", ignore = true)
    Categorie toEntity(CategorieRequest request);

}
