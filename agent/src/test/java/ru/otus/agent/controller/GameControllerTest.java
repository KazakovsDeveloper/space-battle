package ru.otus.agent.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.agent.IoC.IoC;
import ru.otus.agent.model.Settings;
import ru.otus.agent.model.TokenResponse;
import ru.otus.agent.service.CommandService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private Settings settings;

    @MockBean
    private CommandService commandService;

    @BeforeAll
    public void init() {
        objectMapper = new ObjectMapper();
        settings = new Settings();
        IoC.addTokenToIoC("183645", new TokenResponse("some token", "some id"));
    }

    @Test
    @DisplayName("успешный запрос")
    public void createCommandTestSuccess() throws Exception {
        this.mockMvc.perform(post("/createCommand")
                        .header("managerLogin", "183645")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(settings))
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("не пройдена валидация без хедера")
    public void createCommandTestReturn400() throws Exception {
        this.mockMvc.perform(post("/createCommand")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(settings))
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("не пройдена валидация без тела")
    public void createCommandTestReturn400WithoutBody() throws Exception {
        this.mockMvc.perform(post("/createCommand")
                        .header("managerLogin", "183645")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());
    }

}