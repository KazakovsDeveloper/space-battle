package ru.otus.space.battle.movement;

import ru.otus.space.battle.model.Vector;

public interface Movable {

    void move();

    Vector getPosition();

    Vector getVelocity();

}
