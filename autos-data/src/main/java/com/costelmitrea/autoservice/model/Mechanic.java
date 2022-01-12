package com.costelmitrea.autoservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
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

    public Set<Specialty> getSpecialtiesInternal() {
        if(this.specialties == null) {
            this.specialties = new HashSet<>();
        }

        return this.specialties;
    }

    public void setSpecialtiesInternal(Set<Specialty> specialties) {
        this.specialties = specialties;
    }

    @XmlElement
    public List<Specialty> getSpecialties() {
        List<Specialty> sortedSpecialties = new ArrayList<>(getSpecialtiesInternal());
        PropertyComparator.sort(sortedSpecialties,
                new MutableSortDefinition("name", true, true));

        return Collections.unmodifiableList(sortedSpecialties);
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

    public Experience getExperience(String timeInterval) {
        return getExperience(timeInterval, false);
    }

    public Experience getExperience(String timeInterval, boolean ignoreNew) {
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

//    @XmlElement
//    public TreeSet<Experience> getExperience() {
//        TreeSet<Experience> chronologicalExperience = new TreeSet<>(getExperienceInternal());
//        return chronologicalExperience;
//    }

    public void addExperience(Experience experience) {
        if(experience.isNew()) {
            getExperienceInternal().add(experience);
        }

        experience.setMechanic(this);
    }
}
