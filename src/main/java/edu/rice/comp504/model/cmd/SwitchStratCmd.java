package edu.rice.comp504.model.cmd;


import edu.rice.comp504.model.object.character.ACharacter;
import edu.rice.comp504.model.strategy.IUpdateStrategy;
import edu.rice.comp504.model.strategy.StrategyFactory;

/**
 * The SwitchStratCmd will possibly switch the strategy of a ACharacter.
 */
public class SwitchStratCmd implements ICharacterCmd {
    private final IUpdateStrategy newStrat;

    /**
     * The constructor.
     */
    public SwitchStratCmd(String newStrategy) {
        this.newStrat = StrategyFactory.make().make(newStrategy);
    }

    /**
     * Switch the strategy of the ACharacter
     *
     * @param context The character.
     */
    public void execute(ACharacter context) {
        if (context != null && context.getName().equals("ghost")) {
            context.setStrategy(newStrat);
        }
    }
}