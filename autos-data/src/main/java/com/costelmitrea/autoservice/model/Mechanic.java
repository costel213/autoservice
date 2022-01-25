package com.costelmitrea.autoservice.model;

import lombok.*;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

import javax.persistence.*;
import java.util.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mechanics")
public class Mechanic extends Person{

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "mechanic_specialties", joinColumns = @JoinColumn(name = "mechanic_id"),
    inverseJoinColumns = @JoinColumn(name = "specialty_id"))
    private Set<Specialty> specialties;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mechanic")
    private Set<Experience> experience;

    @OneToMany(mappedBy = "mechanic", fetch = FetchType.EAGER)
    Set<Visit> visits;
    @Builder
    public Mechanic(Long id, String firstName, String lastName, String address, String city, String telephone, Set<Specialty> specialties,
                    Set<Experience> experience, Set<Visit> visits) {
        super(firstName, lastName, address, city, telephone);
        this.specialties = specialties;
        this.experience = experience;
        this.visits = visits;
    }

    public Set<Specialty> getSpecialtiesInternal() {
        if(this.specialties == null) {
            this.specialties = new HashSet<>();
        }

        return this.specialties;
    }

    public void setSpecialtiesInternal(Set<Specialty> specialties) {
        this.specialties = specialties;
    }

    public Set<Specialty> getSpecialties() {
        return getSpecialtiesInternal();
    }

    public int getNrOfSpecialties() {
        return getSpecialtiesInternal().size();
    }

    public void addSpecialty(Specialty specialty) {
        getSpecialtiesInternal().add(specialty);
    }

    public Set<Experience> getExperienceInternal() {
        if(this.experience == null) {
            this.experience = new HashSet<>();
        }

        return this.experience;
    }

    public Experience getSingleExperience(String timeInterval) {
        return getSingleExperience(timeInterval, false);
    }

    public Experience getSingleExperience(String timeInterval, boolean ignoreNew) {
        for(Experience experience : getExperienceInternal()) {
            if(!ignoreNew || !experience.isNew()) {
                String compTimeInterval = experience.getTimeInterval();
                if(compTimeInterval.equals(timeInterval)) {
                    return experience;
                }
            }
        }

        return null;
    }

    public void setExperienceInternal(Set<Experience> experience) {
        this.experience = experience;
    }

    public List<Experience> getExperience() {
        List<Experience> chronologicalExperience = new ArrayList<>();
        for(Experience experience : getExperienceInternal()) {
            if(experience.getId() != null) {
                chronologicalExperience.add(experience);
            }
        }
        PropertyComparator.sort(chronologicalExperience,
                new MutableSortDefinition("timeInterval", true, false));

        return Collections.unmodifiableList(chronologicalExperience);
    }

    public void addExperience(Experience experience) {
        if(experience.isNew()) {
            getExperienceInternal().add(experience);
        }

        experience.setMechanic(this);
    }

    public Set<Visit> getVisitsInternal() {
        if(this.visits == null) {
            this.visits = new HashSet<>();
        }

        return this.visits;
    }

    public void setVisitsInternal(Set<Visit> visits) {
        this.visits = visits;
    }

    public List<Visit> getVisits() {
        List<Visit> sortedVisits = new ArrayList<>(getVisitsInternal());
        PropertyComparator.sort(sortedVisits,
                new MutableSortDefinition("date", true, true));

        return Collections.unmodifiableList(sortedVisits);
    }

    public void addVisit(Visit visit) {
        if(visit.isNew()) {
            getVisitsInternal().add(visit);
        }

        visit.setMechanic(this);
    }
}
