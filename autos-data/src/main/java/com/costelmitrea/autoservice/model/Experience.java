package com.costelmitrea.autoservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
