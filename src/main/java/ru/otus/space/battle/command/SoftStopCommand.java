package ru.otus.space.battle.command;

import java.util.concurrent.ExecutorService;

public class SoftStopCommand implements Command {

    @Override
    public boolean execute() {
        return true;
    }

    public void softStop(ExecutorService executor) {
        executor.shutdown();
    }
}
