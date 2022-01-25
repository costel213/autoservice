package com.costelmitrea.autoservice.services.map;

import com.costelmitrea.autoservice.model.Specialty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SpecialtyMapServiceTest {

    SpecialtyMapService specialtyMapService;
    final Long specialtyId = 1L, specialty2Id = 2L;
    final String name = "Engine Repair";

    @BeforeEach
    void setUp() {
        specialtyMapService = new SpecialtyMapService();
        specialtyMapService.save(Specialty.builder().name(name).build());
    }

    @Test
    void findById() {
        Specialty specialty = specialtyMapService.findById(specialtyId);
        assertEquals(specialtyId, specialty.getId());
    }

    @Test
    void save() {
        Specialty specialty2 = Specialty.builder().build();
        Specialty savedSpecialty = specialtyMapService.save(specialty2);
        assertEquals(specialty2Id, savedSpecialty.getId());
    }

    @Test
    void findAll() {
        Set<Specialty> specialtiesSet = specialtyMapService.findAll();
        assertEquals(1, specialtiesSet.size());
    }

    @Test
    void delete() {
        specialtyMapService.delete(specialtyMapService.findById(specialtyId));
        assertEquals(0, specialtyMapService.findAll().size());
    }

    @Test
    void deleteById() {
        specialtyMapService.deleteById(specialtyId);
        assertEquals(0, specialtyMapService.findAll().size());
    }

    @Test
    void saveNoId() {
        Specialty savedSpecialty = specialtyMapService.save(Specialty.builder().build());
        assertNotNull(savedSpecialty);
        assertNotNull(savedSpecialty.getId());
    }
}