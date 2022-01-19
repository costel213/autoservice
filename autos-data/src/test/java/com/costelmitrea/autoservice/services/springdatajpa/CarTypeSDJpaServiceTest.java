package com.costelmitrea.autoservice.services.springdatajpa;

import com.costelmitrea.autoservice.model.CarType;
import com.costelmitrea.autoservice.repositories.CarTypeRepository;
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
class CarTypeSDJpaServiceTest {

    public static final String NAME = "family car";

    @Mock
    CarTypeRepository carTypeRepository;

    @InjectMocks
    CarTypeSDJpaService service;

    CarType returnCarType;

    @BeforeEach
    void setUp() {
        returnCarType = CarType.builder().id(1L).name(NAME).build();
    }

    @Test
    void findById() {
        when(carTypeRepository.findById(anyLong())).thenReturn(Optional.of(returnCarType));
        CarType carType = service.findById(1L);
        assertNotNull(carType);
    }

    @Test
    void findByIdNotFound() {
        when(carTypeRepository.findById(anyLong())).thenReturn(Optional.empty());
        CarType carType = service.findById(1L);
        assertNull(carType);
    }

    @Test
    void save() {
        CarType carTypeToSave = CarType.builder().id(1L).build();
        when(carTypeRepository.save(any())).thenReturn(returnCarType);
        CarType savedCarType = service.save(carTypeToSave);
        assertNotNull(savedCarType);
        verify(carTypeRepository).save(any());
    }

    @Test
    void findAll() {
        Set<CarType> returnCarTypeSet = new HashSet<>();
        returnCarTypeSet.add(CarType.builder().id(1L).build());
        returnCarTypeSet.add(CarType.builder().id(2L).build());
        when(carTypeRepository.findAll()).thenReturn(returnCarTypeSet);
        Set<CarType> carTypes = service.findAll();
        assertNotNull(carTypes);
        assertEquals(2, carTypes.size());
    }

    @Test
    void delete() {
        service.delete(returnCarType);
        verify(carTypeRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(carTypeRepository).deleteById(anyLong());
    }
}