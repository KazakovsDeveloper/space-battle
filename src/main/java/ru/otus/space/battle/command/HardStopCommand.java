package ru.otus.space.battle.command;

import java.util.concurrent.ExecutorService;

public class HardStopCommand implements Command {

    @Override
    public boolean execute() {
        return true;
    }

    public void hardStop(ExecutorService executor) {
        executor.shutdownNow();
    }
}
