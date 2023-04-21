package ru.otus.space.battle.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.space.battle.model.GameSetting;
import ru.otus.space.battle.model.Vector;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MoveCommandTest {

    private MoveCommand moveCommand;

    @BeforeEach
    public void init() {
        Vector position = new Vector(12, 5);
        Vector velocity = new Vector(-7, 3);

        GameSetting gameSetting = new GameSetting(0.0, 0.0, position, velocity);

        moveCommand = new MoveCommand(gameSetting);
    }

    /**
     * Для объекта, находящегося в точке (12, 5) и движущегося со скоростью (-7, 3)
     * движение меняет положение объекта на (5, 8)
     */
    @Test
    public void executeTestShouldMoveTheObject() {
        boolean executeMove = moveCommand.execute();

        assertTrue(executeMove);
    }

}