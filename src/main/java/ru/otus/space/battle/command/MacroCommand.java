package ru.otus.space.battle.command;

import ru.otus.space.battle.exception.CommandException;

public class MacroCommand {

    private final Command[] commands;

    public MacroCommand(Command[] commands) {
        this.commands = commands;
    }

    public void execute() {
        for (Command command : commands) {

            try {
                command.execute();
            } catch (RuntimeException exception) {
                throw new CommandException("Что-то пошло не так " + exception);
            }

        }
    }

}
