package org.example.assetpilotbackend.mapper;

import org.example.assetpilotbackend.dto.auth.AuthResponse;
import org.example.assetpilotbackend.model.Utilisateur;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    AuthResponse toDto(String token, Utilisateur utilisateur);
}
