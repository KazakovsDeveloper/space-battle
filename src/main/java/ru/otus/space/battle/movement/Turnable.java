package ru.otus.space.battle.movement;

import ru.otus.space.battle.model.Direction;

public interface Turnable {

    void turn();

    Direction getDirection();

    int getAngularVelocity();

    void setDirection (Direction newV);

}
