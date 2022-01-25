package com.costelmitrea.autoservice.controller;

import com.costelmitrea.autoservice.model.Client;
import com.costelmitrea.autoservice.services.ClientService;
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
        model.put(AttributesName.CLIENT, client);
        return ViewsName.CREATE_OR_UPDATE_CLIENT_FORM;
    }

    @PostMapping("/clients/new")
    public String processCreationForm(@Validated Client client, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return ViewsName.CREATE_OR_UPDATE_CLIENT_FORM;
        } else {
            this.clientService.save(client);
            return "redirect:/clients/" + client.getId();
        }
    }

    @GetMapping("/clients/find")
    public String initFindForm(Map<String, Object> model) {
        model.put(AttributesName.CLIENT, new Client());
        return ViewsName.FIND_CLIENTS;
    }

    @GetMapping("/clients")
    public String processFindForm(Client client, BindingResult bindingResult, Map<String, Object> model) {
        if(client.getLastName() == null) {
            client.setLastName("");
        }

        Collection<Client> results = this.clientService.findAllByLastNameLike("%" + client.getLastName() + "%");
        if(results.isEmpty()) {
            bindingResult.rejectValue("lastName", "notFound", "not found");
            return ViewsName.FIND_CLIENTS;
        } else if(results.size() == 1) {
            client = results.iterator().next();
            return "redirect:/clients/" + client.getId();
        } else {
            model.put(AttributesName.CLIENTS, results);
            return ViewsName.CLIENTS_LIST;
        }
    }

    @GetMapping("/clients/{clientId}/edit")
    public String initUpdateForm(@PathVariable("clientId") Long clientId, Model model) {
        Client client = this.clientService.findById(clientId);
        model.addAttribute(client);
        return ViewsName.CREATE_OR_UPDATE_CLIENT_FORM;
    }

    @PostMapping("/clients/{clientId}/edit")
    public String processUpdateClientForm(@Validated Client client, BindingResult bindingResult,
                                          @PathVariable("clientId") Long clientId) {
        if(bindingResult.hasErrors()) {
            return ViewsName.CREATE_OR_UPDATE_CLIENT_FORM;
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
        return ViewsName.SUCCESS_DELETE_CLIENT;
    }

    @GetMapping("/clients/{clientId}/deleted")
    public String deleteClient(@PathVariable("clientId") Long clientId) {
        this.clientService.deleteById(clientId);
        if(this.clientService.findAll().size() == 1) {
            Client client = this.clientService.findAll().iterator().next();
            return "redirect:/clients/" + client.getId();
        }
        return ViewsName.CLIENTS_LIST;
    }

    @GetMapping("/clients/{clientId}")
    public ModelAndView showClient(@PathVariable("clientId") Long clientId) {
        ModelAndView modelAndView = new ModelAndView(ViewsName.CLIENT_DETAILS);
        modelAndView.addObject(this.clientService.findById(clientId));
        return modelAndView;
    }

    @GetMapping("/clientsList")
    public String showClients(Model model) {
        model.addAttribute(AttributesName.CLIENTS, clientService.findAll());
        return ViewsName.CLIENTS_LIST;
    }
}
