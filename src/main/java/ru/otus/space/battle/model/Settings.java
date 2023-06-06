package ru.otus.space.battle.model;


public class Settings {

    /**
     * id игры - для идентификации игры, в рамках которой это сообщение обработано.
     * С помощью этого id можно будет определить получателя сообщения при маршрутизации сообщения внутри игрового сервера.
     */
    private String gameId;

    /**
     * id операции - по этому id в IoC можно будет определить команду, которая будет обрабатывать данное сообщение.
     */
    private String operationId;

    /**
     * args - вложенный json объект с параметрами операции.
     * Содержимое этого объекта полностью зависит от команды, которая будет применяться к игровому объекту.
     */
    private Args args;

    public String getGameId() {
        return gameId;
    }

    public String getOperationId() {
        return operationId;
    }

    public Args getArgs() {
        return args;
    }

    public Settings(String gameId, String operationId, Args args) {
        this.gameId = gameId;
        this.operationId = operationId;
        this.args = args;
    }

    public Settings() {
    }
}
