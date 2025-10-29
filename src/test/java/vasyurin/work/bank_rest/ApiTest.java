//package vasyurin.work.bank_rest;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.DynamicPropertyRegistry;
//import org.springframework.test.context.DynamicPropertySource;
//import org.springframework.test.web.servlet.MockMvc;
//import vasyurin.work.bank_rest.controllers.UserController;
//
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(UserController.class)
//public class ApiTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @DynamicPropertySource
//    static void overrideProperties(DynamicPropertyRegistry registry) {
//        registry.add("spring.datasource.url", () -> "jdbc:postgresql://localhost:5432/Bank_DB");
//        registry.add("spring.datasource.username", () -> "postgres");
//        registry.add("spring.datasource.password", () -> "postgres");
//        registry.add("spring.liquibase.enabled", () -> "true"); // миграции будут применяться
//    }
//
//    @Test
//    void testCreateUser() throws Exception {
//        mockMvc.perform(post("/api/users")
//                        .with(httpBasic("user", "123"))
//                        .with(csrf())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"username\": \"testuser\", \"password\": \"123\"}"))
//                .andExpect(status().isCreated());
//    }
//
//    @Test
//    void testGetUserById() throws Exception {
//        mockMvc.perform(get("/api/users/1")
//                        .with(httpBasic("user", "123"))
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//}
