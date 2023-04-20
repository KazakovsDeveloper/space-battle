package ru.otus.space.battle.command;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import ru.otus.space.battle.model.GameSetting;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BurnFuelCommandTest {

    private BurnFuelCommand burnFuelCommand;

    @BeforeAll
    public void init() {
        GameSetting gameSetting = new GameSetting(15.0, 5.0);
        burnFuelCommand = new BurnFuelCommand(gameSetting);
    }

    @Test
    @DisplayName("количество топлива уменьшилось")
    public void executeTestShouldReturnTrue() {
        boolean executeTrue = burnFuelCommand.execute();

        assertTrue(executeTrue);
    }
}