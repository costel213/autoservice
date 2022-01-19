package com.costelmitrea.autoservice.services.springdatajpa;

import com.costelmitrea.autoservice.model.User;
import com.costelmitrea.autoservice.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserSDJpaServiceTest {

    public static final String USERNAME = "user";

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserSDJpaService service;

    User returnUser;

    @BeforeEach
    void setUp() {
        returnUser = User.builder().id(1L).userName(USERNAME).build();
    }

    @Test
    void findById() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(returnUser));
        User user = service.findById(1L);
        assertNotNull(user);
    }

    @Test
    void findByIdNotFound() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        User user = service.findById(1L);
        assertNull(user);
    }

    @Test
    void save() {
        User userToSave = User.builder().id(1L).build();
        when(userRepository.save(any())).thenReturn(returnUser);
        User savedUser = service.save(userToSave);
        assertNotNull(savedUser);
        verify(userRepository).save(any());
    }

    @Test
    void findAll() {
        Set<User> returnUsersSet = new HashSet<>();
        returnUsersSet.add(User.builder().id(1L).build());
        returnUsersSet.add(User.builder().id(2L).build());
        when(userRepository.findAll()).thenReturn(List.copyOf(returnUsersSet));
        Set<User> users = service.findAll();
        assertNotNull(users);
        assertEquals(2, users.size());
    }

    @Test
    void delete() {
        service.delete(returnUser);
        verify(userRepository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(userRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void findByUserName() {
        when(userRepository.findByUserName(USERNAME)).thenReturn(returnUser);
        User user = service.findByUserName(USERNAME);
        assertNotNull(user);
    }
}