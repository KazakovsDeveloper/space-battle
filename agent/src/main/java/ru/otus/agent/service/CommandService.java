package ru.otus.agent.service;

import ru.otus.agent.model.Settings;

public interface CommandService {

    void createCommand(Settings settings, String managerLogin);

}
