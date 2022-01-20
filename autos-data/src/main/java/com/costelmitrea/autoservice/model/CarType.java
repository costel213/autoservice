package com.costelmitrea.autoservice.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "types")
public class CarType extends BaseEntity{

    @Column(name = "name")
    @NotEmpty
    @Size(min = 3, max = 20)
    private String name;

    @Builder
    public CarType(Long id, String name) {
        super(id);
        this.name = name;
    }

    @Override
    public String toString() {
        return  name;
    }
}
