package com.costelmitrea.autoservice.services.map;

import com.costelmitrea.autoservice.model.Mechanic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MechanicMapServiceTest {

    MechanicMapService mechanicMapService;
    final Long mechanicId = 1L, mechanic2Id = 2L;
    final String lastName = "Marcus";

    @BeforeEach
    void setUp() {
        mechanicMapService = new MechanicMapService(new SpecialtyMapService());
        mechanicMapService.save(Mechanic.builder().lastName(lastName).build());
    }

    @Test
    void findById() {
        Mechanic mechanic = mechanicMapService.findById(mechanicId);
        assertEquals(mechanicId, mechanic.getId());
    }

    @Test
    void findByLastName() {
        Mechanic mechanic = mechanicMapService.findByLastName(lastName);
        assertNotNull(mechanic);
        assertEquals(mechanicId, mechanic.getId());
    }

    @Test
    void save() {
        Mechanic mechanic = Mechanic.builder().id(mechanic2Id).build();
        Mechanic savedMechanic = mechanicMapService.save(mechanic);
        assertEquals(mechanic2Id, savedMechanic.getId());
    }

    @Test
    void findAll() {
        Set<Mechanic> mechanicsSet = mechanicMapService.findAll();
        assertEquals(1, mechanicsSet.size());
    }

    @Test
    void delete() {
        mechanicMapService.delete(mechanicMapService.findById(mechanicId));
        assertEquals(0, mechanicMapService.findAll().size());
    }

    @Test
    void deleteById() {
        mechanicMapService.deleteById(mechanicId);
        assertEquals(0, mechanicMapService.findAll().size());
    }

    @Test
    void saveNoId() {
        Mechanic savedMechanic = mechanicMapService.save(Mechanic.builder().build());
        assertNotNull(savedMechanic);
        assertNotNull(savedMechanic.getId());
    }
}