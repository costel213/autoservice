package com.costelmitrea.autoservice.services.map;

import com.costelmitrea.autoservice.model.Experience;
import com.costelmitrea.autoservice.services.ExperienceService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile("map")
public class ExperienceMapService extends AbstractMapService<Experience, Long> implements ExperienceService {

    @Override
    public Experience findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Experience save(Experience object) {
        return super.save(object);
    }

    @Override
    public Set<Experience> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Experience object) {
        super.delete(object);
    }
}
