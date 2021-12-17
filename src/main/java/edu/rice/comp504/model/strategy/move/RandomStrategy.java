package edu.rice.comp504.model.strategy.move;

import edu.rice.comp504.model.object.character.ACharacter;
import edu.rice.comp504.model.object.character.Direction;
import edu.rice.comp504.model.object.character.Ghost;
import edu.rice.comp504.model.object.character.GhostStatus;
import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.util.ArrayList;
import java.util.Random;

/**
 * RandomStrategy makes ghost move randomly.
 */
public class RandomStrategy implements IUpdateStrategy {
    private static IUpdateStrategy ONLY;

    /**
     * Make an IUpdateStrategy.
     *
     * @return the new IUpdateStrategy
     */
    public static IUpdateStrategy make() {
        if (ONLY == null) {
            ONLY = new RandomStrategy();
        }

        return ONLY;
    }

    @Override
    public String getName() {
        return "random";
    }

    @Override
    public void updateState(ACharacter context) {

    }

    @Override
    public void updateState(ACharacter context, ACharacter pacmanContext) {
        if (!context.getName().equals("ghost") || !pacmanContext.getName().equals("pacman")) {
            return;
        }

        Ghost ghost = (Ghost) context;
        if (ghost.getGhostStatus() == GhostStatus.frightened) {
            ghost.setFrightenTime(ghost.getFrightenTime() + 1);
        }
        int direction = context.getDirection();
        if (!context.detectNoCollision(direction)) {
            int newDir;
            ArrayList<Integer> directions = new ArrayList<>();
            for (int i = Direction.LEFT; i <= Direction.DOWN; i++) {
                if (context.detectNoCollision(i)) {
                    directions.add(i);
                }
            }
            if (directions.size() == 1) {
                newDir = directions.get(0);
            } else {
                newDir = directions.get(new Random().nextInt(directions.size()));
                while (Math.abs(newDir - direction) == 2) {
                    newDir = directions.get(new Random().nextInt(directions.size()));
                }
            }
            context.setDirection(newDir);
        }

        context.updatePos();


    }
}
