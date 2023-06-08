package ru.otus.space.battle.model;

import ru.otus.space.battle.command.MacroCommand;

public class Game {

    private final MacroCommand macroCommand;
    private String gameId;

    public Game(MacroCommand macroCommand) {
        this.macroCommand = macroCommand;
    }

    public Game(String gameId, MacroCommand macroCommand) {
        this.gameId = gameId;
        this.macroCommand = macroCommand;
    }

    public MacroCommand getMacroCommand() {
        return macroCommand;
    }
}
