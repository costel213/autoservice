package com.costelmitrea.autoservice.validator;

import com.costelmitrea.autoservice.model.Car;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CarValidator implements Validator {

    private static final String REQUIRED = "required";

    @Override
    public boolean supports(Class<?> clazz) {
        return Car.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Car car = (Car) target;
        String model = car.getModel();
        // name validation
        if (!StringUtils.hasLength(model)) {
            errors.rejectValue("name", REQUIRED, REQUIRED);
        }

        // type validation
        if (car.isNew() && car.getCarType() == null) {
            errors.rejectValue("carType", REQUIRED, REQUIRED);
        }

        // date of production validation
        if (car.getDateOfProduction() == null) {
            errors.rejectValue("dateOfProduction", REQUIRED, REQUIRED);
        }
    }
}
