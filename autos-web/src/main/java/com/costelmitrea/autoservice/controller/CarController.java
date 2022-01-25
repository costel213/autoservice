package com.costelmitrea.autoservice.controller;

import com.costelmitrea.autoservice.model.Car;
import com.costelmitrea.autoservice.model.CarType;
import com.costelmitrea.autoservice.model.Client;
import com.costelmitrea.autoservice.services.CarService;
import com.costelmitrea.autoservice.services.CarTypeService;
import com.costelmitrea.autoservice.services.ClientService;
import com.costelmitrea.autoservice.util.AttributesName;
import com.costelmitrea.autoservice.util.ViewsName;
import com.costelmitrea.autoservice.validator.CarValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.util.Collection;

@Controller
@RequestMapping("/clients/{clientId}")
public class CarController {

    private final CarService carService;
    private final ClientService clientService;
    private final CarTypeService carTypeService;

    public CarController(CarService carService, ClientService clientService, CarTypeService carTypeService) {
        this.carService = carService;
        this.clientService = clientService;
        this.carTypeService = carTypeService;
    }

    @ModelAttribute(AttributesName.TYPES)
    public Collection<CarType> populateCarTypes() {
        return this.carTypeService.findAll();
    }

    @ModelAttribute(AttributesName.CLIENT)
    public Client findClient(@PathVariable("clientId") Long clientId) {
        return this.clientService.findById(clientId);
    }

    @InitBinder("client")
    public void initClientBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @InitBinder("car")
    public void initCarBinder(WebDataBinder dataBinder) {
        dataBinder.setValidator(new CarValidator());

        dataBinder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalDate.parse(text));
            }
        });
    }

    @GetMapping("/cars/new")
    public String initCreationForm(Client client, ModelMap model) {
        Car car = new Car();
        client.addCar(car);
        model.put(AttributesName.CAR, car);
        return ViewsName.CREATE_OR_UPDATE_CAR_FORM;
    }

    @PostMapping("/cars/new")
    public String processCreationForm(Client client, @Validated Car car, BindingResult bindingResult,
                                      ModelMap model) {
        if(StringUtils.hasLength(car.getModel()) && car.isNew() && client.getCar(car.getModel(), true) != null) {
            bindingResult.rejectValue("model", "duplicate", "already exists");
        }
        client.addCar(car);
        if(bindingResult.hasErrors()){
            model.put(AttributesName.CAR, car);
            return ViewsName.CREATE_OR_UPDATE_CAR_FORM;
        } else {
            this.carService.save(car);
            return "redirect:/clients/{clientId}";
        }
    }

    @GetMapping("/cars/{carId}/edit")
    public String initUpdateForm(@PathVariable("carId") Long carId, ModelMap model) {
        Car car = this.carService.findById(carId);
        model.put(AttributesName.CAR, car);
        return ViewsName.CREATE_OR_UPDATE_CAR_FORM;
    }

    @PostMapping("/cars/{carId}/edit")
    public String processUpdateForm(@Validated Car car, BindingResult bindingResult, Client client, ModelMap model) {
        if(bindingResult.hasErrors()) {
            car.setOwner(client);
            model.put(AttributesName.CAR, car);
            return ViewsName.CREATE_OR_UPDATE_CAR_FORM;
        } else {
            client.addCar(car);
            this.carService.save(car);
            return "redirect:/clients/{clientId}";
        }
    }

    @GetMapping("/cars/{carId}/deleted")
    public String deleteCar(@PathVariable("carId") Long carId) {
        this.carService.deleteById(carId);
        return "redirect:/clients/{clientId}";
    }
}
