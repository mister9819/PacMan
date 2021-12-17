package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.object.character.ACharacter;

/**
 * The IObjCmd is an interface used to pass commands to objects in the PacMan game.  The
 * objects must execute the command.
 */
public interface ICharacterCmd {

    /**
     * Execute the command.
     *
     * @param context The receiver on which the command is executed.
     */
    void execute(ACharacter context);
}
