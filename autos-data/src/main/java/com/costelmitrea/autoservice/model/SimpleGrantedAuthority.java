package com.costelmitrea.autoservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "roles")
public final class SimpleGrantedAuthority extends BaseEntity implements GrantedAuthority {

    public SimpleGrantedAuthority(Long id, String role) {
        super(id);
        this.role = role;
    }

    private String role;

    @Override
    public String getAuthority() {
        return role;
    }
}
