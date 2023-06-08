package ru.otus.space.battle.command;

import ru.otus.space.battle.model.GameSetting;

import java.util.concurrent.ExecutorService;

/**
 * Написать команду, которая останавливает цикл выполнения команд из пункта 1,
 * только после того, как все команды завершат свою работу (soft stop).
 */
public class SoftStopCommand implements Command {

    @Override
    public boolean execute() {
        return true;
    }

    @Override
    public void setGameSettings(GameSetting newGameSetting) {

    }

    public void softStop(ExecutorService executor) {
        executor.shutdown();
    }
}
