package edu.rice.comp504.model.object;

import edu.rice.comp504.model.object.treasure.Treasure;
import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.awt.geom.Point2D;

/**
 * Factory to make object.
 */
public interface IObjectFac {

    /**
     * Makes a strategy.
     *
     * @return A strategy
     */
    AObject make(String type, int posX, int posY);
}
