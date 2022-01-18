package com.costelmitrea.autoservice.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "visits")
public class Visit extends BaseEntity{

    @Column(name = "visit_date")
    @NotNull
    private LocalDate date;

    @Column(name = "description")
    @NotNull
    private String description;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @OneToOne
    @JoinColumn(name = "mechanic_id")
    private Mechanic mechanic;

    @Builder
    public Visit(Long id, LocalDate date, String description, Car car, Mechanic mechanic) {
        super(id);
        this.date = date;
        this.description = description;
        this.car = car;
        this.mechanic = mechanic;
    }
}
