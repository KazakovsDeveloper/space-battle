package ru.server.authorization.service;

import ru.server.authorization.model.TokenResponse;

public interface TokenService {

    TokenResponse generateToken(String tankBattleId, String gamerLogin);

}
