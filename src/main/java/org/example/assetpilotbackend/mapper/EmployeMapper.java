package org.example.assetpilotbackend.mapper;

import org.example.assetpilotbackend.dto.employe.EmployeRequest;
import org.example.assetpilotbackend.dto.employe.EmployeResponse;
import org.example.assetpilotbackend.model.Employe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")

public interface EmployeMapper {

    EmployeResponse toDTO(Employe employe);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "affectations", ignore = true)
    @Mapping(target = "incidentsDeclares", ignore = true)
    Employe toEntity(EmployeRequest request);
}
