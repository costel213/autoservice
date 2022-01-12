package com.costelmitrea.autoservice.controller;

import com.costelmitrea.autoservice.model.Mechanic;
import com.costelmitrea.autoservice.services.MechanicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@Controller
public class MechanicController {

    private final MechanicService mechanicService;

    public MechanicController(MechanicService mechanicService) {
        this.mechanicService = mechanicService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
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
