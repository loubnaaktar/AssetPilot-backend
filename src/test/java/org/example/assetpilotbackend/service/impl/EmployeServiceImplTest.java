package org.example.assetpilotbackend.service.impl;

import org.example.assetpilotbackend.dto.employe.EmployeRequest;
import org.example.assetpilotbackend.dto.employe.EmployeResponse;
import org.example.assetpilotbackend.mapper.EmployeMapper;
import org.example.assetpilotbackend.model.Employe;
import org.example.assetpilotbackend.repository.EmployeRepository;
import org.example.assetpilotbackend.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeServiceImplTest {

    @Mock
    private EmployeRepository repo;
    @Mock
    private EmployeMapper mapper;
    @Mock
    private AccountService accountService;
    @InjectMocks
    private EmployeServiceImpl employeService;

    @Test
    void modifierEmployeMatricule() {
        long id = 1L;

        Employe employe = new Employe();
        employe.setMatricule("EMP-001");

        EmployeRequest request = new EmployeRequest();
        request.setNom("Dupont");
        request.setPrenom("Jean");
        request.setEmail("jean.dupont@example.com");
        request.setMatricule("EMP-100");

        EmployeResponse expectedResponse = new EmployeResponse();

        when(repo.findById(id)).thenReturn(Optional.of(employe));
        when(repo.save(employe)).thenReturn(employe);
        when(mapper.toDTO(employe)).thenReturn(expectedResponse);

        employeService.modifierEmploye(id, request);

        assertEquals("EMP-100", employe.getMatricule());
        assertEquals("Dupont", employe.getNom());
    }
}