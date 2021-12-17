package edu.rice.comp504.model.strategy.move;

import edu.rice.comp504.model.object.character.ACharacter;
import edu.rice.comp504.model.strategy.IUpdateStrategy;

/**
 * NullStrategy.
 */
public class NullStrategy implements IUpdateStrategy {
    private static IUpdateStrategy ONLY;

    /**
     * Make an IUpdateStrategy.
     * @return the new IUpdateStrategy.
     */
    public static IUpdateStrategy make() {
        if (ONLY == null) {
            ONLY = new NullStrategy();
        }

        return ONLY;
    }

    @Override
    public String getName() {
        return "null";
    }

    @Override
    public void updateState(ACharacter context) {

    }

    @Override
    public void updateState(ACharacter context, ACharacter pacman) {

    }
}
