package com.costelmitrea.autoservice.services.springdatajpa;

import com.costelmitrea.autoservice.model.Visit;
import com.costelmitrea.autoservice.repositories.VisitRepository;
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
class VisitSDJpaServiceTest {

    public static final String DESCRIPTION = "Add engine oil";

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitSDJpaService service;

    Visit returnVisit;

    @BeforeEach
    void setUp() {
        returnVisit = Visit.builder().id(1L).description(DESCRIPTION).build();
    }

    @Test
    void findById() {
        when(visitRepository.findById(anyLong())).thenReturn(Optional.of(returnVisit));
        Visit visit = service.findById(1L);
        assertNotNull(visit);
    }

    @Test
    void findByIdNotFound() {
        when(visitRepository.findById(anyLong())).thenReturn(Optional.empty());
        Visit visit = service.findById(1L);
        assertNull(visit);
    }

    @Test
    void save() {
        Visit visitToSave = Visit.builder().id(1L).build();
        when(visitRepository.save(any())).thenReturn(returnVisit);
        Visit savedVisit = service.save(visitToSave);
        assertNotNull(savedVisit);
        verify(visitRepository).save(any());
    }

    @Test
    void findAll() {
        Set<Visit> returnVisitSet = new HashSet<>();
        returnVisitSet.add(Visit.builder().id(1L).build());
        returnVisitSet.add(Visit.builder().id(2L).build());
        when(visitRepository.findAll()).thenReturn(returnVisitSet);
        Set<Visit> visits = service.findAll();
        assertNotNull(visits);
        assertEquals(2, visits.size());
    }

    @Test
    void delete() {
        service.delete(returnVisit);
        verify(visitRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(visitRepository, times(1)).deleteById(anyLong());

    }
}