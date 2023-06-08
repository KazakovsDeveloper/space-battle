package ru.server.authorization.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.server.authorization.IoC.IoC;
import ru.server.authorization.model.ListOfGamers;
import ru.server.authorization.model.TokenResponse;
import ru.server.authorization.util.Utils;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${private.key}")
    private String PRIVATE_KEY;

    @Override
    public TokenResponse generateToken(String tankBattleId, String gamerLogin) {

        ListOfGamers gamersByTankBattleId = IoC.getGamersByTankBattleId(tankBattleId);

        if (Utils.checkLogin(gamersByTankBattleId, gamerLogin)) {

            try {
                PRIVATE_KEY = PRIVATE_KEY
                        .replace("-----BEGIN PRIVATE KEY-----", "")
                        .replace("-----END PRIVATE KEY-----", "")
                        .replaceAll("\\s", "");

                byte[] privateKeyDER = Base64.getDecoder().decode(PRIVATE_KEY);

                PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyDER);
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

                Date expirationDate = new Date(System.currentTimeMillis() + 86400000); // 24 часа

                String gameId = Utils.generateRandomId();

                String token = Jwts.builder()
                        .setSubject(gamerLogin)
                        .setExpiration(expirationDate)
                        .claim("gameId", gameId)
                        .signWith(privateKey, SignatureAlgorithm.RS256)
                        .compact();
                return new TokenResponse(token, gameId);

            } catch (Exception exception) {
                throw new RuntimeException("Не удалось сгенерировать токен " + exception);
            }
        }
        return new TokenResponse();
    }
}
