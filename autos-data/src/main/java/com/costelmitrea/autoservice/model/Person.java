package com.costelmitrea.autoservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class Person extends BaseEntity{

    @Column(name = "first_name")
    @NotEmpty
    @Size(min = 3, max = 50)
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty
    @Size(min = 3, max = 50)
    private String lastName;

    @Column(name = "address")
    @NotEmpty
    @Size(min = 5, max = 50)
    private String address;

    @Column(name = "city")
    @NotEmpty
    @Size(min = 3, max = 20)
    private String city;

    @Column(name = "telephone")
    @NotEmpty
    @Size(min = 10, max = 10)
    private String telephone;

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
