package com.costelmitrea.autoservice.controller;

import com.costelmitrea.autoservice.model.Specialty;
import com.costelmitrea.autoservice.services.SpecialtyService;
import com.costelmitrea.autoservice.util.AttributesName;
import com.costelmitrea.autoservice.util.ViewsName;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class SpecialtyController {

    private final SpecialtyService specialtyService;

    public SpecialtyController(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/specialties/new")
    public String initCreationForm(Map<String, Object> model) {
        Specialty specialty = new Specialty();
        model.put(AttributesName.SPECIALTY, specialty);
        return ViewsName.CREATE_OR_UPDATE_SPECIALTY_FORM;
    }

    @PostMapping("/specialties/new")
    public String processCreationForm(@Validated Specialty specialty, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return ViewsName.CREATE_OR_UPDATE_SPECIALTY_FORM;
        } else {
            this.specialtyService.save(specialty);
            return "redirect:/specialtiesList";
        }
    }

    @GetMapping("/specialties/{specialtyId}/edit")
    public String initUpdateForm(@PathVariable("specialtyId") Long specialtyId, Model model) {
        Specialty specialty = this.specialtyService.findById(specialtyId);
        model.addAttribute(specialty);
        return ViewsName.CREATE_OR_UPDATE_SPECIALTY_FORM;
    }

    @PostMapping("/specialties/{specialtyId}/edit")
    public String processUpdateSpecialtyForm(@Validated Specialty specialty, BindingResult bindingResult,
                                          @PathVariable("specialtyId") Long specialtyId) {
        if(bindingResult.hasErrors()) {
            return ViewsName.CREATE_OR_UPDATE_SPECIALTY_FORM;
        } else {
            specialty.setId(specialtyId);
            this.specialtyService.save(specialty);
            return "redirect:/specialtiesList";
        }
    }

    @GetMapping("/specialties/{specialtyId}/deleted")
    public String deleteSpecialty(@PathVariable("specialtyId") Long specialtyId) {
        this.specialtyService.deleteById(specialtyId);
        return "redirect:/specialtiesList";
    }

    @GetMapping("/specialtiesList")
    public String showSpecialties(Model model) {
        model.addAttribute(AttributesName.SPECIALTIES, specialtyService.findAll());
        return ViewsName.SPECIALTIES_LIST;
    }
}
