package ru.otus.space.battle.command;

import ru.otus.space.battle.model.GameSetting;

/**
 * BurnFuelCommand уменьшает количество топлива на скорость расхода топлива.
 */
public class BurnFuelCommand {

    private final GameSetting gameSetting;

    public BurnFuelCommand(GameSetting gameSetting) {
        this.gameSetting = gameSetting;
    }

    public boolean execute() {
        return false;
    }


}
