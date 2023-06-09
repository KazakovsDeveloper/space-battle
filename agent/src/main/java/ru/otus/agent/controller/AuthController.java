package ru.otus.agent.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.agent.model.ListOfGamers;
import ru.otus.agent.service.AuthService;

@Validated
@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Один из пользователей организует танковый бой и определяет список участников (их может быть больше 2-х).
     */
    @PostMapping("/createBattle")
    public ResponseEntity<String> createToken(@Valid
                                              @RequestBody
                                              @NotNull ListOfGamers gamers) {
        authService.createToken(gamers);
        return ResponseEntity.status(201).body("Бой организован");
    }
}
