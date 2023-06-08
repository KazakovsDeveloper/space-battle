package ru.otus.agent.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.agent.model.Settings;
import ru.otus.agent.service.CommandService;

@RestController
public class GameController {

    private final CommandService commandService;

    public GameController(CommandService commandService) {
        this.commandService = commandService;
    }

    @PostMapping("/createCommand")
    public ResponseEntity<String> createCommand(@RequestBody Settings settings, @RequestHeader String managerLogin) {
        commandService.createCommand(settings, managerLogin);
        return ResponseEntity.status(201).body("Команда создана");
    }

}
