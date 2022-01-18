package com.costelmitrea.autoservice.services.map;

import com.costelmitrea.autoservice.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserMapServiceTest {

    UserMapService userMapService;
    final Long userId = 1L, user2Id = 2L;
    final String username = "user";

    @BeforeEach
    void setUp() {
        userMapService = new UserMapService(new SimpleGrantedAuthorityMapService());
        userMapService.save(User.builder().userName(username).build());
    }

    @Test
    void findById() {
        User user = userMapService.findById(userId);
        assertEquals(userId, user.getId());
    }

    @Test
    void save() {
        User user2 = User.builder().id(user2Id).build();
        User savedUser = userMapService.save(user2);
        assertEquals(user2Id, savedUser.getId());
    }

    @Test
    void findAll() {
        Set<User> usersSet = userMapService.findAll();
        assertEquals(1, usersSet.size());
    }

    @Test
    void deleteById() {
        userMapService.deleteById(userId);
        assertEquals(0, userMapService.findAll().size());
    }

    @Test
    void delete() {
        userMapService.delete(userMapService.findById(userId));
        assertEquals(0, userMapService.findAll().size());
    }

    @Test
    void findByUserName() {
        User user = userMapService.findByUserName(username);
        assertEquals(userId, user.getId());
    }

    @Test
    void saveNoId() {
        User savedUser = userMapService.save(User.builder().build());
        assertNotNull(savedUser);
        assertNotNull(savedUser.getId());
    }
}