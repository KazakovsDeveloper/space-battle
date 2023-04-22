package ru.otus.space.battle.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.space.battle.model.Direction;
import ru.otus.space.battle.model.GameSetting;
import ru.otus.space.battle.model.Vector;

import static org.junit.jupiter.api.Assertions.*;


class TurnCommandTest {

    private TurnCommand turnCommand;

    @BeforeEach
    public void init() {
        Vector position = new Vector(1, 5);
        Vector velocity = new Vector(3, 6);
        Direction direction = new Direction(3);

        GameSetting gameSetting = new GameSetting(10.0, 2.0, position, velocity);
        gameSetting.setDirection(direction);
        gameSetting.setDirectionsNumber(2);

        turnCommand = new TurnCommand(gameSetting);
    }

    @Test
    @DisplayName("объект поворачивается")
    public void executeTestShouldSuccess() {

        boolean execute = turnCommand.execute();
        assertTrue(execute);

    }

}