package ru.otus.agent.model;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class ListOfGamers {

    @NotBlank(message = "managerLogin должен быть заполнен")
    private String managerLogin;

    private List<Gamer> gamers;

    public List<Gamer> getGamers() {
        return gamers;
    }

    public String getManagerLogin() {
        return managerLogin;
    }
}
