package com.costelmitrea.autoservice.formatter;

import com.costelmitrea.autoservice.model.SimpleGrantedAuthority;
import com.costelmitrea.autoservice.services.SimpleGrantedAuthorityService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

@Component
public class SimpleGrantedAuthorityFormatter implements Formatter<SimpleGrantedAuthority> {

    private final SimpleGrantedAuthorityService simpleGrantedAuthorityService;

    public SimpleGrantedAuthorityFormatter(SimpleGrantedAuthorityService simpleGrantedAuthorityService) {
        this.simpleGrantedAuthorityService = simpleGrantedAuthorityService;
    }

    @Override
    public SimpleGrantedAuthority parse(String text, Locale locale) throws ParseException {
        Collection<SimpleGrantedAuthority> findSimpleGrantedAuthorities = simpleGrantedAuthorityService.findAll();
        for(SimpleGrantedAuthority simpleGrantedAuthority : findSimpleGrantedAuthorities) {
            if(simpleGrantedAuthority.getRole().equals(text)) {
                return simpleGrantedAuthority;
            }
        }

        throw new ParseException("role not found: " + text, 0);
    }

    @Override
    public String print(SimpleGrantedAuthority object, Locale locale) {
        return object.getRole();
    }
}
