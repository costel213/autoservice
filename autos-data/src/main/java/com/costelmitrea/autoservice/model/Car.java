package com.costelmitrea.autoservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

import java.time.LocalDate;
import java.util.*;

@Setter
@Getter
@NoArgsConstructor
public class Car extends BaseEntity{

    private String model;
    private LocalDate dateOfProduction;
    private CarType carType;
    private Client owner;
    private Set<Visit> visits = new LinkedHashSet<>();

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
                new MutableSortDefinition("date", false, false));
        return Collections.unmodifiableList(sortedVisits);
    }

    public void addVisit(Visit visit) {
        getVisitsInternal().add(visit);
        visit.getCar().setId(this.getId());
    }
}
