package com.costelmitrea.autoservice.services;

import com.costelmitrea.autoservice.model.SimpleGrantedAuthority;

public interface SimpleGrantedAuthorityService extends CrudService<SimpleGrantedAuthority, Long> {

    SimpleGrantedAuthority findByRole(String role);
}
