package com.example.userservice.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.example.userservice.exception.EmailAlreadyRegisteredException;
import com.example.userservice.exception.GlobalExceptionHandler;
import com.example.userservice.model.User;
import com.example.userservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(new GlobalExceptionHandler()) // Agrega el asesor aquí
                .build();

        user = new User();
        user.setEmail("jose@example.org");
        user.setPassword("Fdfdf55w*s");
    }

    @Test
    void createUser_shouldReturnUserResponse() throws Exception {
        when(userService.registerUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"email\": \"jose@example.org\", \"password\": \"Fdfdf55w*s\" }"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value("jose@example.org"));
    }

    @Test
    void createUser_emailAlreadyExists_shouldReturnBadRequest() throws Exception {
        when(userService.registerUser(any(User.class))).thenThrow(new EmailAlreadyRegisteredException("El correo ya está registrado"));

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"email\": \"jose@example.org\", \"password\": \"Fdfdf55w*s\" }"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.mensaje").value("El correo ya está registrado"));
    }
}
