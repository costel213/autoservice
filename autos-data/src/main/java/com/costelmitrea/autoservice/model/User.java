package com.costelmitrea.autoservice.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User extends Person {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
    @Column(name = "user_name")
    @NotNull
    private String userName;

    @Column(name = "password")
    @NotNull
    private String password;

    @Column(name = "active")
    @NotNull
    private boolean active;

    @Column(name = "roles")
    @NotNull
    private String roles;

    public User(String firstName, String lastName, String address,
                String city, String telephone, String userName, String password,
                boolean active, String roles) {
        super(firstName, lastName, address, city, telephone);
        this.userName = userName;
        this.password = password;
        this.active = active;
        this.roles = roles;
    }
}
