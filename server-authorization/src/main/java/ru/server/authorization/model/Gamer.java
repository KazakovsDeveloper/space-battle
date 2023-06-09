package ru.server.authorization.model;

public class Gamer {

    private String login;
    private String name;

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public Gamer(String login, String name) {
        this.login = login;
        this.name = name;
    }

    public Gamer() {
    }
}
