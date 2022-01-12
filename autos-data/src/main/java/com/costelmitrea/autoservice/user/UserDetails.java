package com.costelmitrea.autoservice.user;

import com.costelmitrea.autoservice.model.SimpleGrantedAuthority;
import com.costelmitrea.autoservice.model.User;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class UserDetails implements Serializable, org.springframework.security.core.userdetails.UserDetails {

    private User user;

    private List<SimpleGrantedAuthority> grantedAuthorities;

    public UserDetails(User user) {
        this.user = user;
    }

    public UserDetails() {}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    public String getFirstName() {
        return user.getFirstName();
    }

    public String getLastName() {
        return user.getLastName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User getUser() {
        return user;
    }
}
