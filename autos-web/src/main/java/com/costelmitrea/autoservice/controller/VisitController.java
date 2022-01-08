package com.costelmitrea.autoservice.controller;

import com.costelmitrea.autoservice.model.Car;
import com.costelmitrea.autoservice.model.Visit;
import com.costelmitrea.autoservice.services.CarService;
import com.costelmitrea.autoservice.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.util.Map;

@Controller
public class VisitController {

    private final VisitService visitService;
    private final CarService carService;

    public VisitController(VisitService visitService, CarService carService) {
        this.visitService = visitService;
        this.carService = carService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");

        dataBinder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalDate.parse(text));
            }
        });
    }

    @ModelAttribute("visit")
    public Visit loadCarWithVisit (@PathVariable("carId") Long carId, Map<String, Object> model) {
        Car car = this.carService.findById(carId);
        model.put("car", car);
        Visit visit = new Visit();
        car.addVisit(visit);
        return visit;
    }

    @GetMapping("/clients/*/cars/{carId}/visits/new")
    public String initNewVisitForm(@PathVariable("carId") Long carId, Map<String, Object> model) {
        return "visits/createOrUpdateVisitForm";
    }

    @PostMapping("/clients/{clientId}/cars/{carId}/visits/new")
    public String processNewVisitForm(@Validated Visit visit, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "visits/createOrUpdateVisitForm";
        } else {
            this.visitService.save(visit);
            return "redirect:/clients/{clientId}";
        }
    }
}
