package ru.otus.agent.model;

public class Gamer {

    private String login;
    private String name;

    public Gamer(String login, String name) {
        this.login = login;
        this.name = name;
    }

    public Gamer() {
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

}
