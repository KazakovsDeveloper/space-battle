package ru.otus.space.battle.IoC;

import ru.otus.space.battle.command.Command;
import ru.otus.space.battle.command.MacroCommand;
import ru.otus.space.battle.mapper.SettingsMapper;
import ru.otus.space.battle.model.Game;
import ru.otus.space.battle.model.GameSetting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class IoC {

    private static final Map<String, Game> games = new HashMap<>();
    private static final Map<String, Command> commands = new HashMap<>();
    private static final SettingsMapper settingsMapper = new SettingsMapper();

    public static void addGameToIoC(String gameId, Game game) {
        games.put(gameId, game);
    }

    public static Game getGameById(String gameId) {
        return games.get(gameId);
    }

    public static void addCommandToIoC(String commandId, Command command) {
        commands.put(commandId, command);
    }

    public static Command getCommandById(String commandId) {
        return commands.get(commandId);
    }

    public static void addToCommandQueue(String gameId, Command command) {
        Game gameById = getGameById(gameId);
        MacroCommand macroCommand = gameById.getMacroCommand();
        macroCommand.addCommandToQueue(command);
    }

    public static List<Command> getCommandQueue(String gameId) {
        Game game = games.get(gameId);
        return game.getMacroCommand().getCommands();
    }

    public static void setSettings(String operationId, GameSetting gameSetting) {
        Command commandById = getCommandById(operationId);
        commandById.setGameSettings(gameSetting);
    }

    public static SettingsMapper getSettingsMapper() {
        return settingsMapper;
    }

}
