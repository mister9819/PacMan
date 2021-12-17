package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.object.character.ACharacter;
import edu.rice.comp504.model.object.character.Pacman;

/**
 * PacmanStrategy that determine the pacman behavior.
 * It is either horizontal or vertical.
 **/
public class PacmanStrategy implements IUpdateStrategy {
    private static IUpdateStrategy ONLY;

    /**
     * Make an IUpdateStrategy.
     *
     * @return a new IUpdateStrategy.
     */
    public static IUpdateStrategy make() {
        if (ONLY == null) {
            ONLY = new PacmanStrategy();
        }

        return ONLY;
    }

    @Override
    public String getName() {
        return "pacman";
    }

    @Override
    public void updateState(ACharacter context) {
        if (context.getName().equals("pacman")) {
            Pacman pacman = (Pacman) context;
            if (pacman.isDead()) {
                return;
            }
            pacman.updatePos();
        }
    }

    @Override
    public void updateState(ACharacter context, ACharacter pacman) {

    }
}
