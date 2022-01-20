package com.costelmitrea.autoservice.controller;

import com.costelmitrea.autoservice.model.Car;
import com.costelmitrea.autoservice.model.CarType;
import com.costelmitrea.autoservice.model.Client;
import com.costelmitrea.autoservice.repositories.CarTypeRepository;
import com.costelmitrea.autoservice.repositories.ClientRepository;
import com.costelmitrea.autoservice.services.CarService;
import com.costelmitrea.autoservice.services.CarTypeService;
import com.costelmitrea.autoservice.services.ClientService;
import com.costelmitrea.autoservice.services.springdatajpa.CarTypeSDJpaService;
import com.costelmitrea.autoservice.services.springdatajpa.ClientSDJpaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class CarControllerTest {

    @Mock
    CarService carService;

    @Mock
    ClientService clientService;

    @Mock
    CarTypeService carTypeService;

    @Mock
    ClientRepository clientRepository;

    @Mock
    CarTypeRepository carTypeRepository;

    @InjectMocks
    CarTypeSDJpaService carTypeSDJpaService;

    @InjectMocks
    ClientSDJpaService clientSDJpaService;

    @InjectMocks
    CarController carController;

    Set<CarType> types;
    Set<Car> cars;
    Client returnClient;
    private static final Long TEST_CLIENT_ID = 1L;
    private static final Long TEST_CAR_ID = 1L;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        cars = new HashSet<>();
        cars.add(Car.builder().id(1L).build());
        cars.add(Car.builder().id(2L).build());

        returnClient = Client.builder().id(1L).lastName("Wilson").build();

        types = new HashSet<>();
        types.add(CarType.builder().id(1L).build());
        types.add(CarType.builder().id(2L).build());

        mockMvc = MockMvcBuilders.standaloneSetup(carController).build();
    }

    @Test
    void populateCarTypes() {
        Set<CarType> returnCarTypesSet = new HashSet<>();
        returnCarTypesSet.add(CarType.builder().id(1L).build());
        returnCarTypesSet.add(CarType.builder().id(2L).build());
        when(carTypeRepository.findAll()).thenReturn(returnCarTypesSet);
        Set<CarType> carTypes = carTypeSDJpaService.findAll();
        assertNotNull(carTypes);
        assertEquals(2, carTypes.size());
    }

    @Test
    void findClient() {
        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(returnClient));
        Client client = clientSDJpaService.findById(1L);
        assertNotNull(client);
    }

    @Test
    void initCreationForm() throws Exception {
        when(clientService.findById(1L)).thenReturn(returnClient);
        when(carTypeService.findAll()).thenReturn(types);
        mockMvc.perform(get("/clients/1/cars/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("client"))
                .andExpect(model().attributeExists("car"))
                .andExpect(view().name("cars/createOrUpdateCarForm"));
    }

    @Test
    void processCreationFormSuccess() throws Exception {
        when(clientService.findById(anyLong())).thenReturn(returnClient);
        when(carTypeService.findAll()).thenReturn(types);

        mockMvc.perform(post("/clients/{clientId}/cars/new", TEST_CLIENT_ID)
                        .param("model", "Ford")
                        .param("carType", "family car")
                        .param("dateOfProduction", "2015-02-12"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/clients/{clientId}"));
        verify(carService).save(any());
    }

    @Test
    void processCreationFormHasErrors() throws Exception {
        when(clientService.findById(anyLong())).thenReturn(returnClient);
        when(carTypeService.findAll()).thenReturn(types);

        mockMvc.perform(post("/clients/{clientId}/cars/new", TEST_CLIENT_ID)
                        .param("model", "Volvo")
                        .param("dateOfProduction", "2015-02-12"))
                .andExpect(model().attributeHasNoErrors("client"))
                .andExpect(model().attributeHasErrors("car"))
                .andExpect(model().attributeHasFieldErrors("car", "carType"))
                .andExpect(model().attributeHasFieldErrorCode("car", "carType", "required"))
                .andExpect(status().isOk())
                .andExpect(view().name("cars/createOrUpdateCarForm"));
    }

    @Test
    void initUpdateForm() throws Exception{
        when(clientService.findById(anyLong())).thenReturn(returnClient);
        when(carTypeService.findAll()).thenReturn(types);
        when(carService.findById(anyLong())).thenReturn(Car.builder().id(2L).build());
        mockMvc.perform(get("/clients/1/cars/2/edit"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("client"))
                .andExpect(model().attributeExists("car"))
                .andExpect(view().name("cars/createOrUpdateCarForm"));
    }

    @Test
    void processUpdateFormSuccess() throws Exception {
        when(clientService.findById(anyLong())).thenReturn(returnClient);
        when(carTypeService.findAll()).thenReturn(types);

        mockMvc.perform(post("/clients/{clientId}/cars/{carId}/edit", TEST_CLIENT_ID, TEST_CAR_ID)
                        .param("model", "Hyundai")
                        .param("carType", "family car")
                        .param("dateOfProduction", "2015-02-12"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/clients/{clientId}"));
    }

    @Test
    void processUpdateFormHasErrors() throws Exception {
        when(clientService.findById(anyLong())).thenReturn(returnClient);
        when(carTypeService.findAll()).thenReturn(types);

        mockMvc.perform(post("/clients/{clientId}/cars/{carId}/edit", TEST_CLIENT_ID, TEST_CAR_ID)
                        .param("model", "Hyundai")
                        .param("dateOfProduction", "2015/02/12"))
                .andExpect(model().attributeHasNoErrors("client"))
                .andExpect(model().attributeHasErrors("car"))
                .andExpect(status().isOk())
                .andExpect(view().name("cars/createOrUpdateCarForm"));
    }

    @Test
    void deleteCar() throws Exception{
        when(clientService.findById(anyLong())).thenReturn(returnClient);
        when(carTypeService.findAll()).thenReturn(types);

        mockMvc.perform(get("/clients/{clientId}/cars/{carId}/deleted", TEST_CLIENT_ID,TEST_CAR_ID))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("client"))
                .andExpect(view().name("redirect:/clients/{clientId}"));
        verify(carService).deleteById(anyLong());
    }
}