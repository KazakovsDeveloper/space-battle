package ru.server.authorization.controller;

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
import ru.server.authorization.model.Gamer;
import ru.server.authorization.model.ListOfGamers;
import ru.server.authorization.service.TankBattleService;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @MockBean
    private TankBattleService tankBattleService;

    private ListOfGamers listOfGamers;

    @BeforeAll
    public void init() {
        objectMapper = new ObjectMapper();
        listOfGamers = new ListOfGamers(new Gamer("1947559", "Den"), List.of(new Gamer("297565", "Stas")));
        when(tankBattleService.createTankBattle(listOfGamers)).thenReturn("397646402945");
    }

    @Test
    @DisplayName("успешная отработка запроса")
    public void createTankBattleIdTestSuccess() throws Exception {

        this.mockMvc.perform(post("/tankBattleId")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(listOfGamers))
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("запрос не проходит валидацию, если нет организатора игры")
    public void createTankBattleIdTestReturn400() throws Exception {

        this.mockMvc.perform(post("/tankBattleId")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new ListOfGamers()))
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("запрос не проходит валидацию, если не передано тело запроса")
    public void createTankBattleIdTestReturn400WhenRequestBodyIsNull() throws Exception {

        this.mockMvc.perform(post("/tankBattleId")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());
    }

}