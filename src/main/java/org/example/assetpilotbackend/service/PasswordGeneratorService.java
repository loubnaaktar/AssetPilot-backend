package org.example.assetpilotbackend.service;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.stereotype.Service;

@Service
public class PasswordGeneratorService {

    private final PasswordGenerator passwordGenerator;

    public PasswordGeneratorService() {
        this.passwordGenerator = new PasswordGenerator();
    }

    public String generateRawPassword() {
        return passwordGenerator.generatePassword(
                10,
                new CharacterRule(EnglishCharacterData.LowerCase, 2),
                new CharacterRule(EnglishCharacterData.UpperCase, 2),
                new CharacterRule(EnglishCharacterData.Digit, 2)
        );
    }
}