package com.costelmitrea.autoservice.controller;

import com.costelmitrea.autoservice.model.Experience;
import com.costelmitrea.autoservice.model.Mechanic;
import com.costelmitrea.autoservice.services.ExperienceService;
import com.costelmitrea.autoservice.services.MechanicService;
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
class ExperienceControllerTest {

    @Mock
    ExperienceService experienceService;

    @Mock
    MechanicService mechanicService;

    @InjectMocks
    ExperienceController experienceController;

    Mechanic mechanic;
    Experience experience;
    private static final Long TEST_EXPER_ID = 1L;
    private static final Long TEST_MECHANIC_ID = 1L;
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mechanic = Mechanic.builder().id(1L).build();
        experience = Experience.builder().id(1L).timeInterval("10/2017-12/2021").position("Suspension and Steering Mechanic").build();
        mockMvc = MockMvcBuilders.standaloneSetup(experienceController).build();
    }

    @Test
    void initCreationForm() throws Exception{
        when(mechanicService.findById(TEST_MECHANIC_ID)).thenReturn(mechanic);
        mockMvc.perform(get("/mechanics/{mechanicId}/experience/new", TEST_MECHANIC_ID))
                .andExpect(model().attributeExists("mechanic"))
                .andExpect(model().attributeExists("exper"))
                .andExpect(status().isOk())
                .andExpect(view().name("experience/createOrUpdateExperienceForm"));
    }

    @Test
    void processCreationFormSuccess() throws Exception{
        when(mechanicService.findById(TEST_MECHANIC_ID)).thenReturn(mechanic);
        mockMvc.perform(post("/mechanics/{mechanicId}/experience/new", TEST_MECHANIC_ID)
                        .param("timeInterval", "02/2010-12/2017")
                        .param("position", "Engine Repair Mechanic"))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("mechanic"))
                .andExpect(view().name("redirect:/mechanics/{mechanicId}"));

        verify(experienceService).save(any());
    }

    @Test
    void processCreationFormHasErrors() throws Exception{
        when(mechanicService.findById(TEST_MECHANIC_ID)).thenReturn(mechanic);
        mockMvc.perform(post("/mechanics/{mechanicId}/experience/new", TEST_MECHANIC_ID))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("mechanic"))
                .andExpect(view().name("experience/createOrUpdateExperienceForm"));
    }

    @Test
    void initUpdateForm() throws Exception{
        when(mechanicService.findById(TEST_MECHANIC_ID)).thenReturn(mechanic);
        when(experienceService.findById(TEST_EXPER_ID)).thenReturn(experience);
        mockMvc.perform(get("/mechanics/{mechanicId}/experience/{experienceId}/edit", TEST_MECHANIC_ID, TEST_EXPER_ID))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("exper"))
                .andExpect(model().attribute("exper", hasProperty("timeInterval", is("10/2017-12/2021"))))
                .andExpect(model().attribute("exper", hasProperty("position", is("Suspension and Steering Mechanic"))))
                .andExpect(view().name("experience/createOrUpdateExperienceForm"));
    }

    @Test
    void processUpdateFormSuccess() throws Exception{
        when(mechanicService.findById(TEST_MECHANIC_ID)).thenReturn(mechanic);
        mockMvc.perform(post("/mechanics/{mechanicId}/experience/{experienceId}/edit", TEST_MECHANIC_ID, TEST_EXPER_ID)
                        .param("timeInterval", "10/2017-12/2021")
                        .param("position", "Suspension and Steering Mechanic"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/mechanics/{mechanicId}"));
        verify(experienceService).save(any());
    }

    @Test
    void processUpdateFormHasErrors() throws Exception {
        when(mechanicService.findById(TEST_MECHANIC_ID)).thenReturn(mechanic);
        mockMvc.perform(post("/mechanics/{mechanicId}/experience/{experienceId}/edit", TEST_MECHANIC_ID, TEST_EXPER_ID))
                .andExpect(status().isOk())
                .andExpect(view().name("experience/createOrUpdateExperienceForm"));
    }

    @Test
    void deleteExperience() throws Exception{
        mockMvc.perform(get("/mechanics/{mechanicId}/experience/{experienceId}/successfullyDeleted", TEST_MECHANIC_ID, TEST_EXPER_ID))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/mechanics/{mechanicId}"));
        verify(experienceService).deleteById(anyLong());
    }
}