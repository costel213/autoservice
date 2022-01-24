package com.costelmitrea.autoservice.services.map;

import com.costelmitrea.autoservice.model.Experience;
import com.costelmitrea.autoservice.model.Mechanic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ExperienceMapServiceTest {

    ExperienceMapService experienceMapService;
    Long experienceId = 1L, experience2Id = 2L;
    String position = "Engine Repair Mechanic";
    Mechanic mechanic;
    Experience experience;

    @BeforeEach
    void setUp() {
        mechanic = Mechanic.builder().id(1L).build();
        experience = new Experience();
        experience.setTimeInterval("02/2016-10/2021");
        experience.setPosition("Heating System mechanic");
        mechanic.addExperience(experience);
        experienceMapService = new ExperienceMapService();
        experienceMapService.save(Experience.builder().position(position).build());
        experienceMapService.save(experience);
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
        assertEquals(2, experienceSet.size());
    }

    @Test
    void deleteById() {
        mechanic.getExperienceInternal().remove(experience);
        experienceMapService.deleteById(experience.getId());
        assertEquals(1, experienceMapService.findAll().size());
    }

    @Test
    void delete() {
        mechanic.getExperienceInternal().remove(experience);
        experienceMapService.delete(experience);
        assertEquals(1, experienceMapService.findAll().size());
    }

    @Test
    void saveNoId() {
        Experience savedExperience = experienceMapService.save(Experience.builder().build());
        assertNotNull(savedExperience);
        assertNotNull(savedExperience.getId());
    }
}