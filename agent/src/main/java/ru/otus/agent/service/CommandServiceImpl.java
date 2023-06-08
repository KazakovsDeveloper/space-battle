package ru.otus.agent.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.otus.agent.IoC.IoC;
import ru.otus.agent.model.Settings;

@Service
public class CommandServiceImpl implements CommandService {

    private final RestTemplate restTemplate;

    public CommandServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void createCommand(Settings settings, String managerLogin) {

        // достаем нужный токен из контейнера
        String token = IoC.getToken(managerLogin);

        // распарсить токен, добавить gameId из токена в settings

        // отправить команду с токеном на сервер игры
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + token);
        HttpEntity<Settings> commandRequest = new HttpEntity<>(settings, httpHeaders);

        try {
            restTemplate.postForEntity("localhost:9090/settings", commandRequest, String.class);
        } catch (HttpClientErrorException exception) {
            throw new RuntimeException("Ошибка при обращении к игровому серверу " + exception);
        }
    }
}
