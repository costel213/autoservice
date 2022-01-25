package com.costelmitrea.autoservice.services.map;

import com.costelmitrea.autoservice.model.CarType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CarTypeMapServiceTest {

    CarTypeMapService carTypeMapService;
    final Long carTypeId = 1L, carType2Id = 2L;
    final String name = "sport car";

    @BeforeEach
    void setUp() {
        carTypeMapService = new CarTypeMapService();
        carTypeMapService.save(CarType.builder().name(name).build());
    }

    @Test
    void findById() {
        CarType carType = carTypeMapService.findById(carTypeId);
        assertEquals(carTypeId, carType.getId());
    }

    @Test
    void save() {
        CarType carType = CarType.builder().build();
        CarType savedCarType = carTypeMapService.save(carType);
        assertEquals(carType2Id, savedCarType.getId());
    }

    @Test
    void findAll() {
        Set<CarType> carTypeSet = carTypeMapService.findAll();
        assertEquals(1, carTypeSet.size());
    }

    @Test
    void delete() {
        carTypeMapService.delete(carTypeMapService.findById(carTypeId));
        assertEquals(0, carTypeMapService.findAll().size());
    }

    @Test
    void deleteById() {
        carTypeMapService.deleteById(carTypeId);
        assertEquals(0, carTypeMapService.findAll().size());
    }

    @Test
    void saveNoId() {
        CarType savedCarType = carTypeMapService.save(CarType.builder().build());
        assertNotNull(savedCarType);
        assertNotNull(savedCarType.getId());
    }
}