package com.costelmitrea.autoservice.services.map;

import com.costelmitrea.autoservice.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CarMapServiceTest {

    CarMapService carMapService;
    final Long car1Id = 1L, car2Id = 2L;
    final String model = "Dacia";

    @BeforeEach
    void setUp() {
        carMapService = new CarMapService();
        carMapService.save(Car.builder().model(model).build());
    }

    @Test
    void findById() {
        Car car = carMapService.findById(car1Id);
        assertEquals(car1Id, car.getId());
    }

    @Test
    void save() {
        Car car = Car.builder().id(car2Id).build();
        Car savedCar = carMapService.save(car);
        assertEquals(car2Id, savedCar.getId());
    }

    @Test
    void findAll() {
        Set<Car> carSet = carMapService.findAll();
        assertEquals(1, carSet.size());
    }

    @Test
    void deleteById() {
        carMapService.deleteById(car1Id);
        assertEquals(0, carMapService.findAll().size());
    }

    @Test
    void delete() {
        carMapService.delete(carMapService.findById(car1Id));
        assertEquals(0, carMapService.findAll().size());
    }

    @Test
    void saveNoId() {
        Car savedCar = carMapService.save(Car.builder().build());
        assertNotNull(savedCar);
        assertNotNull(savedCar.getId());
    }
}