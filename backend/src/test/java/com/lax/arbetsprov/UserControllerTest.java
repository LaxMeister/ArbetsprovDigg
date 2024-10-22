package com.lax.arbetsprov;

import com.lax.arbetsprov.Beans.User;
import com.lax.arbetsprov.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private User testUser;

    @BeforeEach
    public void setup() {
        testUser = new User();
        testUser.setId(1L);
        testUser.setName("Test User");
        testUser.setEmail("test@example.com");
        testUser.setAddress("Test Address");
        testUser.setTelephone("1234567890");
    }

    // 1. Testa GET /digg/user (Hämta alla användare)
    @Test
    public void testGetAllUsers() throws Exception {
        List<User> users = Arrays.asList(testUser);

        when(userService.users()).thenReturn(users);

        mockMvc.perform(get("/digg/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test User"));

        verify(userService, times(1)).users();
    }

    // 2. Testa POST /digg/newuser (Skapa ny användare)
    @Test
    public void testCreateNewUser() throws Exception {
        when(userService.newUser(any(User.class))).thenReturn(testUser);

        mockMvc.perform(post("/digg/newuser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(testUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test User"));

        verify(userService, times(1)).newUser(any(User.class));
    }

    // 3. Testa PUT /digg/edituser (Uppdatera användare)
    @Test
    public void testUpdateUser() throws Exception {
        when(userService.updateUser(any(User.class))).thenReturn(testUser);

        mockMvc.perform(put("/digg/edituser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(testUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test User"));

        verify(userService, times(1)).updateUser(any(User.class));
    }

    // 4. Testa DELETE /digg/deleteuser (Radera användare)
    @Test
    public void testDeleteUser() throws Exception {
        when(userService.deleteUser(any(User.class))).thenReturn("Test User was deleted");

        mockMvc.perform(delete("/digg/deleteuser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(testUser)))
                .andExpect(status().isNoContent());

        verify(userService, times(1)).deleteUser(any(User.class));
    }

    // Hjälpmetod för att konvertera objekt till JSON-sträng
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
