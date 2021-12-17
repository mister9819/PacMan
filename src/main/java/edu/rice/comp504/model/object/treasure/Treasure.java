package edu.rice.comp504.model.object.treasure;

import edu.rice.comp504.model.object.AObject;

import java.awt.Point;


public abstract class Treasure extends AObject {
    public int bonusPoint;

    public Treasure(String name, int bonusPoint, Point position) {
        super(name, position);
        this.bonusPoint = bonusPoint;
    }
}
