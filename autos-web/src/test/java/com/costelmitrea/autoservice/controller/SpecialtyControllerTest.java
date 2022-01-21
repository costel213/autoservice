package com.costelmitrea.autoservice.controller;

import com.costelmitrea.autoservice.model.Specialty;
import com.costelmitrea.autoservice.services.SpecialtyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class SpecialtyControllerTest {

    @Mock
    SpecialtyService specialtyService;

    @InjectMocks
    SpecialtyController specialtyController;

    private static final Long TEST_SPECIALTY_ID = 1L;
    MockMvc mockMvc;
    Specialty specialty;

    @BeforeEach
    void setUp() {
        specialty = new Specialty();
        specialty.setId(TEST_SPECIALTY_ID);
        specialty.setName("Electrical/Electronic Systems");

        mockMvc = MockMvcBuilders.standaloneSetup(specialtyController).build();
    }

    @Test
    void initCreationForm() throws Exception {
        mockMvc.perform(get("/specialties/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("specialty"))
                .andExpect(view().name("specialties/createOrUpdateSpecialtyForm"));
    }

    @Test
    void processCreationFormSuccess() throws Exception{
        mockMvc.perform(post("/specialties/new")
                        .param("name", "Electrical/Electronic Systems"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/specialtiesList"));

        verify(specialtyService).save(any());
    }

    @Test
    void processCreationFormHasErrors() throws Exception{
        mockMvc.perform(post("/specialties/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("specialties/createOrUpdateSpecialtyForm"));
    }

    @Test
    void initUpdateForm() throws Exception {
        when(specialtyService.findById(TEST_SPECIALTY_ID)).thenReturn(specialty);
        mockMvc.perform(get("/specialties/{specialtyId}/edit", TEST_SPECIALTY_ID))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("specialty"))
                .andExpect(model().attribute("specialty", hasProperty("name", is("Electrical/Electronic Systems"))))
                .andExpect(view().name("specialties/createOrUpdateSpecialtyForm"));
    }

    @Test
    void processUpdateSpecialtyFormSuccess() throws Exception {
        mockMvc.perform(post("/specialties/{specialtyId}/edit", TEST_SPECIALTY_ID)
                        .param("name", "Brakes"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/specialtiesList"));
        verify(specialtyService).save(any());
    }

    @Test
    void processUpdateSpecialtyFormHasErrors() throws Exception {
        mockMvc.perform(post("/specialties/{specialtyId}/edit", TEST_SPECIALTY_ID))
                .andExpect(status().isOk())
                .andExpect(view().name("specialties/createOrUpdateSpecialtyForm"));
    }

    @Test
    void deleteSpecialty() throws Exception {
        mockMvc.perform(get("/specialties/{specialtyId}/deleted", TEST_SPECIALTY_ID))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/specialtiesList"));
        verify(specialtyService).deleteById(anyLong());
    }

    @Test
    void showSpecialties() throws Exception{
        mockMvc.perform(get("/specialtiesList"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("specialties"))
                .andExpect(view().name("specialties/specialtiesList"));
        verify(specialtyService).findAll();
    }
}