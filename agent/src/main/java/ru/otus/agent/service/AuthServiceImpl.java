package ru.otus.agent.service;

import org.springframework.beans.factory.annotation.Value;
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

    @Value("${agent.auth.url}")
    private String authUrl;

    @Value("${agent.token.url}")
    private String tokenUrl;

    @Value("${agent.token.header}")
    private String tokenHeader;

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

    /**
     * На сервер авторизации уходит запрос о том, что организуется танковый бой и присылается список его участников.
     * Сервер в ответ возвращает id танкового боя.
     */
    private String getTankBattleId(ListOfGamers gamers) {
        HttpEntity<ListOfGamers> tankBattleReq = new HttpEntity<>(gamers);
        ResponseEntity<String> queryGame =
                restTemplate.postForEntity(authUrl, tankBattleReq, String.class);

        return queryGame.getBody();
    }

    /**
     * Аутентифицированный пользователь посылает запрос на выдачу jwt токена, который авторизует право этого пользователя на участие в танковом бое.
     * Для этого он должен указать в запросе id танкового боя.
     */
    private TokenResponse getTokenResponse(String tankBattleId, String managerLogin) {

        HttpHeaders headers = new HttpHeaders();
        headers.add(tokenHeader, managerLogin);
        HttpEntity<String> tokenReq = new HttpEntity<>(tankBattleId, headers);

        ResponseEntity<TokenResponse> createToken =
                restTemplate.postForEntity(tokenUrl, tokenReq, TokenResponse.class);

        return createToken.getBody();
    }

}
