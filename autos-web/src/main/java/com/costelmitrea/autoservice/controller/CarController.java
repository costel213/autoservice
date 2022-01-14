package com.costelmitrea.autoservice.controller;

import com.costelmitrea.autoservice.model.Car;
import com.costelmitrea.autoservice.model.CarType;
import com.costelmitrea.autoservice.model.Client;
import com.costelmitrea.autoservice.services.CarService;
import com.costelmitrea.autoservice.services.CarTypeService;
import com.costelmitrea.autoservice.services.ClientService;
import com.costelmitrea.autoservice.validator.CarValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

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

    @ModelAttribute("types")
    public Collection<CarType> populateCarTypes() {
        return this.carTypeService.findAll();
    }

    @ModelAttribute("client")
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
    }

    @GetMapping("/cars/new")
    public String initCreationForm(Client client, ModelMap model) {
        Car car = new Car();
        client.addCar(car);
        model.put("car", car);
        return "cars/createOrUpdateCarForm";
    }

    @PostMapping("/cars/new")
    public String processCreationForm(Client client, @Validated Car car, BindingResult bindingResult,
                                      ModelMap model) {
        if(StringUtils.hasLength(car.getModel()) && car.isNew() && client.getCar(car.getModel(), true) != null) {
            bindingResult.rejectValue("model", "duplicate", "already exists");
        }
        client.addCar(car);
        if(bindingResult.hasErrors()){
            model.put("car", car);
            return "cars/createOrUpdateCarForm";
        } else {
            this.carService.save(car);
            return "redirect:/clients/{clientId}";
        }
    }

    @GetMapping("/cars/{carId}/edit")
    public String initUpdateForm(@PathVariable("carId") Long carId, ModelMap model) {
        Car car = this.carService.findById(carId);
        model.put("car", car);
        return "cars/createOrUpdateCarForm";
    }

    @PostMapping("/cars/{carId}/edit")
    public String processUpdateForm(@Validated Car car, BindingResult bindingResult, Client client, ModelMap model) {
        if(bindingResult.hasErrors()) {
            car.setOwner(client);
            model.put("car", car);
            return "cars/createOrUpdateCarForm";
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
