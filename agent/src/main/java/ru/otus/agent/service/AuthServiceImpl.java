package ru.otus.agent.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import ru.otus.agent.IoC.IoC;
import ru.otus.agent.model.ListOfGamers;
import ru.otus.agent.model.TokenResponse;

@Service
public class AuthServiceImpl implements AuthService {

    private final RestTemplate restTemplate;
    public AuthServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void createToken(ListOfGamers gamers) {
        if (!gamers.getGamers().isEmpty()) {
            try {

                String tankBattleId = this.getTankBattleId(gamers);

                String managerLogin = gamers.getManagerOfGame().getLogin();

                TokenResponse tokenResponse = this.getTokenResponse(tankBattleId, managerLogin);

                //складываем токен в контейнер
                IoC.addTokenToIoC(managerLogin, tokenResponse);

            } catch (HttpClientErrorException exception) {
                throw new RuntimeException("Ошибка при обращении к серверу авторизации " + exception);
            }
        } else {
            throw new RuntimeException("Лист участников игры не может быть пустым");
        }
    }

    private String getTankBattleId(ListOfGamers gamers) {
        HttpEntity<ListOfGamers> tankBattleReq = new HttpEntity<>(gamers);
        ResponseEntity<String> queryGame =
                restTemplate.postForEntity("localhost:8888/tankBattleId", tankBattleReq, String.class);

        return queryGame.getBody();
    }

    private TokenResponse getTokenResponse(String tankBattleId, String managerLogin) {

        HttpHeaders headers = new HttpHeaders();
        headers.add("gamerLogin", managerLogin);
        HttpEntity<String> tokenReq = new HttpEntity<>(tankBattleId, headers);

        ResponseEntity<TokenResponse> createToken =
                restTemplate.postForEntity("localhost:8888/createToken", tokenReq, TokenResponse.class);

        return createToken.getBody();
    }

}
