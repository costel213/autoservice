package com.costelmitrea.autoservice.services.springdatajpa;

import com.costelmitrea.autoservice.model.Client;
import com.costelmitrea.autoservice.repositories.ClientRepository;
import com.costelmitrea.autoservice.services.ClientService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ClientSDJpaService implements ClientService {

    private final ClientRepository clientRepository;

    public ClientSDJpaService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client findByLastName(String lastName) {
        return clientRepository.findByLastName(lastName);
    }

    @Override
    public List<Client> findAllByLastNameLike(String lastName) {
        return clientRepository.findAllByLastNameLike(lastName);
    }

    @Override
    public Client findById(Long aLong) {
        return clientRepository.findById(aLong).orElse(null);
    }

    @Override
    public Client save(Client object) {
        return clientRepository.save(object);
    }

    @Override
    public Set<Client> findAll() {
        Set<Client> clients = new HashSet<>();
        clientRepository.findAll().forEach(clients::add);
        return clients;
    }

    @Override
    public void delete(Client object) {
        clientRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        clientRepository.deleteById(aLong);
    }
}
