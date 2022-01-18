package com.costelmitrea.autoservice.services.map;

import com.costelmitrea.autoservice.model.SimpleGrantedAuthority;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SimpleGrantedAuthorityMapServiceTest {

    SimpleGrantedAuthorityMapService simpleGrantedAuthorityMapService;
    final Long authorityId = 1L, authority2Id = 2L;
    final String role = "ROLE_USER";

    @BeforeEach
    void setUp() {
        simpleGrantedAuthorityMapService = new SimpleGrantedAuthorityMapService();
        simpleGrantedAuthorityMapService.save(SimpleGrantedAuthority.builder().role(role).build());
    }

    @Test
    void findById() {
        SimpleGrantedAuthority simpleGrantedAuthority =simpleGrantedAuthorityMapService.findById(authorityId);
        assertEquals(authorityId, simpleGrantedAuthority.getId());
    }

    @Test
    void save() {
        SimpleGrantedAuthority simpleGrantedAuthority = SimpleGrantedAuthority.builder().id(authority2Id).build();
        SimpleGrantedAuthority savedSimpleGrantedAuthority = simpleGrantedAuthorityMapService.save(simpleGrantedAuthority);
        assertEquals(authority2Id, savedSimpleGrantedAuthority.getId());
    }

    @Test
    void findAll() {
        Set<SimpleGrantedAuthority> authoritiesSet = simpleGrantedAuthorityMapService.findAll();
        assertEquals(1, authoritiesSet.size());
    }

    @Test
    void deleteById() {
        simpleGrantedAuthorityMapService.deleteById(authorityId);
        assertEquals(0, simpleGrantedAuthorityMapService.findAll().size());
    }

    @Test
    void delete() {
        simpleGrantedAuthorityMapService.delete(simpleGrantedAuthorityMapService.findById(authorityId));
        assertEquals(0, simpleGrantedAuthorityMapService.findAll().size());
    }

    @Test
    void saveNoId() {
        SimpleGrantedAuthority savedSimpleGrantedAuthority = simpleGrantedAuthorityMapService.save(SimpleGrantedAuthority.builder().build());
        assertNotNull(savedSimpleGrantedAuthority);
        assertNotNull(savedSimpleGrantedAuthority.getId());
    }
}