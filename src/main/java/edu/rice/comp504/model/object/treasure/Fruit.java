package edu.rice.comp504.model.object.treasure;

import java.awt.*;
import java.awt.geom.Point2D;
import java.beans.PropertyChangeEvent;

public class Fruit extends Treasure {
    protected int time;

    public Fruit(Point position) {
        super("fruit", 100, position);
        time = 5000;
    }
}
