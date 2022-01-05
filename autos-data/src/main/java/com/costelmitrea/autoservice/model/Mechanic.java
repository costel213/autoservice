package com.costelmitrea.autoservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

import javax.xml.bind.annotation.XmlElement;
import java.util.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Mechanic extends Person{

    private Set<Specialty> specialties;
    private Map<String, String> experience;

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

    public Map<String, String> getExperienceInternal() {
        if(this.experience == null) {
            this.experience = new HashMap<>();
        }

        return this.experience;
    }

    public void setExperienceInternal(Map<String, String> experience) {
        this.experience = experience;
    }

    @XmlElement
    public NavigableMap<String, String> getExperience() {
        TreeMap<String, String> chronologicalExperience = new TreeMap<>(getExperienceInternal());
        return chronologicalExperience.descendingMap();
    }

    public void addExperience(String period, String description) {
        getExperienceInternal().put(period, description);
    }
}
