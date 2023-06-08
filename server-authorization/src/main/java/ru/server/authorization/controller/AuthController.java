package ru.server.authorization.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.server.authorization.model.ListOfGamers;
import ru.server.authorization.service.TankBattleService;

@Validated
@RestController
public class AuthController {

    private final TankBattleService tankBattleService;

    public AuthController(TankBattleService tankBattleService) {
        this.tankBattleService = tankBattleService;
    }

    @PostMapping("/tankBattleId")
    public ResponseEntity<String> createTankBattleId(@RequestBody
                                                     @NotNull
                                                     @Valid ListOfGamers gamers) {
        String tankBattleId = tankBattleService.createTankBattle(gamers);
        return ResponseEntity.ok(tankBattleId);
    }
}
