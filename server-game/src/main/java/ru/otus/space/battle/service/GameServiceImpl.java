package ru.otus.space.battle.service;

import org.springframework.stereotype.Service;
import ru.otus.space.battle.IoC.IoC;
import ru.otus.space.battle.command.Command;
import ru.otus.space.battle.command.InterpretCommand;
import ru.otus.space.battle.model.Settings;

@Service
public class GameServiceImpl implements GameService {

    /**
     * endpoint должен определить игру, которой адресовано сообщение,
     * создать команду InterpretCommand и поместить эту команду в очередь команд этой игры.
     */

    @Override
    public void addSettingsToGame(Settings settings) {
        String gameId = settings.getGameId();
        /**
         * Команда InterpretCommand получает всю информацию об операции,
         * которую необходимо выполнить, параметрах и объекте, над которым эта операция будет выполняться.
         */
        Command interpret = new InterpretCommand(settings);
        IoC.addToCommandQueue(gameId, interpret);
    }
}
