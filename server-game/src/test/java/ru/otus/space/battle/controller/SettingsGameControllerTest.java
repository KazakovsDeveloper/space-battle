package ru.otus.space.battle.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.space.battle.model.Args;
import ru.otus.space.battle.model.Settings;
import ru.otus.space.battle.service.GameService;
import ru.otus.space.battle.service.TokenService;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SettingsGameControllerTest {

    @MockBean
    private GameService gameService;

    @MockBean
    private TokenService tokenService;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private String token;

    private Args args;

    private Settings settings;

    @BeforeEach
    public void init() {
        objectMapper = new ObjectMapper();
        token = "someToken";
        doNothing().when(tokenService).tokenCheck(token);
        args = new Args(0.0, 0.0, 1, 3, 5, 6, 2, 10, 4);
        settings = new Settings("id", "turn", args);
    }

    @Test
    @DisplayName("успешный запрос")
    public void setGameSettingsTest() throws Exception {

        System.out.println(objectMapper.writeValueAsString(settings));

        this.mockMvc.perform(post("/settings")
                        .header("Authorization", token)
                        .header("Content-Type", "application/json")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(settings))
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("запрос не прошел валидацию")
    public void setGameSettingsTest400() throws Exception {
        this.mockMvc.perform(post("/settings")
                        .header("Authorization", token)
                        .header("Content-Type", "application/json")
                )
                .andExpect(status().isBadRequest());
    }

}