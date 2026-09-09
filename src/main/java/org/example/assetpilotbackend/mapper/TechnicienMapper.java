package org.example.assetpilotbackend.mapper;

import org.example.assetpilotbackend.dto.technicien.TechnicienRequest;
import org.example.assetpilotbackend.dto.technicien.TechnicienResponse;
import org.example.assetpilotbackend.model.Technicien;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TechnicienMapper {

    TechnicienResponse toDTO(Technicien technicien);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "incidentsTraites", ignore = true)
    Technicien toEntity(TechnicienRequest request);
}
