package com.costelmitrea.autoservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
}
