package com.costelmitrea.autoservice.services.map;

import com.costelmitrea.autoservice.model.Car;
import com.costelmitrea.autoservice.model.Client;
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
        if(object.getId() == null) {
            return super.save(object);
        } else {
            Car existingCar = this.findById(object.getId());
            existingCar.setModel(object.getModel());
            existingCar.setDateOfProduction(object.getDateOfProduction());
            existingCar.setOwner(object.getOwner());
            existingCar.setCarType(object.getCarType());
            existingCar.setVisitsInternal(object.getVisitsInternal());
            return existingCar;
        }

    }

    @Override
    public Set<Car> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        Car car = this.findById(id);
        Client client = car.getOwner();
        client.getCarsInternal().remove(car);
        super.deleteById(id);
    }

    @Override
    public void delete(Car object) {
        Client client = object.getOwner();
        client.getCarsInternal().remove(object);
        super.delete(object);
    }
}
