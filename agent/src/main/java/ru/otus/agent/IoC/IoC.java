package ru.otus.agent.IoC;

import ru.otus.agent.model.TokenResponse;

import java.util.HashMap;
import java.util.Map;

public class IoC {

    public static Map<String, TokenResponse> tokens = new HashMap<>();

    public static void addTokenToIoC(String managerLogin, TokenResponse token) {
        tokens.put(managerLogin, token);
    }

    public static TokenResponse getToken(String managerLogin) {
        return tokens.get(managerLogin);
    }

}
