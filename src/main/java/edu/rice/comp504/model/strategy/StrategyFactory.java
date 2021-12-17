package edu.rice.comp504.model.strategy;

import edu.rice.comp504.model.strategy.move.*;

/**
 * StrategyFactory create strategies.
 **/
public class StrategyFactory implements IStrategyFac {
    private static StrategyFactory ONLY;

    /**
     * Make a strategy factory.
     * @return desired strategy factory.
     */
    public static StrategyFactory make() {
        if (ONLY == null) {
            ONLY = new StrategyFactory();
        }
        return ONLY;
    }

    /**
     * Make an IUpdateStrategy.
     * @param type strategy type.
     * @return the desired strategy.
     */
    @Override
    public IUpdateStrategy make(String type) {
        switch (type) {
            case "chase":
                return ChaseStrategy.make();
            case "random":
                return RandomStrategy.make();
            case "retreat":
                return RetreatStrategy.make();
            case "pacman":
                return PacmanStrategy.make();
            case "collide":
                return CollideStrategy.make();
            default:
                return NullStrategy.make();
        }
    }
}