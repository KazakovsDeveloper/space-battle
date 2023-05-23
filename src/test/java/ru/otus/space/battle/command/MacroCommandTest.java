package ru.otus.space.battle.command;

import net.jodah.concurrentunit.Waiter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.concurrent.ConcurrentLinkedQueue;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MacroCommandTest {
    private ConcurrentLinkedQueue<Command> commands;
    private MacroCommand macroCommand;
    private Waiter waiter;

    @BeforeAll
    public void setUp() {
        commands = new ConcurrentLinkedQueue<>();
        macroCommand = new MacroCommand(commands);
        waiter = new Waiter();
    }

    @Test
    public void testExecute() throws Throwable {
        Command command1 = new CommandStub(waiter);
        Command command2 = new CommandStub(waiter);
        Command command3 = new CommandStub(waiter);

        commands.offer(command1);
        commands.offer(command2);
        commands.offer(command3);

        macroCommand.execute();
        // дожидаемся завершения команд
        waiter.await();

        // все команды выполнены
        assertTrue(command1.execute());
        assertTrue(command2.execute());
        assertTrue(command3.execute());
    }

    /**
     * CommandStub устанавливают флаг при выполнении команд
     * и вызывают метод resume() для объекта Waiter,
     * чтобы указать, что они завершены.
     */
    private static class CommandStub implements Command {
        private final Waiter waiter;

        public CommandStub(Waiter waiter) {
            this.waiter = waiter;
        }

        public boolean execute() {
            waiter.resume();
            return true;
        }
    }
}