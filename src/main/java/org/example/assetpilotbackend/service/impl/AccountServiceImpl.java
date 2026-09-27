package org.example.assetpilotbackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.assetpilotbackend.service.AccountService;
import org.example.assetpilotbackend.service.EmailService;
import org.example.assetpilotbackend.service.PasswordGeneratorService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final PasswordGeneratorService passwordGeneratorService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String encoderEtEnvoyer(String email) {
        String motDePasseGenere = passwordGeneratorService.generateRawPassword();
        emailService.sendPassword(email, motDePasseGenere);
        return passwordEncoder.encode(motDePasseGenere);
    }
}
