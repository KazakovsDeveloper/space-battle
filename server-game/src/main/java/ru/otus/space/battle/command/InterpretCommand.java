package ru.otus.space.battle.command;

import ru.otus.space.battle.IoC.IoC;
import ru.otus.space.battle.mapper.SettingsMapper;
import ru.otus.space.battle.model.Args;
import ru.otus.space.battle.model.GameSetting;
import ru.otus.space.battle.model.Settings;

/**
 * Задача команды InetrpretCommand на основе IoC контейнера создать команду для нужного объекта,
 * которая соответствует приказу, содержащемуся в сообщении и постановки этой команды в очередь команд игры.
 */
public class InterpretCommand implements Command {

    private final Settings settings;
    private final SettingsMapper mapper;

    public InterpretCommand(Settings settings) {
        this.settings = settings;
        this.mapper = IoC.getSettingsMapper();
    }

    /**
     * Например, если сообщение указано, что объект с id 548 должен начать двигаться:
     * var obj = IoC.Resolve("Игровые объекты", "548"); // "548" получено из входящего сообщения
     * IoC.Resolve("Установить начальное значение скорости", obj, 2); // значение 2 получено из args переданного в сообщении
     * var cmd = IoC.Resolve("Движение по прямой", obj); // Решение, что нужно выполнить движение по прямой получено из сообщения
     * // обратите внимание само значение "Движение по прямой" нельзя читать на прямую из сообщения,
     * // чтобы избежать инъекции, когда пользователь попытается выполнить действие, которое ему выполнять не позволено
     * IoC.Resolve("Очередь команд", cmd).Execute(); // Выполняем команду, которая поместит команду cmd в очередь команд игры.
     */

    @Override
    public boolean execute() {
        // устанавливаем определенные значения, получаемые из аргументов
        Args args = settings.getArgs();
        GameSetting gameSetting = mapper.mapSettings(args);
        // находим команду для установки значений
        String operationId = settings.getOperationId();
        IoC.setSettings(operationId, gameSetting);
        // помещаем команду в очередь команд
        Command commandById = IoC.getCommandById(operationId);
        String gameId = settings.getGameId();
        IoC.addToCommandQueue(gameId, commandById);
        return true;
    }

    @Override
    public void setGameSettings(GameSetting newGameSetting) {
    }

    public Settings getSettings() {
        return settings;
    }
}
