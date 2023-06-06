package ru.otus.space.battle.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.space.battle.model.Settings;
import ru.otus.space.battle.service.GameService;

@RestController
public class SettingsGameController {

    private final GameService gameService;

    public SettingsGameController(GameService gameService) {
        this.gameService = gameService;
    }

    /**
     * Определить endpoint, который принимает входящее сообщение и конвертирует в команду InterpretCommand.
     */
    @PostMapping("/settings")
    public ResponseEntity<String> setGameSettings(@RequestBody Settings settings) {
        gameService.addSettingsToGame(settings);
        return ResponseEntity.ok().body("настройки применены");
    }


}
