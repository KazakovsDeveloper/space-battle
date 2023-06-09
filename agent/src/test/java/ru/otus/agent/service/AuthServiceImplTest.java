package ru.otus.agent.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.otus.agent.IoC.IoC;
import ru.otus.agent.model.Gamer;
import ru.otus.agent.model.ListOfGamers;
import ru.otus.agent.model.TokenResponse;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = AuthServiceImpl.class)
class AuthServiceImplTest {

    @Autowired
    private AuthServiceImpl authService;

    @MockBean
    private RestTemplate restTemplate;

    private ListOfGamers listOfGamers;

    private ListOfGamers listOfGamers2;

    @BeforeEach
    public void init() {
        listOfGamers = new ListOfGamers(new Gamer("1984634", "Alex"),
                List.of(new Gamer("397526", "Nina")));

        when(restTemplate.postForEntity("localhost:8888/tankBattleId", new HttpEntity<>(listOfGamers),
                String.class)).thenReturn(
                ResponseEntity.ok("83925YD97"));

        HttpHeaders headers = new HttpHeaders();
        headers.add("gamerLogin", "1984634");

        when(restTemplate.postForEntity("localhost:8888/createToken", new HttpEntity<>("83925YD97", headers),
                TokenResponse.class)).thenReturn(
                ResponseEntity.ok(new TokenResponse("some token", "some game id")));

        listOfGamers2 = new ListOfGamers(new Gamer("840275", "Alina"), List.of(new Gamer("2056245", "Oleg")));

        when(restTemplate.postForEntity("localhost:8888/tankBattleId", new HttpEntity<>(listOfGamers2),
                String.class)).thenThrow(RuntimeException.class);
    }

    @Test
    @DisplayName("успешное создание токена")
    public void createTokenTestSuccess() {

        assertDoesNotThrow(() -> authService.createToken(listOfGamers));

        TokenResponse token = IoC.getToken("1984634");

        assertEquals("some token", token.getToken());
    }

    @Test
    @DisplayName("сервер авторизации кидает искючение")
    public void createTokenTestThrowException() {
        assertThrows(RuntimeException.class, () -> authService.createToken(listOfGamers2));
    }


}