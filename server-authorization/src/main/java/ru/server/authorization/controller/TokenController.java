package ru.server.authorization.controller;

import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.server.authorization.model.TokenResponse;
import ru.server.authorization.service.TokenService;

@RestController
public class TokenController {

    private final TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/createToken")
    public ResponseEntity<TokenResponse> generateToken(@RequestBody String tankBattleId,
                                                @RequestHeader
                                                @NotNull String gamerLogin) {
        TokenResponse token = tokenService.generateToken(tankBattleId, gamerLogin);
        return ResponseEntity.ok(token);
    }

}
