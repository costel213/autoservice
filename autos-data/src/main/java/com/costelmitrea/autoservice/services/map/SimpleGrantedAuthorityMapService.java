package com.costelmitrea.autoservice.services.map;

import com.costelmitrea.autoservice.model.SimpleGrantedAuthority;
import com.costelmitrea.autoservice.services.SimpleGrantedAuthorityService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile("map")
public class SimpleGrantedAuthorityMapService extends AbstractMapService<SimpleGrantedAuthority, Long> implements SimpleGrantedAuthorityService {

    @Override
    public SimpleGrantedAuthority findById(Long id) {
        return super.findById(id);
    }

    @Override
    public SimpleGrantedAuthority save(SimpleGrantedAuthority object) {
        return super.save(object);
    }

    @Override
    public Set<SimpleGrantedAuthority> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(SimpleGrantedAuthority object) {
        super.delete(object);
    }
}
