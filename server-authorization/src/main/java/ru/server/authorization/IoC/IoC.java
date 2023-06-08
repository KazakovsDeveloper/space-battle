package ru.server.authorization.IoC;

import ru.server.authorization.model.ListOfGamers;

import java.util.HashMap;
import java.util.Map;

public class IoC {

    public static Map<String, ListOfGamers> tankBattles = new HashMap<>();

    public static void addGamersToIoC(String tankBattleId, ListOfGamers gamers) {
        tankBattles.put(tankBattleId, gamers);
    }

    public static ListOfGamers getGamersByTankBattleId(String tankBattleId) {
        return tankBattles.get(tankBattleId);
    }

}
