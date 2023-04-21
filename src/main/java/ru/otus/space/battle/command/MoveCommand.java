package ru.otus.space.battle.command;

import ru.otus.space.battle.model.GameSetting;
import ru.otus.space.battle.model.Vector;
import ru.otus.space.battle.movement.Movable;

public class MoveCommand implements Command, Movable {

    private final GameSetting gameSetting;

    public MoveCommand(GameSetting gameSetting) {
        this.gameSetting = gameSetting;
    }

    @Override
    public boolean execute() {
        return false;
    }

    @Override
    public void move() {

    }

    @Override
    public Vector getPosition() {
        return null;
    }

    @Override
    public Vector getVelocity() {
        return null;
    }
}
