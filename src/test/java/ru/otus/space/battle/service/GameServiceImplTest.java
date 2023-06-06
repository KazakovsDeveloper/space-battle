package ru.otus.space.battle.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.space.battle.IoC.IoC;
import ru.otus.space.battle.command.Command;
import ru.otus.space.battle.command.MacroCommand;
import ru.otus.space.battle.command.TurnCommand;
import ru.otus.space.battle.model.Args;
import ru.otus.space.battle.model.Game;
import ru.otus.space.battle.model.Settings;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GameServiceImplTest {

    private GameServiceImpl gameService = new GameServiceImpl();

    @Test
    @DisplayName("проверяем, что команда добавлена в очередь на выполнение")
    public void addSettingsToGameTest() {
        ConcurrentLinkedQueue<Command> queue = new ConcurrentLinkedQueue<>();
        MacroCommand macroCommand = new MacroCommand(queue);
        Game game = new Game("id", macroCommand);
        IoC.addGameToIoC("id", game);

        Settings settings = new Settings("id", "operationId", new Args());
        gameService.addSettingsToGame(settings);
        List<Command> commands = IoC.getCommandQueue("id");
        Optional<Command> interpretCommand = commands.stream()
                .filter(command -> "InterpretCommand".equals(command.getClass().getSimpleName()))
                .findFirst();

        assertTrue(interpretCommand.isPresent());
    }

}