package ru.otus.space.battle.command;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import ru.otus.space.battle.exception.CommandException;
import ru.otus.space.battle.model.GameSetting;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CheckFuelCommandTest {

    private CheckFuelCommand checkFuelCommandWithSettings;
    private CheckFuelCommand checkFuelCommandWithoutSettings;
    private CheckFuelCommand checkFuelCommandWithGameSettingIsNull;


    @BeforeAll
    public void init() {
        GameSetting gameSetting = new GameSetting(10.0);
        GameSetting gameSetting2 = new GameSetting(0.0);

        checkFuelCommandWithSettings = new CheckFuelCommand(gameSetting);
        checkFuelCommandWithoutSettings = new CheckFuelCommand(gameSetting2);
        checkFuelCommandWithGameSettingIsNull = new CheckFuelCommand(null);

    }

    @Test
    @DisplayName("проверка, что топлива достаточно для движения")
    public void executeTestFuelBiggerThan0() {
        boolean fuelIsBiggerThan0 = checkFuelCommandWithSettings.execute();

        assertTrue(fuelIsBiggerThan0);
    }

    @Test
    @DisplayName("проверка, что топлива недостаточно для движения")
    public void executeTestThrowsCommandException() {

        assertThrows(CommandException.class, () -> checkFuelCommandWithoutSettings.execute());
    }

    @Test
    @DisplayName("проверка, что исключение выброшено при null GameSetting")
    public void executeTestThrowsCommandExceptionIfGameSettingIsNull() {

        assertThrows(CommandException.class, () -> checkFuelCommandWithGameSettingIsNull.execute());
    }

}