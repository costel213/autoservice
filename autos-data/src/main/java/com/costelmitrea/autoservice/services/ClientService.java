package com.costelmitrea.autoservice.services;

import com.costelmitrea.autoservice.model.Client;

import java.util.List;

public interface ClientService extends CrudService<Client, Long>{

    Client findByLastName(String lastName);
    List<Client> findAllByLastNameLike(String lastName);
}
