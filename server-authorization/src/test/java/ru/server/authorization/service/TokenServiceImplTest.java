package ru.server.authorization.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import ru.server.authorization.IoC.IoC;
import ru.server.authorization.model.Gamer;
import ru.server.authorization.model.ListOfGamers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Profile("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = TokenServiceImpl.class)
class TokenServiceImplTest {

    @Autowired
    private TokenServiceImpl tokenService;

    private String tankBattleId;

    private String gamerLogin;

    @BeforeAll
    public void init() {
        tankBattleId = "1984645283047";
        gamerLogin = "120397";
        ListOfGamers listOfGamers =
                new ListOfGamers(new Gamer("125748", "Anatoliy"), List.of(new Gamer(gamerLogin, "Petr")));
        IoC.addGamersToIoC(tankBattleId, listOfGamers);
    }

    @Test
    @DisplayName("генерация токена успешна")
    public void generateTokenTestShouldSuccess() {
        assertDoesNotThrow(() -> tokenService.generateToken(tankBattleId, gamerLogin));
    }

    @Test
    @DisplayName("исключение, т.к. такого игрока нет")
    public void generateTokenTestShouldThrowException() {
        assertThrows(RuntimeException.class, () -> tokenService.generateToken(tankBattleId, "123"));
    }

}