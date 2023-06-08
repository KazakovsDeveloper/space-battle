package ru.otus.agent.service;

import ru.otus.agent.model.ListOfGamers;

public interface AuthService {

    void createToken(ListOfGamers gamers);
}
