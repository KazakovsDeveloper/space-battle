package ru.otus.space.battle.command;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.space.battle.IoC.IoC;
import ru.otus.space.battle.model.Args;
import ru.otus.space.battle.model.Game;
import ru.otus.space.battle.model.GameSetting;
import ru.otus.space.battle.model.Settings;

import java.lang.reflect.Field;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InterpretCommandTest {

    private Args args = new Args(0.0, 0.0, 0, 0, 0, 0, 5, 1, 3);
    private Settings settings = new Settings("id", "turn", args);
    private InterpretCommand interpretCommand = new InterpretCommand(settings);

    @Test
    @DisplayName("проверяем, что команда с настройками помещена в очередь на выполнение")
    public void executeTest() throws NoSuchFieldException, IllegalAccessException {
        ConcurrentLinkedQueue<Command> queue = new ConcurrentLinkedQueue<>();
        MacroCommand macroCommand = new MacroCommand(queue);
        Game game = new Game("id", macroCommand);
        IoC.addGameToIoC("id", game);

        IoC.addCommandToIoC("turn", new TurnCommand(null));

        interpretCommand.execute();

        Game gameById = IoC.getGameById("id");
        Optional<Command> turnCommand = gameById
                .getMacroCommand()
                .getCommands()
                .stream()
                .filter(command -> "TurnCommand".equals(command.getClass().getSimpleName()))
                .findFirst();

        assertTrue(turnCommand.isPresent());
        GameSetting gameSetting = getGameSetting(turnCommand.get());
        assertEquals(3, gameSetting.getAngularVelocity());
    }

    private GameSetting getGameSetting(Command turnCommand) throws NoSuchFieldException, IllegalAccessException {
        Field gameSettingField = turnCommand.getClass().getDeclaredField("gameSetting");
        gameSettingField.setAccessible(true);
        Object gameSettingValue = gameSettingField.get(turnCommand);

        if (gameSettingValue instanceof GameSetting) {
            return (GameSetting) gameSettingValue;
        }
        return new GameSetting(0.0, 0.0);
    }

}