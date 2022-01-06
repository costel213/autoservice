package com.costelmitrea.autoservice.services.springdatajpa;

import com.costelmitrea.autoservice.model.Car;
import com.costelmitrea.autoservice.repositories.CarRepository;
import com.costelmitrea.autoservice.services.CarService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CarSDJpaService implements CarService {

    private final CarRepository carRepository;

    public CarSDJpaService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Car findById(Long aLong) {
        return carRepository.findById(aLong).orElse(null);
    }

    @Override
    public Car save(Car object) {
        return carRepository.save(object);
    }

    @Override
    public Set<Car> findAll() {
        Set<Car> cars = new HashSet<>();
        carRepository.findAll().forEach(cars::add);
        return cars;
    }

    @Override
    public void delete(Car object) {
        carRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        carRepository.deleteById(aLong);
    }
}
