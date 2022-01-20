package com.costelmitrea.autoservice.controller;

import com.costelmitrea.autoservice.model.Car;
import com.costelmitrea.autoservice.model.CarType;
import com.costelmitrea.autoservice.model.Client;
import com.costelmitrea.autoservice.model.Visit;
import com.costelmitrea.autoservice.services.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ClientControllerTest {

    @Mock
    ClientService clientService;

    @InjectMocks
    ClientController clientController;

    Client client;
    List<Client> clients;
    MockMvc mockMvc;
    private static final Long TEST_CLIENT_ID = 1L;

    @BeforeEach
    void setUp() {

        client = new Client();
        client.setId(TEST_CLIENT_ID);
        client.setFirstName("George");
        client.setLastName("Franklin");
        client.setAddress("110 W. Liberty St.");
        client.setCity("Madison");
        client.setTelephone("6085551023");
        Car car = new Car();
        CarType carType = new CarType();
        carType.setName("family car");
        car.setId(1L);
        car.setCarType(carType);
        car.setModel("Range Rover");
        car.setDateOfProduction(LocalDate.now());
        client.setCarsInternal(Collections.singleton(car));

        Visit visit = new Visit();
        visit.setDate(LocalDate.now());

        clients = new LinkedList<>();
        clients.add(client);
        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
    }

    @Test
    void initCreationForm() throws Exception{
        mockMvc.perform(get("/clients/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("client"))
                .andExpect(view().name("clients/createOrUpdateClientForm"));
    }

    @Test
    void processCreationFormSuccess() throws Exception{
        mockMvc.perform(post("/clients/new")
                        .param("firstName", "Joe")
                        .param("lastName", "Bloggs")
                        .param("address", "123 Caramel Street")
                        .param("city", "London")
                        .param("telephone", "0131676163"))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("client"));

        verify(clientService).save(ArgumentMatchers.any());
    }

    @Test
    void processCreationFormHasErrors() throws Exception {
        mockMvc.perform(post("/clients/new").param("firstName", "Joe")
                        .param("lastName", "Bloggs")
                        .param("city", "London"))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors("client"))
                .andExpect(model().attributeHasFieldErrors("client", "address"))
                .andExpect(model().attributeHasFieldErrors("client", "telephone"))
                .andExpect(view().name("clients/createOrUpdateClientForm"));
    }

    @Test
    void initFindForm() throws Exception{
        mockMvc.perform(get("/clients/find"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("client"))
                .andExpect(view().name("clients/findClients"));
    }

    @Test
    void processFindFormSuccess() throws Exception{
        mockMvc.perform(get("/clients"))
                .andExpect(status().isOk())
                .andExpect(view().name("clients/findClients"));
    }

    @Test
    void processFindFormByLastName() throws Exception {
        when(this.clientService.findAllByLastNameLike("%Franklin%")).thenReturn(clients);
        mockMvc.perform(get("/clients")
                        .param("lastName", "Franklin"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/clients/" + TEST_CLIENT_ID));
    }

    @Test
    void initUpdateForm() throws Exception{
        when(clientService.findById(TEST_CLIENT_ID)).thenReturn(client);
        mockMvc.perform(get("/clients/{clientId}/edit", TEST_CLIENT_ID))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("client"))
                .andExpect(model().attribute("client", hasProperty("lastName", is("Franklin"))))
                .andExpect(model().attribute("client", hasProperty("firstName", is("George"))))
                .andExpect(model().attribute("client", hasProperty("address", is("110 W. Liberty St."))))
                .andExpect(model().attribute("client", hasProperty("city", is("Madison"))))
                .andExpect(model().attribute("client", hasProperty("telephone", is("6085551023"))))
                .andExpect(view().name("clients/createOrUpdateClientForm"));
    }

    @Test
    void processUpdateClientFormSuccess() throws Exception{
        mockMvc.perform(post("/clients/{clientId}/edit", TEST_CLIENT_ID)
                        .param("firstName", "Joe")
                        .param("lastName", "Bloggs")
                        .param("address", "123 Caramel Street")
                        .param("city", "London")
                        .param("telephone", "0161629158"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/clients/{clientId}"));
    }

    @Test
    void processUpdateFormHasErrors() throws Exception {
        mockMvc.perform(post("/clients/{clientId}/edit", TEST_CLIENT_ID)
                        .param("firstName", "Joe")
                        .param("lastName", "Bloggs")
                        .param("city", "London"))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors("client"))
                .andExpect(model().attributeHasFieldErrors("client", "address"))
                .andExpect(model().attributeHasFieldErrors("client", "telephone"))
                .andExpect(view().name("clients/createOrUpdateClientForm"));
    }

    @Test
    void initDeleteForm() throws Exception{
        when(clientService.findById(TEST_CLIENT_ID)).thenReturn(client);
        mockMvc.perform(get("/clients/{clientId}/deletedSuccessfully", TEST_CLIENT_ID))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("client"))
                .andExpect(view().name("clients/successDeleteClient"));
    }

    @Test
    void deleteClient() throws Exception {
        mockMvc.perform(get("/clients/{clientId}/deleted", TEST_CLIENT_ID))
                .andExpect(status().isOk())
                .andExpect(view().name("clients/clientsList"));
    }

    @Test
    void showClient() throws Exception {
        when(clientService.findById(TEST_CLIENT_ID)).thenReturn(client);
        mockMvc.perform(get("/clients/{clientId}", TEST_CLIENT_ID))
                .andExpect(status().isOk())
                .andExpect(view().name("clients/clientDetails"))
                .andExpect(model().attribute("client", hasProperty("id", is(1L))));
    }
}