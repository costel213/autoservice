package com.costelmitrea.autoservice.services.map;

import com.costelmitrea.autoservice.model.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ClientMapServiceTest {

    ClientMapService clientMapService;
    final Long clientId = 1L, client2Id = 2L;
    final String lastName = "Watson";

    @BeforeEach
    void setUp() {
        clientMapService = new ClientMapService(new CarTypeMapService(), new CarMapService());
        clientMapService.save(Client.builder().lastName(lastName).build());
    }

    @Test
    void findByLastName() {
        Client client = clientMapService.findByLastName(lastName);
        assertNotNull(client);
        assertEquals(clientId, client.getId());
    }

    @Test
    void findByLastNameNotFound() {
        Client client = clientMapService.findByLastName("Watson");
        assertNotNull(client);
    }

    @Test
    void findAllByLastNameLike() {
        List<Client> clientsList = clientMapService.findAllByLastNameLike("%" + "wat" + "%");
        assertEquals(1, clientsList.size());
    }

    @Test
    void findById() {
        Client client = clientMapService.findById(clientId);
        assertEquals(clientId, client.getId());
    }

    @Test
    void save() {
        Client client2 = Client.builder().id(client2Id).build();
        Client savedClient = clientMapService.save(client2);
        assertEquals(client2Id, savedClient.getId());
    }

    @Test
    void saveNoId() {
        Client savedClient = clientMapService.save(Client.builder().build());
        assertNotNull(savedClient);
        assertNotNull(savedClient.getId());
    }

    @Test
    void findAll() {
        Set<Client> clientsSet = clientMapService.findAll();
        assertEquals(1, clientsSet.size());
    }

    @Test
    void delete() {
        clientMapService.delete(clientMapService.findById(clientId));
        assertEquals(0, clientMapService.findAll().size());
    }

    @Test
    void deleteById() {
        clientMapService.deleteById(clientId);
        assertEquals(0, clientMapService.findAll().size());
    }
}