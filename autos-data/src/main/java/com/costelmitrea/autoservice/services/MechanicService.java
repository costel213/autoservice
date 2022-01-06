package com.costelmitrea.autoservice.services;

import com.costelmitrea.autoservice.model.Mechanic;

public interface MechanicService extends CrudService<Mechanic,Long>{

    Mechanic findByLastName(String lastName);
}
