package com.costelmitrea.autoservice.model;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "experience")
public class Experience extends BaseEntity{

    @Column(name = "time_interval")
    private String timeInterval;

    @Column(name = "position")
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
