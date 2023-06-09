package ru.otus.agent.model;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class ListOfGamers {

    @NotNull(message = "Организатор игры должен быть указан")
    private Gamer managerOfGame;

    private List<Gamer> gamers;

    public ListOfGamers(Gamer managerOfGame, List<Gamer> gamers) {
        this.managerOfGame = managerOfGame;
        this.gamers = gamers;
    }

    public ListOfGamers() {
    }

    public List<Gamer> getGamers() {
        return gamers;
    }

    public Gamer getManagerOfGame() {
        return managerOfGame;
    }
}
