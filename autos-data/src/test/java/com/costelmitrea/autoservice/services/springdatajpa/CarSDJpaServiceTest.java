package com.costelmitrea.autoservice.services.springdatajpa;

import com.costelmitrea.autoservice.model.Car;
import com.costelmitrea.autoservice.repositories.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

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
    void save() {
    }

    @Test
    void findAll() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteById() {
    }
}