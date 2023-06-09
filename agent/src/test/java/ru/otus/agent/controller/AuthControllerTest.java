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
import ru.otus.agent.model.Gamer;
import ru.otus.agent.model.ListOfGamers;
import ru.otus.agent.service.AuthService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private ListOfGamers listOfGamers;

    @MockBean
    private AuthService authService;

    @BeforeAll
    public void init() {
        objectMapper = new ObjectMapper();
        listOfGamers = new ListOfGamers(new Gamer("1947559", "Den"), List.of(new Gamer("297565", "Stas")));
        doNothing().when(authService).createToken(any());
    }

    @Test
    @DisplayName("успешный запрос")
    public void createTokenTestSuccess() throws Exception {
        this.mockMvc.perform(post("/createBattle")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(listOfGamers))
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("запрос не прошел валидацию")
    public void createTokenTestReturn400() throws Exception {
        this.mockMvc.perform(post("/createBattle")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new ListOfGamers()))
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());
    }

}