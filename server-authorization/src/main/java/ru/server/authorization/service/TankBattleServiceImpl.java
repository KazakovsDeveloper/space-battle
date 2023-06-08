package ru.server.authorization.service;

import org.springframework.stereotype.Service;
import ru.server.authorization.IoC.IoC;
import ru.server.authorization.util.Utils;
import ru.server.authorization.model.ListOfGamers;

@Service
public class TankBattleServiceImpl implements TankBattleService {

    @Override
    public String createTankBattle(ListOfGamers gamers) {
        String tankBattleId = Utils.generateRandomId();
        IoC.addGamersToIoC(tankBattleId, gamers);
        return tankBattleId;
    }
}
