package ru.otus.agent.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.otus.agent.IoC.IoC;
import ru.otus.agent.model.ListOfGamers;

@Service
public class AuthServiceImpl implements AuthService {

    private final RestTemplate restTemplate;

    public AuthServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void createToken(ListOfGamers gamers) {
        if (!gamers.getGamers().isEmpty()) {

            HttpEntity<ListOfGamers> tankBattleReq = new HttpEntity<>(gamers);

            try {
                ResponseEntity<String> queryGame =
                        restTemplate.postForEntity("localhost:8888/tankBattleId", tankBattleReq, String.class);

                String tankBattleId = queryGame.getBody();

                HttpEntity<String> tokenReq = new HttpEntity<>(tankBattleId);

                ResponseEntity<String> createToken =
                        restTemplate.postForEntity("localhost:8888/createToken", tokenReq, String.class);

                String jwtToken = createToken.getBody();

                //складываем токен в контейнер
                String managerLogin = gamers.getManagerLogin();
                IoC.addTokenToIoC(managerLogin, jwtToken);

            } catch (HttpClientErrorException exception) {
                throw new RuntimeException("Ошибка при обращении к серверу авторизации " + exception);
            }
        } else {
            throw new RuntimeException("Лист участников игры не может быть пустым");
        }

    }
}
