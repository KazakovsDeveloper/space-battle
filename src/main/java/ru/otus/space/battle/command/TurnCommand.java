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
        turn();
        return true;
    }

    @Override
    public void turn() {

        int angularVelocity = getAngularVelocity();

        Direction direction = getDirection();

        Direction next = next(angularVelocity, direction);

        setDirection(next);
    }

    @Override
    public Direction getDirection() {
        return gameSetting.getDirection();
    }

    @Override
    public void setDirection(Direction newV) {
        gameSetting.setDirection(newV);
    }

    @Override
    public int getAngularVelocity() {
        return gameSetting.getAngularVelocity();
    }

    private Direction next(int angularVelocity, Direction direction) {
        return new Direction(1);
    }

}
