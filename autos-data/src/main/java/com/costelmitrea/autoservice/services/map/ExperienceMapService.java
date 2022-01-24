package com.costelmitrea.autoservice.services.map;

import com.costelmitrea.autoservice.model.Experience;
import com.costelmitrea.autoservice.model.Mechanic;
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
        if(object.getId() == null) {
            return super.save(object);
        } else {
            Experience existingExperience = this.findById(object.getId());
            existingExperience.setTimeInterval(object.getTimeInterval());
            existingExperience.setPosition(object.getPosition());
            existingExperience.setMechanic(object.getMechanic());
            return existingExperience;
        }
    }

    @Override
    public Set<Experience> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        Experience experience = this.findById(id);
        Mechanic mechanic = experience.getMechanic();
        mechanic.getExperienceInternal().remove(experience);
        super.deleteById(id);
    }

    @Override
    public void delete(Experience object) {
        Mechanic mechanic = object.getMechanic();
        mechanic.getExperienceInternal().remove(object);
        super.delete(object);
    }
}
