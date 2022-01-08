package com.costelmitrea.autoservice.formatter;

import com.costelmitrea.autoservice.model.CarType;
import com.costelmitrea.autoservice.services.CarTypeService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

@Component
public class CarTypeFormatter implements Formatter<CarType> {

    private final CarTypeService carTypeService;

    public CarTypeFormatter (CarTypeService carTypeService) {
        this.carTypeService = carTypeService;
    }

    @Override
    public CarType parse(String text, Locale locale) throws ParseException {
        Collection<CarType> findPetTypes = carTypeService.findAll();
        for(CarType type : findPetTypes) {
            if(type.getName().equals(text)) {
                return type;
            }
        }

        throw new ParseException("type not found: " + text, 0);
    }

    @Override
    public String print(CarType object, Locale locale) {
        return object.getName();
    }
}
