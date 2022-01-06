package com.costelmitrea.autoservice.repositories;

import com.costelmitrea.autoservice.model.CarType;
import org.springframework.data.repository.CrudRepository;

public interface CarTypeRepository extends CrudRepository<CarType, Long> {
}
