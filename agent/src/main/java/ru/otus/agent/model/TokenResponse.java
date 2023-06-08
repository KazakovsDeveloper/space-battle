package ru.otus.agent.model;

public class TokenResponse {

    private String token;
    private String gameId;

    public TokenResponse(String token, String gameId) {
        this.token = token;
        this.gameId = gameId;
    }

    public TokenResponse() {
    }

    public String getToken() {
        return token;
    }

    public String getGameId() {
        return gameId;
    }
}
