package ru.otus.space.battle.model;

public class Args {

    private double fuel;

    private double consumption;

    private int xVelocity;

    private int yVelocity;

    private int xPosition;

    private int yPosition;

    private int direction;

    private int directionsNumber;

    private int angularVelocity;

    public Args(double fuel, double consumption, int xVelocity, int yVelocity, int xPosition, int yPosition, int direction, int directionsNumber, int angularVelocity) {
        this.fuel = fuel;
        this.consumption = consumption;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.direction = direction;
        this.directionsNumber = directionsNumber;
        this.angularVelocity = angularVelocity;
    }

    public Args() {
    }

    public double getFuel() {
        return fuel;
    }

    public double getConsumption() {
        return consumption;
    }

    public int getxVelocity() {
        return xVelocity;
    }

    public int getyVelocity() {
        return yVelocity;
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public int getDirection() {
        return direction;
    }

    public int getDirectionsNumber() {
        return directionsNumber;
    }

    public int getAngularVelocity() {
        return angularVelocity;
    }
}
