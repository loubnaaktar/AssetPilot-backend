package org.example.assetpilotbackend.mapper;

import org.example.assetpilotbackend.dto.categorie.CategoryRequest;
import org.example.assetpilotbackend.dto.categorie.CategoryResponse;
import org.example.assetpilotbackend.model.Categorie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapStructConfig.class)
public interface CategorieMapper {

    CategoryResponse toDto(Categorie categorie);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "equipements", ignore = true)
    Categorie toEntity(CategoryRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "equipements", ignore = true)
    void updateEntity(CategoryRequest request, @MappingTarget Categorie categorie);
}
