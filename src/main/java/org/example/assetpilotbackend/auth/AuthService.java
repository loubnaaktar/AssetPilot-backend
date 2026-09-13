package org.example.assetpilotbackend.auth;

import org.example.assetpilotbackend.dto.auth.AuthResponse;
import org.example.assetpilotbackend.dto.auth.LoginRequest;

public interface AuthService {
    AuthResponse login(LoginRequest request);
}