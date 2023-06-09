package ru.otus.space.battle.controller;

import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.space.battle.model.Settings;
import ru.otus.space.battle.service.GameService;
import ru.otus.space.battle.service.TokenService;

import java.util.Map;

@Validated
@RestController
public class SettingsGameController {

    private final GameService gameService;
    private final TokenService tokenService;

    public SettingsGameController(GameService gameService,
                                  TokenService tokenService) {
        this.gameService = gameService;
        this.tokenService = tokenService;
    }

    /**
     * Определить endpoint, который принимает входящее сообщение и конвертирует в команду InterpretCommand.
     */
    @PostMapping(value = "/settings", consumes = "application/json")
    public ResponseEntity<String> setGameSettings(@RequestBody
                                                  @NotNull Settings settings,
                                                  @RequestHeader Map<String, String> headers) {
        String authorization = headers.get("Authorization");
        tokenService.tokenCheck(authorization);
        gameService.addSettingsToGame(settings);
        return ResponseEntity.ok().body("настройки применены");
    }
}
