package com.costelmitrea.autoservice.controller;

import com.costelmitrea.autoservice.model.User;
import com.costelmitrea.autoservice.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class RegistrationControllerTest {

    @Mock
    UserService userService;

    @InjectMocks
    RegistrationController registrationController;

    MockMvc mockMvc;
    User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setFirstName("Default");
        user.setLastName("User");
        user.setUserName("username");
        user.setPassword("password");
        mockMvc = MockMvcBuilders.standaloneSetup(registrationController).build();
    }

    @Test
    void initRegistrationForm() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"))
                .andExpect(view().name("signupForm"));
    }

    @Test
    void processRegistrationFormSuccess() throws Exception {
        mockMvc.perform(post("/register")
                        .param("firstName", "Default")
                        .param("lastName", "User")
                        .param("userName", "username")
                        .param("password", "tLIMOczX8MgzQri7RSYXvuYgnFkJkjb2y21wRB07cN4GyaFziRUti"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/registeredSuccessfully"));

        verify(userService).save(any());
    }

    @Test
    void processCreationFormHasErrors() throws Exception{
        mockMvc.perform(post("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("signupForm"));
    }

    @Test
    void homeRegistration() throws Exception{
        mockMvc.perform(get("/registeredSuccessfully"))
                .andExpect(status().isOk())
                .andExpect(view().name("indexAfterRegistration"));
    }
}