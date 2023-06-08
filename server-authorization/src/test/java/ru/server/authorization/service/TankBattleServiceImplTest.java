package ru.server.authorization.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import ru.server.authorization.IoC.IoC;
import ru.server.authorization.model.Gamer;
import ru.server.authorization.model.ListOfGamers;

import java.util.List;

import static java.util.Objects.nonNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Profile("test")
@SpringBootTest(classes = TankBattleServiceImpl.class)
class TankBattleServiceImplTest {

    @Autowired
    private TankBattleServiceImpl tankBattleService;

    @Test
    @DisplayName("создает id танкового боя и добавляет его в контейнер")
    public void createTankBattleSuccess() {
        ListOfGamers listOfGamers = new ListOfGamers(new Gamer("1093754", "Petr"), List.of(new Gamer("123", "nik")));

        String tankBattleId = tankBattleService.createTankBattle(listOfGamers);

        ListOfGamers gamersByTankBattleId = IoC.getGamersByTankBattleId(tankBattleId);

        assertTrue(nonNull(gamersByTankBattleId));
    }

}