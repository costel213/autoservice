package com.costelmitrea.autoservice.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "car", fetch = FetchType.EAGER)
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
        visit.setCar(this);
    }

    @Override
    public String toString() {
        return model;
    }
}
