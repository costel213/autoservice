package com.costelmitrea.autoservice.services.springdatajpa;

import com.costelmitrea.autoservice.model.Mechanic;
import com.costelmitrea.autoservice.repositories.MechanicRepository;
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
class MechanicSDJpaServiceTest {

    public static final String LAST_NAME = "Watson";

    @Mock
    MechanicRepository mechanicRepository;

    @InjectMocks
    MechanicSDJpaService service;

    Mechanic returnMechanic;

    @BeforeEach
    void setUp() {
        returnMechanic = Mechanic.builder().id(1L).lastName(LAST_NAME).build();
    }

    @Test
    void findById() {
        when(mechanicRepository.findById(anyLong())).thenReturn(Optional.of(returnMechanic));
        Mechanic mechanic = service.findById(1L);
        assertNotNull(mechanic);
    }

    @Test
    void findByIdNotFound() {
        when(mechanicRepository.findById(anyLong())).thenReturn(Optional.empty());
        Mechanic mechanic = service.findById(1L);
        assertNull(mechanic);
    }

    @Test
    void save() {
        Mechanic mechanicToSave = Mechanic.builder().id(1L).build();
        when(mechanicRepository.save(any())).thenReturn(returnMechanic);
        Mechanic savedMechanic = service.save(mechanicToSave);
        assertNotNull(savedMechanic);
        verify(mechanicRepository).save(any());
    }

    @Test
    void findAll() {
        Set<Mechanic> returnMechanicsSet = new HashSet<>();
        returnMechanicsSet.add(Mechanic.builder().id(1L).build());
        returnMechanicsSet.add(Mechanic.builder().id(2L).build());
        when(mechanicRepository.findAll()).thenReturn(returnMechanicsSet);
        Set<Mechanic> mechanics = service.findAll();
        assertNotNull(mechanics);
        assertEquals(2, mechanics.size());

    }

    @Test
    void delete() {
        service.delete(returnMechanic);
        verify(mechanicRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(mechanicRepository).deleteById(anyLong());
    }

    @Test
    void findByLastName() {
        when(mechanicRepository.findByLastName(LAST_NAME)).thenReturn(returnMechanic);
        Mechanic mechanic = service.findByLastName(LAST_NAME);
        assertNotNull(mechanic);

    }
}