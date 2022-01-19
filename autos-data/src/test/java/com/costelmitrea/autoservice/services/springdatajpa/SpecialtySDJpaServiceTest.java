package com.costelmitrea.autoservice.services.springdatajpa;

import com.costelmitrea.autoservice.model.Specialty;
import com.costelmitrea.autoservice.repositories.SpecialtyRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialtySDJpaServiceTest {

    public static final String NAME = " Engine Performance";

    @Mock
    SpecialtyRepository specialtyRepository;

    @InjectMocks
    SpecialtySDJpaService service;

    Specialty returnSpecialty;

    @BeforeEach
    void setUp() {
        returnSpecialty = Specialty.builder().id(1L).name(NAME).build();
    }

    @Test
    void findById() {
        when(specialtyRepository.findById(anyLong())).thenReturn(Optional.of(returnSpecialty));
        Specialty specialty = service.findById(1L);
        assertNotNull(specialty);
    }

    @Test
    void findByIdNotFound() {
        when(specialtyRepository.findById(anyLong())).thenReturn(Optional.empty());
        Specialty specialty = service.findById(1L);
        assertNull(specialty);
    }

    @Test
    void save() {
        Specialty specialtyToSave = Specialty.builder().id(1L).build();
        when(specialtyRepository.save(any())).thenReturn(returnSpecialty);
        Specialty savedSpecialty = service.save(specialtyToSave);
        assertNotNull(savedSpecialty);
        verify(specialtyRepository).save(any());
    }

    @Test
    void findAll() {
        Set<Specialty> returnSpecialtySet = new HashSet<>();
        returnSpecialtySet.add(Specialty.builder().id(1L).build());
        returnSpecialtySet.add(Specialty.builder().id(2L).build());
        when(specialtyRepository.findAll()).thenReturn(returnSpecialtySet);
        Set<Specialty> specialties = service.findAll();
        assertNotNull(specialties);
        assertEquals(2, specialties.size());
    }

    @Test
    void delete() {
        service.delete(returnSpecialty);
        verify(specialtyRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(specialtyRepository, times(1)).deleteById(anyLong());
    }
}