package com.costelmitrea.autoservice.services.springdatajpa;

import com.costelmitrea.autoservice.model.Experience;
import com.costelmitrea.autoservice.repositories.ExperienceRepository;
import com.costelmitrea.autoservice.services.ExperienceService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class ExperienceSDJpaService implements ExperienceService {

    private final ExperienceRepository experienceRepository;

    public ExperienceSDJpaService(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }


    @Override
    public Experience findById(Long aLong) {
        return experienceRepository.findById(aLong).orElse(null);
    }

    @Override
    public Experience save(Experience object) {
        return experienceRepository.save(object);
    }

    @Override
    public Set<Experience> findAll() {
        Set<Experience> experiences = new HashSet<>();
        experienceRepository.findAll().forEach(experiences::add);
        return experiences;
    }

    @Override
    public void delete(Experience object) {
        experienceRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        experienceRepository.deleteById(aLong);
    }
}
