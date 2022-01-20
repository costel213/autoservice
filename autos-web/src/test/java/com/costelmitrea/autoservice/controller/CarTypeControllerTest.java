package com.costelmitrea.autoservice.controller;

import com.costelmitrea.autoservice.model.CarType;
import com.costelmitrea.autoservice.services.CarTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class CarTypeControllerTest {

    @Mock
    CarTypeService carTypeService;

    @InjectMocks
    CarTypeController carTypeController;

    private static final Long TEST_CARTYPE_ID = 1L;
    CarType returnCarType;
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        returnCarType = CarType.builder().id(1L).build();
        mockMvc = MockMvcBuilders.standaloneSetup(carTypeController).build();
    }

    @Test
    void initCreationForm() throws Exception {
        mockMvc.perform(get("/carTypes/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("carType"))
                .andExpect(view().name("carTypes/createOrUpdateCarTypeForm"));
    }

    @Test
    void processCreationForm() throws Exception {
        mockMvc.perform(post("/carTypes/new")
                        .param("name", "off-road car"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/carTypesList"));
        verify(carTypeService).save(any());
    }

    @Test
    void initUpdateForm() throws Exception{
        when(carTypeService.findById(anyLong())).thenReturn(returnCarType);
        mockMvc.perform(get("/carTypes/{carTypeId}/edit", TEST_CARTYPE_ID))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("carType"))
                .andExpect(view().name("carTypes/createOrUpdateCarTypeForm"));
    }

    @Test
    void processUpdateCarTypeForm() throws Exception{
        mockMvc.perform(post("/carTypes/{carTypeId}/edit", TEST_CARTYPE_ID)
                        .param("name", "off-road car"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/carTypesList"));
    }

    @Test
    void deleteCarType() throws Exception{
        mockMvc.perform(get("/carTypes/{carTypeId}/deleted", TEST_CARTYPE_ID))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/carTypesList"));
        verify(carTypeService).deleteById(anyLong());
    }

    @Test
    void showCarTypes() throws Exception{
        mockMvc.perform(get("/carTypesList"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("carTypes"))
                .andExpect(view().name("carTypes/carTypesList"));
        verify(carTypeService).findAll();
    }
}