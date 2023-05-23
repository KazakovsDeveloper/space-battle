package ru.otus.space.battle.command;

import net.jodah.concurrentunit.Waiter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import ru.otus.space.battle.model.GameSetting;

import java.util.concurrent.ConcurrentLinkedQueue;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MacroCommandTest {

    private ConcurrentLinkedQueue<Command> commands;
    private MacroCommand macroCommand;
    private Waiter waiter;

    private MacroCommand macroCommandWithShutDownNow;
    private ConcurrentLinkedQueue<Command> commandsWithShutDownNow;
    private HardStopCommand hardStopCommand;

    private MacroCommand macroCommandWithShutDown;
    private ConcurrentLinkedQueue<Command> commandsWithShutDown;
    private SoftStopCommand softStopCommand;


    @BeforeAll
    public void setUp() {
        commands = new ConcurrentLinkedQueue<>();
        macroCommand = new MacroCommand(commands);
        waiter = new Waiter();

        commandsWithShutDownNow = new ConcurrentLinkedQueue<>();
        macroCommandWithShutDownNow = new MacroCommand(commandsWithShutDownNow);
        hardStopCommand = new HardStopCommand();
        commandsWithShutDownNow.add(new RotateCommand());
        commandsWithShutDownNow.add(hardStopCommand);
        commandsWithShutDownNow.add(new BurnFuelCommand(new GameSetting(5.0, 5.0)));

        commandsWithShutDown = new ConcurrentLinkedQueue<>();
        softStopCommand = new SoftStopCommand();
        commandsWithShutDown.add(new BurnFuelCommand(new GameSetting(5.0, 5.0)));
        commandsWithShutDown.add(softStopCommand);
        commands.add(new RotateCommand());
        macroCommandWithShutDown = new MacroCommand(commandsWithShutDown);
    }

    @Test
    @DisplayName("проверяем, что команда hard stop выполнена")
    public void testHardStop() {
        macroCommandWithShutDownNow.execute();
        assertTrue(hardStopCommand.execute());
    }

    @Test
    @DisplayName("проверяем, что команда soft stop выполнена")
    public void testSoftStop() {
        macroCommandWithShutDown.execute();
        assertTrue(softStopCommand.execute());
    }

    @Test
    @DisplayName("проверяем, что команды выполнены")
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