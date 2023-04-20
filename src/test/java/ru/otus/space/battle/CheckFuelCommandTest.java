package ru.otus.space.battle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckFuelCommandTest {

    private CheckFuelCommand checkFuelCommand;

    @BeforeEach
    public void init() {
        GameSetting gameSetting = new GameSetting(10.0);
        checkFuelCommand = new CheckFuelCommand(gameSetting);
    }

    @Test
    @DisplayName("проверка, что топлива достаточно для движения")
    public void executeTestFuelBiggerThan0() {
        boolean fuelIsBiggerThan0 = checkFuelCommand.execute();

        assertTrue(fuelIsBiggerThan0);
    }


}