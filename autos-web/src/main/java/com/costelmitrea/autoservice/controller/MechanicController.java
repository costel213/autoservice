package com.costelmitrea.autoservice.controller;

import com.costelmitrea.autoservice.model.Mechanic;
import com.costelmitrea.autoservice.model.Specialty;
import com.costelmitrea.autoservice.services.ExperienceService;
import com.costelmitrea.autoservice.services.MechanicService;
import com.costelmitrea.autoservice.services.SpecialtyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

@Controller
public class MechanicController {

    private final MechanicService mechanicService;
    private final ExperienceService experienceService;
    private final SpecialtyService specialtyService;

    public MechanicController(MechanicService mechanicService, ExperienceService experienceService, SpecialtyService specialtyService) {
        this.mechanicService = mechanicService;
        this.experienceService = experienceService;
        this.specialtyService = specialtyService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("specialties")
    public Collection<Specialty> populateSpecialties() {
        return this.specialtyService.findAll();
    }

    @GetMapping("/mechanics/new")
    public String initCreationForm(Map<String, Object> model) {
        Mechanic mechanic = new Mechanic();
        model.put("mechanic", mechanic);
        return "mechanics/createOrUpdateMechanicForm";
    }

    @PostMapping("/mechanics/new")
    public String processCreationForm(@Validated Mechanic mechanic, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "mechanics/createOrUpdateMechanicForm";
        } else {
            this.mechanicService.save(mechanic);
            return "redirect:/mechanics/" + mechanic.getId();
        }
    }

    @GetMapping("/mechanics/{mechanicId}/edit")
    public String initUpdateForm(@PathVariable("mechanicId") Long mechanicId, Model model) {
        Mechanic mechanic = this.mechanicService.findById(mechanicId);
        model.addAttribute(mechanic);
        return "mechanics/createOrUpdateMechanicForm";
    }

    @PostMapping("/mechanics/{mechanicId}/edit")
    public String processUpdateForm(@Validated Mechanic mechanic, BindingResult bindingResult,
                                    @PathVariable("mechanicId") Long mechanicId) {
        if(bindingResult.hasErrors()) {
            return "mechanics/createOrUpdateMechanicForm";
        } else {
            mechanic.setId(mechanicId);
            this.mechanicService.save(mechanic);
            return "redirect:/mechanics/{mechanicId}";
        }
    }

    @GetMapping("/mechanics/{mechanicId}/deletedSuccessfully")
    public String initDeleteForm(@PathVariable("mechanicId") Long mechanicId, Model model) {
        Mechanic mechanic = this.mechanicService.findById(mechanicId);
        model.addAttribute(mechanic);
        this.mechanicService.delete(mechanic);
        return "mechanics/successDeleteMechanic";
    }

    @GetMapping("/mechanics/{mechanicId}/deleted")
    public String deleteMechanic(@PathVariable("mechanicId") Long mechanicId) {
        this.mechanicService.deleteById(mechanicId);
        if(this.mechanicService.findAll().size() == 1) {
            Mechanic mechanic = this.mechanicService.findAll().iterator().next();
            return "redirect:/mechanics/" + mechanic.getId();
        }

        return "mechanics/index";
    }

    @GetMapping("mechanics")
    public String listMechanics(Model model) {
        model.addAttribute("mechanics", mechanicService.findAll());
        return "mechanics/index";
    }

    @GetMapping("/mechanics/{mechanicId}")
    public ModelAndView showMechanic(@PathVariable("mechanicId") Long mechanicId) {
        ModelAndView modelAndView = new ModelAndView("mechanics/mechanicDetails");
        modelAndView.addObject(this.mechanicService.findById(mechanicId));
        return modelAndView;
    }

    @GetMapping("/api/mechanics")
    public  @ResponseBody Set<Mechanic> getMechanicsJson() {
        return mechanicService.findAll();
    }
}
