package ru.server.authorization.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TokenControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeAll
    public void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("успешный запрос на генерацию токена")
    public void generateTokenTestSuccess() throws Exception {
        this.mockMvc.perform(post("/createToken")
                        .header("gamerLogin", "198642")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString("308462525"))
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("валидация провалена, если заголовок не передан")
    public void generateTokenTestReturn400() throws Exception {
        this.mockMvc.perform(post("/createToken")
                        .header("gamerLogin", "198642")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());
    }

}