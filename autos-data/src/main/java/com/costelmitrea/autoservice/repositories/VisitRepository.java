package com.costelmitrea.autoservice.repositories;

import com.costelmitrea.autoservice.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
