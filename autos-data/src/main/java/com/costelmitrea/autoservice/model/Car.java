package com.costelmitrea.autoservice.model;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class Car extends BaseEntity{

    @Column(name = "model")
    @NotNull
    private String model;

    @Column(name = "date_of_production")
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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

    public Visit getVisits(LocalDate date) {
        return getVisits(date, false);
    }

    public Visit getVisits(LocalDate date, boolean ignoreNew) {
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

//    public List<Visit> getVisits() {
//        List<Visit> sortedVisits = new ArrayList<>(getVisitsInternal());
//        PropertyComparator.sort(sortedVisits,
//                new MutableSortDefinition("date", false, false));
//        return Collections.unmodifiableList(sortedVisits);
//    }

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
