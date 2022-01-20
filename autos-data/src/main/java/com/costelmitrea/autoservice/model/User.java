package com.costelmitrea.autoservice.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "user_name", unique = true)
    @NotEmpty
    @Size(min = 6, max = 15)
    private String userName;

    @Column(name = "password")
    @NotEmpty
    private String password;

    @Column(name = "first_name")
    @NotEmpty
    @Size(min = 3, max = 30)
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty
    @Size(min = 3, max = 30)
    private String lastName;

    @Builder
    public User(Long id, String userName, String password, String firstName, String lastName, Set<SimpleGrantedAuthority> roles) {
        super(id);
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<SimpleGrantedAuthority> roles = new HashSet<>();
}
