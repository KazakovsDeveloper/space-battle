package ru.otus.space.battle.model;

public class GameSetting {

    private final double fuel;

    private final double consumption;

    public GameSetting(double fuel, double consumption) {
        this.fuel = fuel;
        this.consumption = consumption;
    }

    public double getFuel() {
        return fuel;
    }

    public double getConsumption() {
        return consumption;
    }
}
