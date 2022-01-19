package com.costelmitrea.autoservice.services.springdatajpa;

import com.costelmitrea.autoservice.model.Car;
import com.costelmitrea.autoservice.repositories.CarRepository;
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
class CarSDJpaServiceTest {

    public static final String MODEL = "Skoda";

    @Mock
    CarRepository carRepository;

    @InjectMocks
    CarSDJpaService service;

    Car returnCar;

    @BeforeEach
    void setUp() {
        returnCar = Car.builder().id(1L).model(MODEL).build();
    }

    @Test
    void findById() {
        when(carRepository.findById(anyLong())).thenReturn(Optional.of(returnCar));
        Car car = service.findById(1L);
        assertNotNull(car);
    }

    @Test
    void findByIdNotFound() {
        when(carRepository.findById(anyLong())).thenReturn(Optional.empty());
        Car car = service.findById(1L);
        assertNull(car);
    }

    @Test
    void save() {
        Car carToSave = Car.builder().id(1L).build();
        when(carRepository.save(any())).thenReturn(returnCar);
        Car savedCar = service.save(carToSave);
        assertNotNull(savedCar);
        verify(carRepository).save(any());
    }

    @Test
    void findAll() {
        Set<Car> returnCarSet = new HashSet<>();
        returnCarSet.add(Car.builder().id(1L).build());
        returnCarSet.add(Car.builder().id(2L).build());
        when(carRepository.findAll()).thenReturn(returnCarSet);
        Set<Car> cars = service.findAll();
        assertNotNull(cars);
        assertEquals(2, cars.size());
    }

    @Test
    void delete() {
        service.delete(returnCar);
        verify(carRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(carRepository,times(1)).deleteById(anyLong());
    }
}