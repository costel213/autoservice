package com.costelmitrea.autoservice.controller;

import com.costelmitrea.autoservice.model.Car;
import com.costelmitrea.autoservice.model.Mechanic;
import com.costelmitrea.autoservice.model.Visit;
import com.costelmitrea.autoservice.services.CarService;
import com.costelmitrea.autoservice.services.ClientService;
import com.costelmitrea.autoservice.services.MechanicService;
import com.costelmitrea.autoservice.services.VisitService;
import com.costelmitrea.autoservice.validator.CarValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.util.Collection;

@Controller
public class VisitController {

    private final VisitService visitService;
    private final CarService carService;
    private final MechanicService mechanicService;
    private final ClientService clientService;

    public VisitController(VisitService visitService, CarService carService, MechanicService mechanicService, ClientService clientService) {
        this.visitService = visitService;
        this.carService = carService;
        this.mechanicService = mechanicService;
        this.clientService = clientService;
    }

    @ModelAttribute("mechanics")
    public Collection<Mechanic> populateMechanics() {
        return this.mechanicService.findAll();
    }

    @InitBinder("client")
    public void initClientBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @InitBinder("car")
    public void initCarBinder(WebDataBinder dataBinder) {
        dataBinder.setValidator(new CarValidator());
    }

    @InitBinder("visit")
    public void initVisitBinder (WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");

        dataBinder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalDate.parse(text));
            }
        });
    }

    @ModelAttribute("car")
    public Car findCar (@PathVariable("carId") Long carId) {
        return this.carService.findById(carId);
    }

    @GetMapping("/clients/*/cars/{carId}/visits/new")
    public String initNewVisitForm(Car car, ModelMap model) {
        Visit visit = new Visit();
        car.addVisit(visit);
        model.put("visit", visit);
        return "visits/createOrUpdateVisitForm";
    }

    @PostMapping("/clients/{clientId}/cars/{carId}/visits/new")
    public String processNewVisitForm(Car car, @Validated Visit visit, BindingResult bindingResult,
                                      ModelMap model) {
        car.addVisit(visit);
        if(bindingResult.hasErrors()) {
            model.put("visit", visit);
            return "visits/createOrUpdateVisitForm";
        } else {
            this.visitService.save(visit);
            return "redirect:/clients/{clientId}";
        }
    }

    @GetMapping("/clients/{clientId}/cars/{carId}/visits/{visitId}/deleted")
    public String processDeleteVisit(@PathVariable("visitId") Long visitId) {
        this.visitService.deleteById(visitId);
        return "redirect:/clients/{clientId}";
    }
}
