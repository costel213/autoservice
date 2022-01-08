package com.costelmitrea.autoservice.services.map;

import com.costelmitrea.autoservice.model.CarType;
import com.costelmitrea.autoservice.services.CarTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile("map")
public class CarTypeMapService extends AbstractMapService<CarType, Long> implements CarTypeService {

    @Override
    public CarType findById(Long id) {
        return super.findById(id);
    }

    @Override
    public CarType save(CarType object) {
        return super.save(object);
    }

    @Override
    public Set<CarType> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(CarType object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
         super.deleteById(id);
    }
}
