package com.costelmitrea.autoservice.services.map;

import com.costelmitrea.autoservice.model.User;
import com.costelmitrea.autoservice.services.SimpleGrantedAuthorityService;
import com.costelmitrea.autoservice.services.UserService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile("map")
public class UserMapService extends AbstractMapService<User, Long> implements UserService {

    private final SimpleGrantedAuthorityService simpleGrantedAuthorityService;

    public UserMapService(SimpleGrantedAuthorityService simpleGrantedAuthorityService) {
        this.simpleGrantedAuthorityService = simpleGrantedAuthorityService;
    }

    @Override
    public User findById(Long id) {
        return super.findById(id);
    }

    @Override
    public User save(User object) {
        if(object != null) {
            if(object.getRoles() != null) {
                object.getRoles().forEach(role -> {
                    if(role.getId() == null) {
                        role.setId(simpleGrantedAuthorityService.save(role).getId());
                    }
                });
            }

            return super.save(object);
        } else {
            return null;
        }
    }

    @Override
    public Set<User> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(User object) {
        super.delete(object);
    }

    @Override
    public User findByUserName(String userName) {
        return this.findAll().stream()
                .filter(user -> user.getUserName().equalsIgnoreCase(userName.trim()))
                .findFirst()
                .orElse(null);
    }
}
