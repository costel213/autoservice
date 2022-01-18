package com.costelmitrea.autoservice.model;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "types")
public class CarType extends BaseEntity{

    @Column(name = "name")
    @NotNull
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
