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
import ru.otus.agent.model.Args;
import ru.otus.agent.model.Settings;
import ru.otus.agent.model.TokenResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = CommandServiceImpl.class)
class CommandServiceImplTest {

    @Autowired
    private CommandServiceImpl commandService;

    @MockBean
    private RestTemplate restTemplate;

    private Settings settings;

    private String managerLogin;

    private Settings settings2;

    private String managerLogin2;

    @BeforeEach
    public void init() {
        settings = new Settings(null, "turn", new Args(0.0, 5.0, 0, 0, 2, 5, 1, 7, 8));
        managerLogin = "login";
        TokenResponse tokenResponse = new TokenResponse("some token", "some gameId");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer some token");
        IoC.addTokenToIoC(managerLogin, tokenResponse);
        when(restTemplate.postForEntity("localhost:9999/settings", new HttpEntity<>(settings, httpHeaders),
                String.class)).thenReturn(ResponseEntity.ok("ok"));

        settings2 = new Settings(null, "", new Args());
        managerLogin2 = "login2";
        IoC.addTokenToIoC(managerLogin2, tokenResponse);
        when(restTemplate.postForEntity("localhost:9999/settings", new HttpEntity<>(settings2, httpHeaders),
                String.class)).thenThrow(RuntimeException.class);

    }

    @Test
    @DisplayName("успешное создание команды на сервере игры")
    public void createCommandTestSuccess() {
        assertDoesNotThrow(() -> commandService.createCommand(settings, managerLogin));

        assertEquals("some gameId", settings.getGameId());
    }

    @Test
    @DisplayName("сервер игры кидает исключение")
    public void createCommandTestThrowException() {
        assertThrows(RuntimeException.class, () -> commandService.createCommand(settings2, managerLogin2));
    }

}