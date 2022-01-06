package com.costelmitrea.autoservice.services.springdatajpa;

import com.costelmitrea.autoservice.model.Mechanic;
import com.costelmitrea.autoservice.repositories.MechanicRepository;
import com.costelmitrea.autoservice.services.MechanicService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class MechanicSDJpaService implements MechanicService {

    private final MechanicRepository mechanicRepository;

    public MechanicSDJpaService(MechanicRepository mechanicRepository) {
        this.mechanicRepository = mechanicRepository;
    }

    @Override
    public Mechanic findById(Long aLong) {
        return mechanicRepository.findById(aLong).orElse(null);
    }

    @Override
    public Mechanic save(Mechanic object) {
        return mechanicRepository.save(object);
    }

    @Override
    public Set<Mechanic> findAll() {
        Set<Mechanic> mechanics = new HashSet<>();
        mechanicRepository.findAll().forEach(mechanics::add);
        return mechanics;
    }

    @Override
    public void delete(Mechanic object) {
        mechanicRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        mechanicRepository.deleteById(aLong);
    }

    @Override
    public Mechanic findByLastName(String lastName) {
        return mechanicRepository.findByLastName(lastName);
    }
}
