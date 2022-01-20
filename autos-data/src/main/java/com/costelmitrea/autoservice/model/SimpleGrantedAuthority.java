package com.costelmitrea.autoservice.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "roles")
public final class SimpleGrantedAuthority extends BaseEntity implements GrantedAuthority {

    @Column
    @NotEmpty
    @Size(min = 5, max = 15)
    private String role;

    @Builder
    public SimpleGrantedAuthority(Long id, String role) {
        super(id);
        this.role = role;
    }

    @Override
    public String toString() {
        return role;
    }

    @Override
    public String getAuthority() {
        return this.role;
    }
}
