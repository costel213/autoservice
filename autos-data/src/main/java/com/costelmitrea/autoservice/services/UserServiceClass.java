package com.costelmitrea.autoservice.services;

import com.costelmitrea.autoservice.model.User;
import com.costelmitrea.autoservice.user.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceClass implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUserName(username);
//        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
        return (org.springframework.security.core.userdetails.UserDetails) new UserDetails(user);
    }
}
