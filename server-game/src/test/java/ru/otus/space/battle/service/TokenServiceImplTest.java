package ru.otus.space.battle.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import ru.otus.space.battle.IoC.IoC;
import ru.otus.space.battle.model.GamerRole;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Profile("test")
@SpringBootTest(classes = TokenServiceImpl.class)
class TokenServiceImplTest {

    @Autowired
    private TokenServiceImpl tokenService;

    private String token;

    private String badToken;

    private String tokenWithNotAuthGamer;

    @BeforeEach
    public void init() {
        IoC.addRoleToGamersIoC("120397", GamerRole.MANAGER);
        IoC.addRoleToGamersIoC("125748", GamerRole.NOT_AUTH);
        token =
                "eyJhbGciOiJSUzI1NiJ9" +
                        ".eyJzdWIiOiIxMjAzOTciLCJleHAiOjE2ODYzNzgyNjQsImdhbWVJZCI6ImE0YTJlOTBiLTVhYWEtNDBmNC1iNGU3LWY2MjZiZjMwZTFjNSJ9" +
                        ".hXU23CVcFU-u2S0KAvJLq4gI404BqatmGKepzqWF8XysmcBMJpB1D2I6EQUp0zaRDd437oCUoGUveYAfruf7oBaGi-1q3bJJNBiYSS1wkshtsA0NaGTyOtGEsN8xb21ugDgaiOnDswVGkmfhWr1L8SCLS1MfMT1Z_" +
                        "-6Y-JM_Xa9VtDEBnGqoPbwMOx1s62SX-doGzMs0WaNvPopLz9sl2dYhEFjefeYwBNInUjaz-JJlY0Hb90qaws_UGylZ0YagjyBi6Ca0oNPe4dwXZWjzRjUANPocL6y62S" +
                        "-LpSbnJc9s2bR3AEdNT2dwViq42q95NrvHMcisxHafD7O772KP4A";

        badToken = "bad token";

        tokenWithNotAuthGamer =
                "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIxMjU3NDgiLCJleHAiOjE2ODYzNzk5OTIsImdhbWVJZCI6ImI1NGQ3YTc0LWY5M2EtNGM3ZC1hZDcxLTQ5Y2IzYWUwOTNjNyJ9" +
                        ".j6BKLe61lMJaAkZr9Xaj0R8PJP1R8nckL-l4l7s_FsyWs0lvdSkyDApexBeEmUHiptD4MHBQdKRbdFyEQOyk0-h2Jxqz85zwcSnIdkJ_fl8snnb3b-bjTz_qsKk" +
                        "-eqsSwX0haj3rSA2rMaf_4mtcwanM9qyBLgyUXcO7WFuixOMzYb79fy9cmrJNUiWmRQn54eICCWedrLZpZaUY0IoNYnPF9T8ErF60q0GXLjpBq6p2jljRkTlvU5bVooH1ehIR8v9" +
                        "-FW-i-UxlBZKHz01svtd1Wm8Ccbageed50OCr-ov6SZEgsXPgb7s2CGIove_w_H4GeGbhnIgnX3q_BUFjnw";
    }


    @Test
    @DisplayName("успешно декодирован токен")
    public void tokenCheckTestSuccess() {
        assertDoesNotThrow(() -> tokenService.tokenCheck(token));
    }

    @Test
    @DisplayName("ошибка при декодировании")
    public void tokenCheckTestThrowException() {
        assertThrows(RuntimeException.class, () -> tokenService.tokenCheck(badToken));
    }

    @Test
    @DisplayName("ошибка при отсутствии прав")
    public void tokenCheckTestThrowExceptionIfGamerNotAuth() {
        assertThrows(RuntimeException.class, () -> tokenService.tokenCheck(tokenWithNotAuthGamer));
    }

}