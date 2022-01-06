package com.costelmitrea.autoservice.repositories;

import com.costelmitrea.autoservice.model.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long> {
}
