package ru.otus.space.battle.command;

import ru.otus.space.battle.exception.CommandException;
import ru.otus.space.battle.model.Direction;
import ru.otus.space.battle.model.GameSetting;
import ru.otus.space.battle.movement.Turnable;

import static java.util.Objects.isNull;

public class TurnCommand implements Turnable, Command {

    private GameSetting gameSetting;

    public TurnCommand(GameSetting gameSetting) {
        this.gameSetting = gameSetting;
    }

    @Override
    public boolean execute() {
        turn();
        return true;
    }

    @Override
    public void setGameSettings(GameSetting newGameSetting) {
        this.gameSetting = newGameSetting;
    }

    @Override
    public void turn() {

        int angularVelocity = getAngularVelocity();

        Direction next = next(angularVelocity);

        setDirection(next);
    }

    @Override
    public Direction getDirection() {
        Direction direction = gameSetting.getDirection();
        if (isNull(direction)) {
            throw new CommandException("Направление не установлено");
        }
        return direction;
    }

    @Override
    public void setDirection(Direction newV) {
        gameSetting.setDirection(newV);
    }

    @Override
    public int getAngularVelocity() {
        return gameSetting.getAngularVelocity();
    }

    private Direction next(int angularVelocity) {

        int direction = getDirection().getDirection();

        if (0 == direction) {
            throw new CommandException("Направление не может быть нулевым");
        }

        int directionsNumber = gameSetting.getDirectionsNumber();

        if (0==angularVelocity || 0==directionsNumber) {
            throw new CommandException("Скорость и градус направления не должны быть 0");
        }

        int valueDirection = direction + angularVelocity / directionsNumber;

        return new Direction(valueDirection);
    }

}
