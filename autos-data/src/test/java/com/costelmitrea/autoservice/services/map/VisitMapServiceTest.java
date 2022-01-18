package com.costelmitrea.autoservice.services.map;

import com.costelmitrea.autoservice.model.Mechanic;
import com.costelmitrea.autoservice.model.Visit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class VisitMapServiceTest {

    VisitMapService visitMapService;
    final Long visitId = 1L, visit2Id = 2L;
    final String visitDescription = "Change tyres";

    @BeforeEach
    void setUp() {
        visitMapService = new VisitMapService(new CarMapService(), new CarTypeMapService(),
                new ClientMapService(new CarTypeMapService(), new CarMapService()), new MechanicMapService(new SpecialtyMapService()));
        visitMapService.save(Visit.builder().description(visitDescription).mechanic(new Mechanic()).build());
    }

    @Test
    void findById() {
        Visit visit = visitMapService.findById(visitId);
        assertEquals(visitId, visit.getId());
    }

    @Test
    void save() {
        Visit visit2 = Visit.builder().id(visit2Id).mechanic(new Mechanic()).build();
        Visit savedVisit = visitMapService.save(visit2);
        assertEquals(visit2Id, savedVisit.getId());
    }

    @Test
    void findAll() {
        Set<Visit> visitsSet = visitMapService.findAll();
        assertEquals(1, visitsSet.size());
    }

    @Test
    void delete() {
        visitMapService.delete(visitMapService.findById(visitId));
        assertEquals(0, visitMapService.findAll().size());
    }

    @Test
    void deleteById() {
        visitMapService.deleteById(visitId);
        assertEquals(0, visitMapService.findAll().size());
    }

    @Test
    void saveNoId() {
        Visit savedVisit = visitMapService.save(Visit.builder().mechanic(new Mechanic()).build());
        assertNotNull(savedVisit);
        assertNotNull(savedVisit.getId());
    }
}