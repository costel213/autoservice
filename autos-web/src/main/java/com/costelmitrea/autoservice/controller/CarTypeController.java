package com.costelmitrea.autoservice.controller;

import com.costelmitrea.autoservice.model.CarType;
import com.costelmitrea.autoservice.services.CarTypeService;
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
public class CarTypeController {

    private final CarTypeService carTypeService;

    public CarTypeController(CarTypeService carTypeService) {
        this.carTypeService = carTypeService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/carTypes/new")
    public String initCreationForm(Map<String, Object> model) {
        CarType carType = new CarType();
        model.put(AttributesName.CARTYPE, carType);
        return ViewsName.CREATE_OR_UPDATE_CARTYPE_FORM;
    }

    @PostMapping("/carTypes/new")
    public String processCreationForm(@Validated CarType carType, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return ViewsName.CREATE_OR_UPDATE_CARTYPE_FORM;
        } else {
            this.carTypeService.save(carType);
            return "redirect:/carTypesList";
        }
    }

    @GetMapping("/carTypes/{carTypeId}/edit")
    public String initUpdateForm(@PathVariable("carTypeId") Long carTypeId, Model model) {
        CarType carType = this.carTypeService.findById(carTypeId);
        model.addAttribute(carType);
        return ViewsName.CREATE_OR_UPDATE_CARTYPE_FORM;
    }

    @PostMapping("/carTypes/{carTypeId}/edit")
    public String processUpdateCarTypeForm(@Validated CarType carType, BindingResult bindingResult,
                                          @PathVariable("carTypeId") Long carTypeId) {
        if(bindingResult.hasErrors()) {
            return ViewsName.CREATE_OR_UPDATE_CARTYPE_FORM;
        } else {
            carType.setId(carTypeId);
            this.carTypeService.save(carType);
            return "redirect:/carTypesList";
        }
    }

    @GetMapping("/carTypes/{carTypeId}/deleted")
    public String deleteCarType(@PathVariable("carTypeId") Long carTypeId) {
        this.carTypeService.deleteById(carTypeId);
        return "redirect:/carTypesList";
    }

    @GetMapping("/carTypesList")
    public String showCarTypes(Model model) {
        model.addAttribute(AttributesName.CARTYPES, carTypeService.findAll());
        return ViewsName.CAR_TYPES_LIST;
    }
}
