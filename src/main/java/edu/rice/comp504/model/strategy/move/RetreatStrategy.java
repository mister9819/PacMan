package edu.rice.comp504.model.strategy.move;

import edu.rice.comp504.model.object.character.ACharacter;
import edu.rice.comp504.model.object.character.Ghost;
import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.awt.*;
import java.util.ArrayList;

/**
 * RandomStrategy makes ghost go back to base.
 **/
public class RetreatStrategy implements IUpdateStrategy {
    private static IUpdateStrategy ONLY;

    /**
     * Make an IUpdateStrategy.
     *
     * @return the desired IUpdateStrategy
     */
    public static IUpdateStrategy make() {
        if (ONLY == null) {
            ONLY = new RetreatStrategy();
        }

        return ONLY;
    }

    @Override
    public String getName() {
        return "retreat";
    }

    @Override
    public void updateState(ACharacter context) {

    }

    @Override
    public void updateState(ACharacter context, ACharacter pacmanContext) {
        if (!context.getName().equals("ghost") || !pacmanContext.getName().equals("pacman")) {
            return;
        }
        Point pos = context.getPosition();

        int direction = context.getDirection();
        Point origin = context.getOrigin();
        Point distance = new Point(origin.x - pos.x, origin.y - pos.y);
        int newDir = 0;
        int alternativeDir = 0;
        ArrayList<Integer> directions = new ArrayList<>();
        Ghost.rankDir(directions, distance);
        for (int dir : directions) {
            if (context.detectNoCollision(dir)) {
                if (newDir == 0) {
                    newDir = dir;
                } else {
                    alternativeDir = dir;
                    break;
                }
            }
        }
        if (alternativeDir != 0 && Math.abs(direction - newDir) == 2) {
            newDir = alternativeDir;
        }
        context.setDirection(newDir);
        context.updatePos();
    }
}
