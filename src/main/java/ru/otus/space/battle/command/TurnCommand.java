package ru.otus.space.battle.command;

import ru.otus.space.battle.model.Direction;
import ru.otus.space.battle.model.GameSetting;
import ru.otus.space.battle.movement.Turnable;

public class TurnCommand implements Turnable, Command {

    private final GameSetting gameSetting;

    public TurnCommand(GameSetting gameSetting) {
        this.gameSetting = gameSetting;
    }

    @Override
    public boolean execute() {
        return false;
    }

    @Override
    public void turn() {

    }

    @Override
    public Direction getDirection() {
        return null;
    }

    @Override
    public int getAngularVelocity() {
        return 0;
    }

    @Override
    public void setDirection(Direction newV) {
    }


}
