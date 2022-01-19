package com.costelmitrea.autoservice.services.springdatajpa;

import com.costelmitrea.autoservice.model.Experience;
import com.costelmitrea.autoservice.repositories.ExperienceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExperienceSDJpaServiceTest {

    public static final String POSITION = "Engine Repair Mechanic";

    @Mock
    ExperienceRepository experienceRepository;

    @InjectMocks
    ExperienceSDJpaService service;

    Experience returnExperience;

    @BeforeEach
    void setUp() {
        returnExperience = Experience.builder().id(1L).position(POSITION).build();
    }

    @Test
    void findById() {
        when(experienceRepository.findById(anyLong())).thenReturn(Optional.of(returnExperience));
        Experience experience = service.findById(1L);
        assertNotNull(experience);
    }

    @Test
    void findByIdNotFound() {
        when(experienceRepository.findById(anyLong())).thenReturn(Optional.empty());
        Experience experience = service.findById(1L);
        assertNull(experience);
    }

    @Test
    void save() {
        Experience experienceToSave = Experience.builder().id(1L).build();
        when(experienceRepository.save(any())).thenReturn(returnExperience);
        Experience savedExperience = service.save(experienceToSave);
        assertNotNull(savedExperience);
        verify(experienceRepository).save(any());
    }

    @Test
    void findAll() {
        Set<Experience> returnExperienceSet = new HashSet<>();
        returnExperienceSet.add(Experience.builder().id(1L).build());
        returnExperienceSet.add(Experience.builder().id(2L).build());
        when(experienceRepository.findAll()).thenReturn(returnExperienceSet);
        Set<Experience> experiences = service.findAll();
        assertNotNull(experiences);
        assertEquals(2, experiences.size());
    }

    @Test
    void delete() {
        service.delete(returnExperience);
        verify(experienceRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(experienceRepository).deleteById(anyLong());
    }
}