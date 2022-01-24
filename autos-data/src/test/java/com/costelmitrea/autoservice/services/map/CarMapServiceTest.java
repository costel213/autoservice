package com.costelmitrea.autoservice.services.map;

import com.costelmitrea.autoservice.model.Car;
import com.costelmitrea.autoservice.model.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CarMapServiceTest {

    CarMapService carMapService;
    final Long car1Id = 1L, car2Id = 2L;
    final String model = "Dacia";
    Car car;
    Client client;

    @BeforeEach
    void setUp() {
        carMapService = new CarMapService();
        car = new Car();
        car.setModel("Ford");
        car.setDateOfProduction(LocalDate.now());

        client = Client.builder().id(1L).build();
        client.addCar(car);
        carMapService.save(Car.builder().model(model).build());
        carMapService.save(car);
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
        assertEquals(2, carSet.size());
    }

    @Test
    void deleteById() {
        client.getCarsInternal().remove(car);
        carMapService.deleteById(car.getId());
        assertEquals(1, carMapService.findAll().size());
    }

    @Test
    void delete() {
        client.getCarsInternal().remove(car);
        carMapService.delete(car);
        assertEquals(1, carMapService.findAll().size());
    }

    @Test
    void saveNoId() {
        Car savedCar = carMapService.save(Car.builder().build());
        assertNotNull(savedCar);
        assertNotNull(savedCar.getId());
    }
}