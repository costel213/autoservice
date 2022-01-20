package com.costelmitrea.autoservice.controller;

import com.costelmitrea.autoservice.model.Client;
import com.costelmitrea.autoservice.services.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

@ExtendWith(MockitoExtension.class)
class ClientControllerTest {

    @Mock
    ClientService clientService;

    @InjectMocks
    ClientController clientController;

    Set<Client> clients;
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @Test
    void initCreationForm() {
    }

    @Test
    void processCreationForm() {
    }

    @Test
    void initFindForm() {
    }

    @Test
    void processFindForm() {
    }

    @Test
    void initUpdateForm() {
    }

    @Test
    void processUpdateClientForm() {
    }

    @Test
    void initDeleteForm() {
    }

    @Test
    void deleteClient() {
    }

    @Test
    void showClient() {
    }

    @Test
    void showClients() {
    }
}