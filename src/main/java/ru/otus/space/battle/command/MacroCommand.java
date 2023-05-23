package ru.otus.space.battle.command;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MacroCommand {
    private final ConcurrentLinkedQueue<Command> commands;
    private final ExecutorService executor;

    public MacroCommand(ConcurrentLinkedQueue<Command> commands) {
        this.commands = commands;
        this.executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    public void execute() {
        while (!commands.isEmpty()) {
            Command command = commands.poll();
            if (command != null) {
                executor.submit(() -> {
                    try {
                        System.out.println(command.getClass() + " ");
                        command.execute();
                    } catch (RuntimeException exception) {
                        System.out.println("Исключение: " + exception);
                    }
                });
            }
        }
        executor.shutdown();
    }
}