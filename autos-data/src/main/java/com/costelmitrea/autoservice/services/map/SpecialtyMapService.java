package com.costelmitrea.autoservice.services.map;

import com.costelmitrea.autoservice.model.Specialty;
import com.costelmitrea.autoservice.services.SpecialtyService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile("map")
public class SpecialtyMapService extends AbstractMapService<Specialty, Long> implements SpecialtyService {

    @Override
    public Specialty findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Specialty save(Specialty object) {
        if (object.getId() == null) {
            return super.save(object);
        } else {
            Specialty existingSpecialty = this.findById(object.getId());
            existingSpecialty.setName(object.getName());
            return existingSpecialty;
        }
    }

    @Override
    public Set<Specialty> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Specialty object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
