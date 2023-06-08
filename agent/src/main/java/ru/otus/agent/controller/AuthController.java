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

    @PostMapping("/createToken")
    public ResponseEntity<String> createToken(@Valid @RequestBody @NotNull ListOfGamers gamers) {
        authService.createToken(gamers);
        return ResponseEntity.status(201).body("Токен создан");
    }
}
