package com.costelmitrea.autoservice.services.springdatajpa;

import com.costelmitrea.autoservice.model.Client;
import com.costelmitrea.autoservice.repositories.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientSDJpaServiceTest {

    public static final String LAST_NAME = "Thompson";
    public static final String LAST_NAME2 = "Thomas";

    @Mock
    ClientRepository clientRepository;

    @InjectMocks
    ClientSDJpaService service;

    Client returnClient;

    @BeforeEach
    void setUp() {
        returnClient = Client.builder().id(1L).lastName(LAST_NAME).build();
    }

    @Test
    void findByLastName() {
        when(clientRepository.findByLastName(any())).thenReturn(returnClient);
        Client client = service.findByLastName(LAST_NAME);
        assertEquals(LAST_NAME, client.getLastName());
    }

    @Test
    void findAllByLastNameLike() {
        Set<Client> returnClientSet = new HashSet<>();
        returnClientSet.add(Client.builder().id(1L).lastName(LAST_NAME).build());
        returnClientSet.add(Client.builder().id(2L).lastName(LAST_NAME2).build());
        when(clientRepository.findAllByLastNameLike(any())).thenReturn(List.copyOf(returnClientSet));
        List<Client> clients = clientRepository.findAllByLastNameLike(any());
        assertNotNull(clients);
        assertEquals(2, clients.size());
    }

    @Test
    void findById() {
        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(returnClient));
        Client client = service.findById(1L);
        assertNotNull(client);
    }

    @Test
    void findByIdNotFound() {
        when(clientRepository.findById(anyLong())).thenReturn(Optional.empty());
        Client client = service.findById(1L);
        assertNull(client);
    }

    @Test
    void save() {
        Client clientToSave = Client.builder().id(1L).build();
        when(clientRepository.save(any())).thenReturn(returnClient);
        Client savedClient = service.save(clientToSave);
        assertNotNull(savedClient);
        verify(clientRepository).save(any());
    }

    @Test
    void findAll() {
        Set<Client> returnClientSet = new HashSet<>();
        returnClientSet.add(Client.builder().id(1L).build());
        returnClientSet.add(Client.builder().id(2L).build());
        when(clientRepository.findAll()).thenReturn(returnClientSet);
        Set<Client> clients = service.findAll();
        assertNotNull(clients);
        assertEquals(2, clients.size());
    }

    @Test
    void delete() {
        service.delete(returnClient);
        verify(clientRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(clientRepository).deleteById(anyLong());
    }
}