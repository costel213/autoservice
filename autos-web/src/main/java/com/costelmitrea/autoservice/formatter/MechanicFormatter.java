package com.costelmitrea.autoservice.formatter;

import com.costelmitrea.autoservice.model.Mechanic;
import com.costelmitrea.autoservice.services.MechanicService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

@Component
public class MechanicFormatter implements Formatter<Mechanic> {

    private final MechanicService mechanicService;

    public MechanicFormatter(MechanicService mechanicService) {
        this.mechanicService = mechanicService;
    }

    @Override
    public Mechanic parse(String text, Locale locale) throws ParseException {
        Collection<Mechanic> findMechanics = mechanicService.findAll();
        for(Mechanic mechanic : findMechanics) {
            String mechanicName = mechanic.getFirstName() + " " + mechanic.getLastName();
            if (mechanicName.equals(text)) {
                return mechanic;
            }
        }

        throw new ParseException("mechanic not found: " + text, 0);
    }

    @Override
    public String print(Mechanic object, Locale locale) {
        return object.getFirstName() + " " + object.getLastName();
    }
}
