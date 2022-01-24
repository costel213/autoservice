package com.costelmitrea.autoservice.services.map;

import com.costelmitrea.autoservice.model.Client;
import com.costelmitrea.autoservice.services.CarService;
import com.costelmitrea.autoservice.services.CarTypeService;
import com.costelmitrea.autoservice.services.ClientService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Profile("map")
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
                .filter(client -> client.getLastName().equalsIgnoreCase(lastName.trim()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Client> findAllByLastNameLike(String lastName) {
        if(lastName.equalsIgnoreCase("%%")) {
            List<Client> foundClients = new ArrayList<>();
            foundClients.addAll(this.findAll());
            return foundClients;
        } else {
            return this.findAll()
                    .stream()
                    .filter(client -> client.getLastName().toLowerCase().startsWith(
                            lastName.substring(1, lastName.length() - 1).toLowerCase()))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public Client findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Client save(Client object) {
        return super.save(object);
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
