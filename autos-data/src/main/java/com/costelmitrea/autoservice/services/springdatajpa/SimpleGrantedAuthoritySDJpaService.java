package com.costelmitrea.autoservice.services.springdatajpa;

import com.costelmitrea.autoservice.model.SimpleGrantedAuthority;
import com.costelmitrea.autoservice.repositories.SimpleGrantedAuthorityRepository;
import com.costelmitrea.autoservice.services.SimpleGrantedAuthorityService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class SimpleGrantedAuthoritySDJpaService implements SimpleGrantedAuthorityService {

    private final SimpleGrantedAuthorityRepository simpleGrantedAuthorityRepository;

    public SimpleGrantedAuthoritySDJpaService(SimpleGrantedAuthorityRepository simpleGrantedAuthorityRepository) {
        this.simpleGrantedAuthorityRepository = simpleGrantedAuthorityRepository;
    }

    @Override
    public SimpleGrantedAuthority findById(Long aLong) {
        return simpleGrantedAuthorityRepository.findById(aLong).orElse(null);
    }

    @Override
    public SimpleGrantedAuthority save(SimpleGrantedAuthority object) {
        return simpleGrantedAuthorityRepository.save(object);
    }

    @Override
    public Set<SimpleGrantedAuthority> findAll() {
        Set<SimpleGrantedAuthority> roles = new HashSet<>();
        simpleGrantedAuthorityRepository.findAll().forEach(roles::add);
        return roles;
    }

    @Override
    public void delete(SimpleGrantedAuthority object) {
        simpleGrantedAuthorityRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        simpleGrantedAuthorityRepository.deleteById(aLong);
    }

    @Override
    public SimpleGrantedAuthority findByRole(String role) {
        return simpleGrantedAuthorityRepository.findByRole(role);
    }
}
