package com.costelmitrea.autoservice.formatter;

import com.costelmitrea.autoservice.model.Specialty;
import com.costelmitrea.autoservice.services.SpecialtyService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

@Component
public class SpecialtyFormatter implements Formatter<Specialty> {

    private final SpecialtyService specialtyService;

    public SpecialtyFormatter(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @Override
    public Specialty parse(String text, Locale locale) throws ParseException {
        Collection<Specialty> findSpecialties = specialtyService.findAll();
        for(Specialty specialty : findSpecialties) {
            if(specialty.getName().equals(text)) {
                return specialty;
            }
        }

        throw new ParseException("specialty not found: " + text, 0);
    }

    @Override
    public String print(Specialty object, Locale locale) {
        return object.getName();
    }
}
