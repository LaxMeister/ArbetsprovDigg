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

// @SpringBootTest startar en fullständig Spring-kontext för tester.
// @AutoConfigureMockMvc konfigurerar MockMvc för att simulera HTTP-anrop.
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc; // MockMvc används för att simulera HTTP-förfrågningar i tester.

    @MockBean
    private UserService userService; // Mockar UserService för att isolera testerna från den verkliga databasen.

    private User testUser; // Variabel för att hålla en testanvändare.

    // @BeforeEach körs innan varje test, vilket säkerställer att testUser alltid är i ett förväntat tillstånd.
    @BeforeEach
    public void setup() {
        testUser = new User();
        testUser.setId(1L);
        testUser.setName("Test User");
        testUser.setEmail("test@example.com");
        testUser.setAddress("Test Address");
        testUser.setTelephone("1234567890");
    }

    // 1. Testar GET /digg/user (Hämta alla användare)
    @Test
    public void testGetAllUsers() throws Exception {
        List<User> users = Arrays.asList(testUser); // Skapar en lista med en användare.

        when(userService.users()).thenReturn(users); // Mockar service-anropet.

        mockMvc.perform(get("/digg/user")) // Utför GET-förfrågan.
                .andExpect(status().isOk()) // Förväntar sig statuskod 200.
                .andExpect(jsonPath("$[0].name").value("Test User")); // Kontrollerar att namnet i JSON-svaret är korrekt.

        verify(userService, times(1)).users(); // Verifierar att metoden anropades en gång.
    }

    // 2. Testar POST /digg/newuser (Skapa ny användare)
    @Test
    public void testCreateNewUser() throws Exception {
        when(userService.newUser(any(User.class))).thenReturn(testUser); // Mockar skapandet av användaren.

        mockMvc.perform(post("/digg/newuser")
                        .contentType(MediaType.APPLICATION_JSON) // Anger att innehållet är JSON.
                        .content(asJsonString(testUser))) // Konverterar testUser till JSON och skickar med förfrågan.
                .andExpect(status().isOk()) // Förväntar sig statuskod 200.
                .andExpect(jsonPath("$.name").value("Test User")); // Kontrollerar att namnet i svaret är korrekt.

        verify(userService, times(1)).newUser(any(User.class)); // Verifierar att metoden anropades en gång.
    }

    // 3. Testar PUT /digg/edituser (Uppdatera användare)
    @Test
    public void testUpdateUser() throws Exception {
        when(userService.updateUser(any(User.class))).thenReturn(testUser); // Mockar uppdateringen av användaren.

        mockMvc.perform(put("/digg/edituser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(testUser))) // Skickar JSON-objektet för uppdatering.
                .andExpect(status().isOk()) // Förväntar sig statuskod 200.
                .andExpect(jsonPath("$.name").value("Test User")); // Kontrollerar att namnet i svaret är korrekt.

        verify(userService, times(1)).updateUser(any(User.class)); // Verifierar att metoden anropades en gång.
    }

    // 4. Testar DELETE /digg/deleteuser (Radera användare)
    @Test
    public void testDeleteUser() throws Exception {
        when(userService.deleteUser(any(User.class))).thenReturn("Test User was deleted"); // Mockar raderingen av användaren.

        mockMvc.perform(delete("/digg/deleteuser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(testUser))) // Skickar JSON-objektet för radering.
                .andExpect(status().isNoContent()); // Förväntar sig statuskod 204 (No Content).

        verify(userService, times(1)).deleteUser(any(User.class)); // Verifierar att metoden anropades en gång.
    }

    // Hjälpmetod för att konvertera objekt till JSON-sträng
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj); // Använder ObjectMapper för att konvertera objekt till JSON.
        } catch (Exception e) {
            throw new RuntimeException(e); // Hanterar eventuella fel vid konvertering.
        }
    }
}
