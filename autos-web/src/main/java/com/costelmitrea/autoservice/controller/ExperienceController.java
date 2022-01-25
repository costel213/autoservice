package com.costelmitrea.autoservice.controller;

import com.costelmitrea.autoservice.model.Experience;
import com.costelmitrea.autoservice.model.Mechanic;
import com.costelmitrea.autoservice.services.ExperienceService;
import com.costelmitrea.autoservice.services.MechanicService;
import com.costelmitrea.autoservice.util.AttributesName;
import com.costelmitrea.autoservice.util.ViewsName;
import com.costelmitrea.autoservice.validator.ExperienceValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mechanics/{mechanicId}")
public class ExperienceController {

    private final ExperienceService experienceService;
    private final MechanicService mechanicService;

    public ExperienceController(ExperienceService experienceService, MechanicService mechanicService) {
        this.experienceService = experienceService;
        this.mechanicService = mechanicService;
    }

    @ModelAttribute(AttributesName.MECHANIC)
    public Mechanic findMechanic(@PathVariable("mechanicId") Long mechanicId) {
        return this.mechanicService.findById(mechanicId);
    }

    @InitBinder("mechanic")
    public void initMechanicBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @InitBinder("experience")
    public void initExperienceBinder(WebDataBinder dataBinder) {
        dataBinder.setValidator(new ExperienceValidator());
    }

    @GetMapping("/experience/new")
    public String initCreationForm(Mechanic mechanic, ModelMap model) {
        Experience experience = new Experience();
        mechanic.addExperience(experience);
        model.put(AttributesName.EXPERIENCE, experience);
        return ViewsName.CREATE_OR_UPDATE_EXPERIENCE_FORM;
    }

    @PostMapping("/experience/new")
    public String processCreationForm(Mechanic mechanic, @Validated Experience experience,
                                      BindingResult bindingResult, ModelMap model) {
        if(StringUtils.hasLength(experience.getTimeInterval()) && experience.isNew() && mechanic.getSingleExperience(experience.getTimeInterval(), true) != null) {
            bindingResult.rejectValue("timeInterval", "duplicate", "already exists");
        }
        mechanic.addExperience(experience);
        if(bindingResult.hasErrors()){
            model.put(AttributesName.EXPERIENCE, experience);
            return ViewsName.CREATE_OR_UPDATE_EXPERIENCE_FORM;
        } else {
            this.experienceService.save(experience);
            return "redirect:/mechanics/{mechanicId}";
        }
    }

    @GetMapping("/experience/{experienceId}/edit")
    public String initUpdateForm(@PathVariable("experienceId") Long experienceId, ModelMap model) {
        Experience experience = this.experienceService.findById(experienceId);
        model.put(AttributesName.EXPERIENCE, experience);
        return ViewsName.CREATE_OR_UPDATE_EXPERIENCE_FORM;
    }

    @PostMapping("/experience/{experienceId}/edit")
    public String processUpdateForm(@Validated Experience experience, BindingResult bindingResult,
                                    Mechanic mechanic, ModelMap model) {
        if(bindingResult.hasErrors()) {
            experience.setMechanic(mechanic);
            model.put(AttributesName.EXPERIENCE, experience);
            return ViewsName.CREATE_OR_UPDATE_EXPERIENCE_FORM;
        } else {
            mechanic.addExperience(experience);
            this.experienceService.save(experience);
            return "redirect:/mechanics/{mechanicId}";
        }
    }

    @GetMapping("/experience/{experienceId}/successfullyDeleted")
    public String deleteExperience(@PathVariable("experienceId") Long experienceId, @PathVariable("mechanicId") Long mechanicId) {
        this.experienceService.deleteById(experienceId);
        return "redirect:/mechanics/{mechanicId}";
    }
}
