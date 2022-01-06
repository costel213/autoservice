package com.costelmitrea.autoservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Visit extends BaseEntity{

    private LocalDate date;
    private String description;
    private Car car;
    private Mechanic mechanic;
}
