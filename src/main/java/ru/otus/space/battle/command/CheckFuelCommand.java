package ru.otus.space.battle.command;

import ru.otus.space.battle.exception.CommandException;
import ru.otus.space.battle.model.GameSetting;
import ru.otus.space.battle.utils.DoubleValueChecker;

import static java.util.Objects.nonNull;

/**
 * CheckFuelCommand проверяет, что топлива достаточно, если нет, то выбрасывает исключение CommandException.
 */
public class CheckFuelCommand implements DoubleValueChecker {

    private final GameSetting gameSetting;

    public CheckFuelCommand(GameSetting gameSetting) {
        this.gameSetting = gameSetting;
    }

    public boolean execute() {
        return checkFuel();
    }

    private boolean checkFuel() {
        if (nonNull(gameSetting)) {
            double fuel = gameSetting.getFuel();
            double consumption = gameSetting.getConsumption();

            valueChecker(fuel);
            valueChecker(consumption);

            if (fuel >= consumption) {
                return true;
            }
            throw new CommandException("Топлива недостаточно");
        }
        throw new CommandException("Настройки игры не установлены");
    }

}
