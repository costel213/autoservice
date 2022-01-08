package com.costelmitrea.autoservice.controller;

import com.costelmitrea.autoservice.model.Mechanic;
import com.costelmitrea.autoservice.services.MechanicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

@Controller
public class MechanicController {

    private final MechanicService mechanicService;

    public MechanicController(MechanicService mechanicService) {
        this.mechanicService = mechanicService;
    }

    @RequestMapping("mechanics.html")
    public String listMechanics(Model model) {
        model.addAttribute("mechanics", mechanicService.findAll());
        return "mechanics/index";
    }

    @GetMapping("/api/mechanics")
    public  @ResponseBody Set<Mechanic> getMechanicsJson() {
        return mechanicService.findAll();
    }
}
