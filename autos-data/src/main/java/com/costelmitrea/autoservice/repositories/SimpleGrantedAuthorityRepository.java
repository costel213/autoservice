package com.costelmitrea.autoservice.repositories;

import com.costelmitrea.autoservice.model.SimpleGrantedAuthority;
import org.springframework.data.repository.CrudRepository;

public interface SimpleGrantedAuthorityRepository extends CrudRepository<SimpleGrantedAuthority, Long> {
}
