package com.costelmitrea.autoservice.services.map;

import com.costelmitrea.autoservice.model.Visit;
import com.costelmitrea.autoservice.services.CarService;
import com.costelmitrea.autoservice.services.CarTypeService;
import com.costelmitrea.autoservice.services.ClientService;
import com.costelmitrea.autoservice.services.VisitService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
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
        if(object != null) {
            if(object.getCar() != null) {
                if(object.getCar().getCarType() != null) {
                    if(object.getCar().getCarType().getId() == 0) {
                        object.getCar().getCarType().setId(carTypeService.save(object.getCar().getCarType()).getId());
                    }
                    if(object.getCar().getOwner() != null) {
                        if(object.getCar().getOwner().getId() == null) {
                            object.getCar().setOwner(clientService.save(object.getCar().getOwner()));
                        }
                    } else {
                        throw new RuntimeException("Invalid visit");
                    }

                    if(object.getCar().getId() == null) {
                        object.getCar().setId(carService.save(object.getCar()).getId());
                    }
                }
            }

            if(object.getMechanic() != null) {
                if(object.getMechanic().getId() == null) {
                    object.getMechanic().setId(mechanicService.save(object.getMechanic()).getId());
                }
            } else {
                throw new RuntimeException("Invalid object. Add mechanic.");
            }

            return super.save(object);
        } else {
            return null;
        }
    }

    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Visit object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
