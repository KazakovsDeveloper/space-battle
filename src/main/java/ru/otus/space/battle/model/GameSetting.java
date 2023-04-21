package ru.otus.space.battle.model;

public class GameSetting {

    private double fuel;

    private final double consumption;

    private Vector velocity;

    private Vector position;

    public GameSetting(double fuel, double consumption, Vector position, Vector velocity) {
        this.fuel = fuel;
        this.consumption = consumption;
        this.position = position;
        this.velocity = velocity;
    }

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

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public Vector getVelocity() {
        return velocity;
    }
}
