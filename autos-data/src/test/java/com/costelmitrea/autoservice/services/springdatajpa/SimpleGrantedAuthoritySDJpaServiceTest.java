package com.costelmitrea.autoservice.services.springdatajpa;

import com.costelmitrea.autoservice.model.SimpleGrantedAuthority;
import com.costelmitrea.autoservice.repositories.SimpleGrantedAuthorityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SimpleGrantedAuthoritySDJpaServiceTest {

    public static final String ROLE = "USER";

    @Mock
    SimpleGrantedAuthorityRepository repository;

    @InjectMocks
    SimpleGrantedAuthoritySDJpaService service;

    SimpleGrantedAuthority returnedAuthority;

    @BeforeEach
    void setUp() {
        returnedAuthority = SimpleGrantedAuthority.builder().id(1L).role(ROLE).build();
    }

    @Test
    void findById() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(returnedAuthority));
        SimpleGrantedAuthority authority = service.findById(1L);
        assertNotNull(authority);
    }

    @Test
    void findByIdNotFound() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        SimpleGrantedAuthority authority = service.findById(1L);
        assertNull(authority);
    }

    @Test
    void save() {
        SimpleGrantedAuthority authorityToSave = SimpleGrantedAuthority.builder().id(1L).build();
        when(repository.save(any())).thenReturn(returnedAuthority);
        SimpleGrantedAuthority savedAuthority = service.save(authorityToSave);
        assertNotNull(savedAuthority);
        verify(repository).save(any());
    }

    @Test
    void findAll() {
        Set<SimpleGrantedAuthority> returnAuthoritySet = new HashSet<>();
        returnAuthoritySet.add(SimpleGrantedAuthority.builder().id(1L).build());
        returnAuthoritySet.add(SimpleGrantedAuthority.builder().id(2L).build());
        when(repository.findAll()).thenReturn(returnAuthoritySet);
        Set<SimpleGrantedAuthority> authorities = service.findAll();
        assertNotNull(authorities);
        assertEquals(2, authorities.size());
    }

    @Test
    void delete() {
        service.delete(returnedAuthority);
        verify(repository).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(anyLong());
        verify(repository, times(1)).deleteById(anyLong());
    }
}