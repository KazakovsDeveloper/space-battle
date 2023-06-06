package ru.otus.space.battle.command;

import ru.otus.space.battle.model.GameSetting;

import java.util.concurrent.ExecutorService;

/**
 * Написать команду, которая останавливает цикл выполнения команд из пункта 1,
 * не дожидаясь их полного завершения (hard stop).
 */
public class HardStopCommand implements Command {

    @Override
    public boolean execute() {
        return true;
    }

    @Override
    public void setGameSettings(GameSetting newGameSetting) {

    }

    public void hardStop(ExecutorService executor) {
        executor.shutdownNow();
    }
}
