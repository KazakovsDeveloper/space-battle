package ru.otus.agent.IoC;

import java.util.HashMap;
import java.util.Map;

public class IoC {

    public static Map<String, String> tokens = new HashMap<>();

    public static void addTokenToIoC(String managerLogin, String token) {
        tokens.put(managerLogin, token);
    }

    public static String getToken(String managerLogin) {
        return tokens.get(managerLogin);
    }

}
