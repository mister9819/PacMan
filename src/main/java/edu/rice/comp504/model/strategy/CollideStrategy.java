package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.object.character.ACharacter;

/**
 * CollideStrategy determine the interaction between pacman and treasure.
 **/
public class CollideStrategy implements IUpdateStrategy {
    private static IUpdateStrategy ONLY;

    /**
     * Make an IUpdateStrategy.
     *
     * @return the new IUpdateStrategy.
     */
    public static IUpdateStrategy make() {
        if (ONLY == null) {
            ONLY = new CollideStrategy();
        }

        return ONLY;
    }

    @Override
    public String getName() {
        return "collide";
    }

    @Override
    public void updateState(ACharacter context) {

    }

    @Override
    public void updateState(ACharacter context, ACharacter pacman) {

    }
}
