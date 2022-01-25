package com.costelmitrea.autoservice.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class Car extends BaseEntity{

    @Column(name = "model")
    @NotEmpty
    @Size(min = 3, max = 20)
    private String model;

    @Column(name = "date_of_production")
    private LocalDate dateOfProduction;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private CarType carType;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Client owner;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "car")
    private Set<Visit> visits;

    @Builder
    public Car(Long id, String model, LocalDate dateOfProduction, CarType carType, Client owner, Set<Visit> visits) {
        super(id);
        this.model = model;
        this.dateOfProduction = dateOfProduction;
        this.carType = carType;
        this.owner = owner;

        if(visits == null || visits.size() > 0) {
            this.visits = visits;
        }
    }

    public Set<Visit> getVisitsInternal() {
        if(this.visits == null) {
            this.visits = new HashSet<>();
        }

        return this.visits;
    }

    public Visit getVisit(LocalDate date) {
        return getVisit(date, false);
    }

    public Visit getVisit(LocalDate date, boolean ignoreNew) {
        for(Visit visit : getVisitsInternal()) {
            if(!ignoreNew || !visit.isNew()) {
                LocalDate compDate = visit.getDate();
                if(compDate.equals(date)) {
                    return visit;
                }
            }
        }

        return null;
    }

    public void setVisitsInternal(Set<Visit> visits) {
        this.visits = visits;
    }

    public List<Visit> getVisits() {
        List<Visit> sortedVisits = new ArrayList<>();
        for(Visit visit :getVisitsInternal()) {
            if(visit.getId() != null) {
                sortedVisits.add(visit);
            }
        }
        PropertyComparator.sort(sortedVisits,
                new MutableSortDefinition("date", false, false));
        return Collections.unmodifiableList(sortedVisits);
    }

    public void addVisit(Visit visit) {
        if(visit.isNew()) {
            getVisitsInternal().add(visit);
        }

        visit.setCar(this);
    }

    @Override
    public String toString() {
        return model;
    }
}
