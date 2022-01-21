package com.costelmitrea.autoservice.controller;

import com.costelmitrea.autoservice.model.Experience;
import com.costelmitrea.autoservice.model.Mechanic;
import com.costelmitrea.autoservice.model.Specialty;
import com.costelmitrea.autoservice.model.Visit;
import com.costelmitrea.autoservice.services.MechanicService;
import com.costelmitrea.autoservice.services.SpecialtyService;
import com.costelmitrea.autoservice.services.VisitService;
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
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class MechanicControllerTest {

    @Mock
    MechanicService mechanicService;

    @Mock
    SpecialtyService specialtyService;

    @Mock
    VisitService visitService;

    @InjectMocks
    MechanicController mechanicController;

    MockMvc mockMvc;
    private static final Long TEST_MECHANIC_ID = 1L;

    Mechanic returnMechanic;
    Set<Specialty> specialties;
    Set<Experience> experienceSet;
    Set<Visit> visits;

    @BeforeEach
    void setUp() {
        specialties = new HashSet<>();
        specialties.add(Specialty.builder().id(1L).name("Engine Performance").build());
        specialties.add(Specialty.builder().id(2L).name("Electrical Systems").build());

        experienceSet = new HashSet<>();
        experienceSet.add(Experience.builder().id(1L).timeInterval("12/2013-10/2018").position("Mechanic").build());

        visits = new HashSet<>();
        Visit visit = Visit.builder().id(1L).date(LocalDate.now()).description("new visit").build();
        visits.add(visit);

        returnMechanic = new Mechanic();
        returnMechanic.setId(TEST_MECHANIC_ID);
        returnMechanic.setFirstName("Jurgen");
        returnMechanic.setLastName("Davies");
        returnMechanic.setAddress("57 Main St.");
        returnMechanic.setCity("Falticeni");
        returnMechanic.setTelephone("4758352617");
        returnMechanic.setSpecialtiesInternal(specialties);
        returnMechanic.setExperienceInternal(experienceSet);
        returnMechanic.setVisitsInternal(visits);

        mockMvc = MockMvcBuilders.standaloneSetup(mechanicController).build();
    }

    @Test
    void populateSpecialties() {
        assertEquals(2, specialties.size());
    }

    @Test
    void initCreationForm() throws Exception{
        mockMvc.perform(get("/mechanics/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("mechanic"))
                .andExpect(view().name("mechanics/createOrUpdateMechanicForm"));
    }

    @Test
    void processCreationFormSuccess() throws Exception{
        mockMvc.perform(post("/mechanics/new")
                        .param("firstName", "Jurgen")
                        .param("lastName", "Davies")
                        .param("address", "57 Main St.")
                        .param("city", "Falticeni")
                        .param("telephone", "4758352617"))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeExists("mechanic"));

        verify(mechanicService).save(ArgumentMatchers.any());
    }

    @Test
    void processCreationFormHasErrors() throws Exception {
        mockMvc.perform(post("/mechanics/new")
                        .param("firstName", "Jurgen")
                        .param("lastName", "Davies")
                        .param("city", "Falticeni"))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors("mechanic"))
                .andExpect(model().attributeHasFieldErrors("mechanic", "address"))
                .andExpect(model().attributeHasFieldErrors("mechanic", "telephone"))
                .andExpect(view().name("mechanics/createOrUpdateMechanicForm"));
    }

    @Test
    void initUpdateForm() throws Exception {
        when(mechanicService.findById(TEST_MECHANIC_ID)).thenReturn(returnMechanic);
        mockMvc.perform(get("/mechanics/{mechanicId}/edit", TEST_MECHANIC_ID))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("mechanic"))
                .andExpect(model().attribute("mechanic", hasProperty("lastName", is("Davies"))))
                .andExpect(model().attribute("mechanic", hasProperty("firstName", is("Jurgen"))))
                .andExpect(model().attribute("mechanic", hasProperty("address", is("57 Main St."))))
                .andExpect(model().attribute("mechanic", hasProperty("city", is("Falticeni"))))
                .andExpect(model().attribute("mechanic", hasProperty("telephone", is("4758352617"))))
                .andExpect(view().name("mechanics/createOrUpdateMechanicForm"));
    }

    @Test
    void processUpdateFormSuccess() throws Exception{
        mockMvc.perform(post("/mechanics/{mechanicId}/edit", TEST_MECHANIC_ID)
                        .param("firstName", "Jurgen")
                        .param("lastName", "Davies")
                        .param("address", "23 Stadium St.")
                        .param("city", "Suceava")
                        .param("telephone", "9878064637"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/mechanics/{mechanicId}"));
    }

    @Test
    void processUpdateFormHasErrors() throws Exception {
        mockMvc.perform(post("/mechanics/{mechanicId}/edit", TEST_MECHANIC_ID)
                        .param("firstName", "Jurgen")
                        .param("lastName", "Davies")
                        .param("city", "Suceava"))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors("mechanic"))
                .andExpect(model().attributeHasFieldErrors("mechanic", "address"))
                .andExpect(model().attributeHasFieldErrors("mechanic", "telephone"))
                .andExpect(view().name("mechanics/createOrUpdateMechanicForm"));
    }

    @Test
    void initDeleteForm() throws Exception{
        when(mechanicService.findById(TEST_MECHANIC_ID)).thenReturn(returnMechanic);
        mockMvc.perform(get("/mechanics/{mechanicId}/deletedSuccessfully", TEST_MECHANIC_ID))
                .andExpect(status().isOk())
                .andExpect(view().name("mechanics/successDeleteMechanic"));
    }

    @Test
    void deleteMechanic() throws Exception{
        mockMvc.perform(get("/mechanics/{mechanicId}/deleted", TEST_MECHANIC_ID))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/mechanics/index"));
    }

    @Test
    void listMechanics() throws Exception{
        mockMvc.perform(get("/mechanics"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("mechanics"))
                .andExpect(view().name("mechanics/index"));
    }

    @Test
    void showMechanic() throws Exception{
        when(mechanicService.findById(TEST_MECHANIC_ID)).thenReturn(returnMechanic);
        mockMvc.perform(get("/mechanics/{mechanicId}", TEST_MECHANIC_ID))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("mechanic"))
                .andExpect(view().name("mechanics/mechanicDetails"));
    }
}