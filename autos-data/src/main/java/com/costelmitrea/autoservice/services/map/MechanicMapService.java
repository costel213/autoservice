package com.costelmitrea.autoservice.services.map;

import com.costelmitrea.autoservice.model.Mechanic;
import com.costelmitrea.autoservice.services.MechanicService;
import com.costelmitrea.autoservice.services.SpecialtyService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile("map")
public class MechanicMapService extends AbstractMapService<Mechanic, Long> implements MechanicService {

    private final SpecialtyService specialtyService;

    public MechanicMapService(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @Override
    public Mechanic findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Mechanic findByLastName(String lastName) {
        return this.findAll()
                .stream()
                .filter(mechanic -> mechanic.getLastName().equalsIgnoreCase(lastName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Mechanic save(Mechanic object) {
//        if(object != null) {
//            if(object.getSpecialtiesInternal() != null) {
//                object.getSpecialtiesInternal().forEach(specialty -> {
//                    if(specialty.getId() == null) {
//                        specialty.setId(specialtyService.save(specialty).getId());
//                    }
//                });
//            } else {
//                throw new RuntimeException("Specialties cannot be empty.");
//            }
//
//            return super.save(object);
//        } else {
//            return null;
//        }
        return super.save(object);
    }

    @Override
    public Set<Mechanic> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Mechanic object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
