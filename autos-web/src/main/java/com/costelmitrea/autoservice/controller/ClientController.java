package com.costelmitrea.autoservice.controller;

import com.costelmitrea.autoservice.model.Client;
import com.costelmitrea.autoservice.services.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.Map;

@Controller
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/clients/new")
    public String initCreationForm(Map<String, Object> model) {
        Client client = new Client();
        model.put("client", client);
        return "clients/createOrUpdateClientForm";
    }

    @PostMapping("/clients/new")
    public String processCreationForm(@Validated Client client, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "clients/createOrUpdateClientForm";
        } else {
            this.clientService.save(client);
            return "redirect:/clients/" + client.getId();
        }
    }

    @GetMapping("/clients/find")
    public String initFindForm(Map<String, Object> model) {
        model.put("client", new Client());
        return "clients/findClients";
    }

    @GetMapping("/clients")
    public String processFindForm(Client client, BindingResult bindingResult, Map<String, Object> model) {
        if(client.getLastName() == null) {
            client.setLastName("");
        }

        Collection<Client> results = this.clientService.findAllByLastNameLike("%" + client.getLastName() + "%");
        if(results.isEmpty()) {
            bindingResult.rejectValue("lastName", "notFound", "not found");
            return "clients/findClients";
        } else if(results.size() == 1) {
            client = results.iterator().next();
            return "redirect:/clients/" + client.getId();
        } else {
            model.put("clients", results);
            return "clients/clientsList";
        }
    }

    @GetMapping("/clients/{clientId}/edit")
    public String initUpdateForm(@PathVariable("clientId") Long clientId, Model model) {
        Client client = this.clientService.findById(clientId);
        model.addAttribute(client);
        return "clients/createOrUpdateClientForm";
    }

    @PostMapping("/clients/{clientId}/edit")
    public String processUpdateClientForm(@Validated Client client, BindingResult bindingResult,
                                          @PathVariable("clientId") Long clientId) {
        if(bindingResult.hasErrors()) {
            return "clients/createOrUpdateClientForm";
        } else {
            client.setId(clientId);
            this.clientService.save(client);
            return "redirect:/clients/{clientId}";
        }
    }

    @GetMapping("/clients/{clientId}/deletedSuccessfully")
    public String initDeleteForm(@PathVariable("clientId") Long clientId, Model model) {
        Client client = this.clientService.findById(clientId);
        model.addAttribute(client);
        this.clientService.delete(client);
        return "clients/successDeleteClient";
    }

    @GetMapping("/clients/{clientId}/deleted")
    public String deleteClient(@PathVariable("clientId") Long clientId) {
        this.clientService.deleteById(clientId);
        if(this.clientService.findAll().size() == 1) {
            Client client = this.clientService.findAll().iterator().next();
            return "redirect:/clients/" + client.getId();
        }
        return "clients/clientsList";
    }

    @GetMapping("/clients/{clientId}")
    public ModelAndView showClient(@PathVariable("clientId") Long clientId) {
        ModelAndView modelAndView = new ModelAndView("clients/clientDetails");
        modelAndView.addObject(this.clientService.findById(clientId));
        return modelAndView;
    }

    @GetMapping("/clientsList")
    public String showClients(Model model) {
        model.addAttribute("clients", clientService.findAll());
        return "clients/clientsList";
    }
}
