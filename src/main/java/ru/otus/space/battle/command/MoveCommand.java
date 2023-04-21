package ru.otus.space.battle.command;

import ru.otus.space.battle.exception.CommandException;
import ru.otus.space.battle.model.GameSetting;
import ru.otus.space.battle.model.Vector;
import ru.otus.space.battle.movement.Movable;

import static java.util.Objects.isNull;

public class MoveCommand implements Command, Movable {

    private final GameSetting gameSetting;

    public MoveCommand(GameSetting gameSetting) {
        this.gameSetting = gameSetting;
    }

    @Override
    public boolean execute() {
        move();
        return true;
    }

    @Override
    public void move() {
        Vector position = getPosition();
        Vector velocity = getVelocity();

        if (isNull(position) || isNull(velocity)) {
            throw new CommandException("Не указаны координаты позиции");
        }

        Vector newPosition = getVectorSum(position, velocity);

        gameSetting.setPosition(newPosition);
    }

    private Vector getVectorSum(Vector position, Vector velocity) {
        int xPosition = position.getX();
        int xVelocity = velocity.getX();

        int yPosition = position.getY();
        int yVelocity = velocity.getY();

        int xNewPosition = xPosition + xVelocity;
        int yNewPosition = yPosition + yVelocity;

        return new Vector(xNewPosition, yNewPosition);
    }

    @Override
    public Vector getPosition() {
        return gameSetting.getPosition();
    }

    @Override
    public Vector getVelocity() {
        return gameSetting.getVelocity();
    }
}
