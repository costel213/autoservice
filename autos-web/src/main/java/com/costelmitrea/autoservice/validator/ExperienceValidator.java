package com.costelmitrea.autoservice.validator;

import com.costelmitrea.autoservice.model.Experience;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ExperienceValidator implements Validator {

    private static final String REQUIRED = "required";

    @Override
    public boolean supports(Class<?> clazz) {
        return Experience.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Experience experience = (Experience) target;
        String timeInterval = experience.getTimeInterval();
        // name validation
        if (!StringUtils.hasLength(timeInterval)) {
            errors.rejectValue("timeInterval", REQUIRED, REQUIRED);
        }

        // type validation
        if (experience.isNew() && experience.getPosition() == null) {
            errors.rejectValue("position", REQUIRED, REQUIRED);
        }
    }
}
