package org.example.assetpilotbackend.service.impl;

import org.example.assetpilotbackend.service.PasswordGeneratorService;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.stereotype.Service;

@Service
public class PasswordGeneratorServiceImpl implements PasswordGeneratorService {

    private final PasswordGenerator passwordGenerator;

    public PasswordGeneratorServiceImpl() {
        this.passwordGenerator = new PasswordGenerator();
    }

    @Override
    public String generateRawPassword() {
        return passwordGenerator.generatePassword(
                10,
                new CharacterRule(EnglishCharacterData.LowerCase, 2),
                new CharacterRule(EnglishCharacterData.UpperCase, 2),
                new CharacterRule(EnglishCharacterData.Digit, 2)
        );
    }
}