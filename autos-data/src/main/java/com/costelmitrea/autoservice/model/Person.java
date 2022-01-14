package com.costelmitrea.autoservice.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class Person extends BaseEntity{

    @Column(name = "first_name")
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    private String lastName;

    @Column(name = "address")
    @NotNull
    private String address;

    @Column(name = "city")
    @NotNull
    private String city;

    @Column(name = "telephone")
    @NotNull
    private String telephone;

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
