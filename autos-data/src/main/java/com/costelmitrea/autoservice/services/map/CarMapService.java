package com.costelmitrea.autoservice.services.map;

import com.costelmitrea.autoservice.model.Car;
import com.costelmitrea.autoservice.services.CarService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile("map")
public class CarMapService extends AbstractMapService<Car, Long> implements CarService {

    @Override
    public Car findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Car save(Car object) {
        return super.save(object);
    }

    @Override
    public Set<Car> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Car object) {
        super.delete(object);
    }
}
