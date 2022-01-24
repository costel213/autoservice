package com.costelmitrea.autoservice.services.map;

import com.costelmitrea.autoservice.model.Car;
import com.costelmitrea.autoservice.model.Visit;
import com.costelmitrea.autoservice.services.CarService;
import com.costelmitrea.autoservice.services.CarTypeService;
import com.costelmitrea.autoservice.services.ClientService;
import com.costelmitrea.autoservice.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile("map")
public class VisitMapService extends AbstractMapService<Visit, Long> implements VisitService {

    private final CarService carService;
    private final CarTypeService carTypeService;
    private final ClientService clientService;
    private final MechanicMapService mechanicService;

    public VisitMapService(CarService carService, CarTypeService carTypeService,
                           ClientService clientService, MechanicMapService mechanicService) {
        this.carService = carService;
        this.carTypeService = carTypeService;
        this.clientService = clientService;
        this.mechanicService = mechanicService;
    }

    @Override
    public Visit findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Visit save(Visit object) {
        if(object.getId() == null) {
            return super.save(object);
        } else {
            Visit existingVisit = this.findById(object.getId());
            existingVisit.setDate(object.getDate());
            existingVisit.setDescription(object.getDescription());
            existingVisit.setCar(object.getCar());
            existingVisit.setMechanic(object.getMechanic());
            return existingVisit;
        }
    }

    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Visit object) {
        Car car = object.getCar();
        car.getVisitsInternal().remove(object);
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        Visit visit = this.findById(id);
        Car car = visit.getCar();
        car.getVisitsInternal().remove(visit);
        super.deleteById(id);
    }
}
