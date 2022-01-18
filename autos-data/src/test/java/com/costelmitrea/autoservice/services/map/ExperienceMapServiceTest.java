package com.costelmitrea.autoservice.services.map;

import com.costelmitrea.autoservice.model.Experience;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ExperienceMapServiceTest {

    ExperienceMapService experienceMapService;
    Long experienceId = 1L, experience2Id = 2L;
    String position = "Engine Repair Mechanic";

    @BeforeEach
    void setUp() {
        experienceMapService = new ExperienceMapService();
        experienceMapService.save(Experience.builder().position(position).build());
    }

    @Test
    void findById() {
        Experience experience = experienceMapService.findById(experienceId);
        assertEquals(experienceId, experience.getId());
    }

    @Test
    void save() {
        Experience experience = Experience.builder().id(experience2Id).build();
        Experience savedExperience = experienceMapService.save(experience);
        assertEquals(experience2Id, savedExperience.getId());
    }

    @Test
    void findAll() {
        Set<Experience> experienceSet = experienceMapService.findAll();
        assertEquals(1, experienceSet.size());
    }

    @Test
    void deleteById() {
        experienceMapService.deleteById(experienceId);
        assertEquals(0, experienceMapService.findAll().size());
    }

    @Test
    void delete() {
        experienceMapService.deleteById(experienceId);
        assertEquals(0, experienceMapService.findAll().size());
    }

    @Test
    void saveNoId() {
        Experience savedExperience = experienceMapService.save(Experience.builder().build());
        assertNotNull(savedExperience);
        assertNotNull(savedExperience.getId());
    }
}