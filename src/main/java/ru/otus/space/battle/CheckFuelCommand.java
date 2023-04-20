package ru.otus.space.battle;

/**
 * CheckFuelCommand проверяет, что топлива достаточно, если нет, то выбрасывает исключение CommandException.
 */
public class CheckFuelCommand {

    private GameSetting gameSetting;

    public CheckFuelCommand(GameSetting gameSetting) {
        this.gameSetting = gameSetting;
    }

    public boolean execute() {
        return false;
    }

}
