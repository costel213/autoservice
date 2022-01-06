package com.costelmitrea.autoservice.services.map;

import com.costelmitrea.autoservice.model.Client;
import com.costelmitrea.autoservice.services.CarService;
import com.costelmitrea.autoservice.services.CarTypeService;
import com.costelmitrea.autoservice.services.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ClientMapService extends AbstractMapService<Client, Long> implements ClientService {

    private final CarTypeService carTypeService;
    private final CarService carService;

    public ClientMapService(CarTypeService carTypeService, CarService carService) {
        this.carTypeService = carTypeService;
        this.carService = carService;
    }

    @Override
    public Client findByLastName(String lastName) {
        return this.findAll()
                .stream()
                .filter(client -> client.getLastName().equalsIgnoreCase(lastName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Client> findAllByLastNameLike(String lastName) {
        return this.findAll()
                .stream()
                .filter(client -> client.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
    }

    @Override
    public Client findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Client save(Client object) {
        if(object != null) {
            if(object.getCarsInternal() != null) {
                object.getCarsInternal().forEach(car -> {
                    if(car.getCarType() != null) {
                        if(car.getCarType().getId() == null) {
                            car.getCarType().setId(carTypeService.save(car.getCarType()).getId());
                        }
                    } else {
                        throw new RuntimeException("CarType is required.");
                    }

                    if(car.getId() == null) {
                        car.setId(carService.save(car).getId());
                    }
                });
            }

            return super.save(object);
        } else {
            return null;
        }
    }

    @Override
    public Set<Client> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Client object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
