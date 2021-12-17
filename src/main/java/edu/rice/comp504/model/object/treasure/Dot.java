package edu.rice.comp504.model.object.treasure;

import java.awt.*;
import java.awt.geom.Point2D;
import java.beans.PropertyChangeEvent;

public class Dot extends Treasure {
    public Dot(Point position) {
        super("dot", 10, position);
    }
}
