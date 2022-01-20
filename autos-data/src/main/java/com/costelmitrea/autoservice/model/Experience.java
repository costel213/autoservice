package com.costelmitrea.autoservice.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "experience")
public class Experience extends BaseEntity{

    @Column(name = "time_interval")
    @NotEmpty
    @Size(min = 15, max = 15)
    private String timeInterval;

    @Column(name = "position")
    @NotEmpty
    @Size(min = 3, max = 30)
    private String position;

    @ManyToOne
    @JoinColumn(name="mechanic_id")
    private Mechanic mechanic;

    @Builder
    public Experience(Long id, String timeInterval, String position, Mechanic mechanic) {
        super(id);
        this.timeInterval = timeInterval;
        this.position = position;
        this.mechanic = mechanic;
    }
}
