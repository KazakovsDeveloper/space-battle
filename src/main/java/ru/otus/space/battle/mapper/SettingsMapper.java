package ru.otus.space.battle.mapper;

import ru.otus.space.battle.model.Args;
import ru.otus.space.battle.model.Direction;
import ru.otus.space.battle.model.GameSetting;
import ru.otus.space.battle.model.Vector;

import static java.util.Objects.isNull;

public class SettingsMapper {

    public GameSetting mapSettings(Args args) {
        if (isNull(args)) {
            return null;
        }
        Vector position = new Vector(args.getxPosition(), args.getyPosition());
        Vector velocity = new Vector(args.getxVelocity(), args.getyVelocity());
        GameSetting gameSetting = new GameSetting(args.getFuel(), args.getConsumption(), position, velocity);
        gameSetting.setDirectionsNumber(args.getDirectionsNumber());
        Direction direction = new Direction(args.getDirection());
        gameSetting.setDirection(direction);
        gameSetting.setAngularVelocity(args.getAngularVelocity());
        return gameSetting;
    }

}
