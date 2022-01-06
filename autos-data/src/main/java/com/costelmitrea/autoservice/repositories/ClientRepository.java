package com.costelmitrea.autoservice.repositories;

import com.costelmitrea.autoservice.model.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepository extends CrudRepository<Client, Long> {

    Client findByLastName(String lastName);
    List<Client> findAllByLastNameLike(String lastName);
}
