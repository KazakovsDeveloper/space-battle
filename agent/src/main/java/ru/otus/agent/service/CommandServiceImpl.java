package ru.otus.agent.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.otus.agent.IoC.IoC;
import ru.otus.agent.model.Settings;
import ru.otus.agent.model.TokenResponse;

@Service
public class CommandServiceImpl implements CommandService {

    private final RestTemplate restTemplate;

    public CommandServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void createCommand(Settings settings, String managerLogin) {
        try {
            // достаем нужный токен из контейнера
            TokenResponse tokenResponse = IoC.getToken(managerLogin);
            String token = tokenResponse.getToken();
            String gameId = tokenResponse.getGameId();
            settings.setGameId(gameId);

            this.sendCommandToServerGame(token, settings);
        } catch (HttpClientErrorException exception) {
            throw new RuntimeException("Ошибка при обращении к игровому серверу " + exception);
        }
    }

    private void sendCommandToServerGame(String token, Settings settings) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + token);
        HttpEntity<Settings> commandRequest = new HttpEntity<>(settings, httpHeaders);
        restTemplate.postForEntity("localhost:9090/settings", commandRequest, String.class);
    }

}
