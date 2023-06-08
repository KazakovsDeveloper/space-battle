package ru.otus.space.battle.command;

import ru.otus.space.battle.model.GameSetting;

public interface Command {

    boolean execute();

    void setGameSettings(GameSetting newGameSetting);
}
