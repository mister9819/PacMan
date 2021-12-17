package edu.rice.comp504.model.strategy;

/**
 * IStrategy Factory interface.
 */
public interface IStrategyFac {
    /**
     * Makes a strategy.
     *
     * @return A strategy
     */
    IUpdateStrategy make(String type);
}
