package ru.server.authorization.service;

public interface TokenService {

    String generateToken(String tankBattleId, String gamerLogin);

}
