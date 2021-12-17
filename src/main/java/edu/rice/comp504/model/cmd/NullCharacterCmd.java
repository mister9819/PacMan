package edu.rice.comp504.model.cmd;


import edu.rice.comp504.model.object.character.ACharacter;

/**
 * The NullCharacterCmd does nothing.
 */
public class NullCharacterCmd implements ICharacterCmd {
    private static ICharacterCmd ONLY;

    /**
     * The constructor.
     *
     */
    public NullCharacterCmd() {
    }

    /**
     * Only makes one null command.
     * @return The null command
     */
    public static ICharacterCmd make() {
        if (ONLY == null ) {
            ONLY = new NullCharacterCmd();
        }
        return ONLY;
    }

    /**
     * Get the command name.
     * @return command name
     */
    public String getName() {
        return "null";
    }

    /**
     * Update the state of the paint object
     * @param context  The character.
     */
    public void execute(ACharacter context) {
        System.out.println("A null character command has been executed!");
    }

}