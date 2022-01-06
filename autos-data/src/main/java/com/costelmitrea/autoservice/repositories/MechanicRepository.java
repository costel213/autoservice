package com.costelmitrea.autoservice.repositories;

import com.costelmitrea.autoservice.model.Mechanic;
import org.springframework.data.repository.CrudRepository;

public interface MechanicRepository extends CrudRepository<Mechanic, Long> {

    Mechanic findByLastName(String lastName);
}
