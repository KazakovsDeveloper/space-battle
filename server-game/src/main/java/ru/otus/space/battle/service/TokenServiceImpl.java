package ru.otus.space.battle.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.space.battle.IoC.IoC;
import ru.otus.space.battle.model.GamerRole;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import static java.util.Objects.isNull;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${public.key}")
    private String PUBLIC_KEY;

    @Override
    public void tokenCheck(String token) {
        if (isNull(token)) {
            throw new RuntimeException("Заголовок авторизации отсутствует");
        } else {
            try {
                PUBLIC_KEY = PUBLIC_KEY
                        .replace("-----BEGIN PUBLIC KEY-----", "")
                        .replace("-----END PUBLIC KEY-----", "")
                        .replaceAll("\\s", "");

                byte[] publicKeyDER = Base64.getDecoder().decode(PUBLIC_KEY);

                X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyDER);
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                PublicKey publicKey = keyFactory.generatePublic(keySpec);

                Jws<Claims> claimsJws = Jwts.parserBuilder()
                        .setSigningKey(publicKey)
                        .build()
                        .parseClaimsJws(token);

                Claims claims = claimsJws.getBody();
                // логин запросившего токен
                String subject = claims.getSubject();
                GamerRole gamerRoleByLogin = IoC.getGamerRoleByLogin(subject);
                if (GamerRole.NOT_AUTH.equals(gamerRoleByLogin) || isNull(gamerRoleByLogin)) {
                    throw new RuntimeException("Настройки не могут быть применены, т.к. игрок не авторизован");
                }
            } catch (Exception e) {
                throw new RuntimeException("При декодировании токена произошла ошибка");
            }
        }
    }
}
