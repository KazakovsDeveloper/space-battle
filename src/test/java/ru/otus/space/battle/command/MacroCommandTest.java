package ru.otus.space.battle.command;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import ru.otus.space.battle.exception.CommandException;
import ru.otus.space.battle.model.GameSetting;
import ru.otus.space.battle.model.Vector;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MacroCommandTest {

    private MacroCommand macroCommandWithCorrectSettings;
    private MacroCommand macroCommandWithNotEnoughFuel;
    private MacroCommand macroCommandWithNegativeConsumption;

    @BeforeAll
    public void init() {
        Vector position = new Vector(1, 2);
        Vector velocity = new Vector(4, 5);

        GameSetting correctGameSetting = new GameSetting(10.0, 2.0, position, velocity);
        CheckFuelCommand checkFuelCommand = new CheckFuelCommand(correctGameSetting);
        MoveCommand moveCommand = new MoveCommand(correctGameSetting);
        BurnFuelCommand burnFuelCommand = new BurnFuelCommand(correctGameSetting);

        GameSetting notEnoughFuel = new GameSetting(1.0, 2.0);
        CheckFuelCommand checkFuelCommand2 = new CheckFuelCommand(notEnoughFuel);

        GameSetting negativeConsumption = new GameSetting(15.0, -5.0);
        CheckFuelCommand checkFuelCommand3 = new CheckFuelCommand(negativeConsumption);

        Command[] commands = {checkFuelCommand, moveCommand, burnFuelCommand};
        Command[] commands2 = {checkFuelCommand2, moveCommand, burnFuelCommand};
        Command[] commands3 = {checkFuelCommand3, moveCommand, burnFuelCommand};

        macroCommandWithCorrectSettings = new MacroCommand(commands);
        macroCommandWithNotEnoughFuel = new MacroCommand(commands2);
        macroCommandWithNegativeConsumption = new MacroCommand(commands3);
    }

    @Test
    @DisplayName("успешный запуск команд")
    public void executeTestDoesNotThrowException() {

        assertDoesNotThrow(() -> macroCommandWithCorrectSettings.execute());

    }

    @Test
    @DisplayName("недостаточно топлива")
    public void executeTestThrowsExceptionWhenNotEnoughFuel() {

        assertThrows(CommandException.class, () -> macroCommandWithNotEnoughFuel.execute());

    }

    @Test
    @DisplayName("отрицательное значение расхода")
    public void executeTestThrowsExceptionWhenConsumptionIsNegative() {

        assertThrows(CommandException.class, () -> macroCommandWithNegativeConsumption.execute());

    }

}