package edu.rice.comp504.adapter;

import edu.rice.comp504.model.PacmanStore;
import edu.rice.comp504.model.Response;

import java.beans.PropertyChangeListener;

/**
 * This adapter interfaces with the view (paint objects) and the controller.
 */
public class DispatchAdapter {

    private PacmanStore pacmanStore;
    /**
     * Constructor call.
     */

    public DispatchAdapter() {
        pacmanStore = PacmanStore.make();
    }

    /**
     * Set the canvas dimensions.
     *
     * @param dims The canvas width (x) and height (y).
     */
    public void setCanvasDims(String dims) {

    }

    /**
     * Update the game.
     *
     * @return Balls in BallWorld
     */
    public Response update() {
        return pacmanStore.update();
    }

    public void keypress(int key) {
        pacmanStore.keypress(key);
    }

    /**
     * Initialize the world.
     *
     * @return a map of items
     */
    public int[][] init(int level, int numberofGhosts) {
        // TODO: fill in
        return pacmanStore.init(level, numberofGhosts);
    }


    /**
     * Switch the strategy for switcher balls
     *
     * @param strat The REST request strategy.
     * @return Balls in BallWorld
     */
    public int[][] reset() {
        // TODO: fill in
        return pacmanStore.init(pacmanStore.getLevel(), pacmanStore.getNumberOfGhosts());
    }

}
