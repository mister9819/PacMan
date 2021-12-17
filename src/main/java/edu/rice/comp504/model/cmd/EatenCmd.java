package edu.rice.comp504.model.cmd;


import edu.rice.comp504.model.object.character.ACharacter;
import edu.rice.comp504.model.object.character.Ghost;
import edu.rice.comp504.model.object.character.GhostStatus;
import edu.rice.comp504.model.object.character.Pacman;

import java.awt.*;

/**
 * EatenCmd.
 */
public class EatenCmd implements ICharacterCmd {
    private static ICharacterCmd ONLY;
    private Pacman pacman;
    private int tileSize;

    /**
     * The constructor.
     */
    public EatenCmd(Pacman pacman, int tileSize) {
        this.pacman = pacman;
        this.tileSize = tileSize;
    }

    /**
     * Only makes one command.
     *
     * @return The command
     */
    public static ICharacterCmd make(Pacman pacman, int tileSize) {
        if (ONLY == null) {
            ONLY = new EatenCmd(pacman, tileSize);
        }
        return ONLY;
    }

    /**
     * Get the command name.
     *
     * @return command name
     */
    public String getName() {
        return "eaten";
    }

    /**
     * Execute the command.
     *
     * @param context The first receiver on which the command is executed.
     */
    public void execute(ACharacter context) {
        if (context != null && context.getName().equals("ghost")) {
            Ghost ghost = (Ghost) context;
            if (pacman.isDead() || ghost.getGhostStatus() == GhostStatus.dead) {
                return;
            }
            Point position = pacman.getPosition();
            Point ghostPosition = ghost.getPosition();
            double diffX = Math.abs(position.getX() - ghostPosition.getX());
            double diffY = Math.abs(position.getY() - ghostPosition.getY());
            if (diffX < tileSize / 2.0 && diffY < tileSize / 2.0) {
                if (ghost.getGhostStatus() == GhostStatus.frightened) {
                    ghost.setGhostStatus(GhostStatus.dead);
                } else if (ghost.getGhostStatus() == GhostStatus.normal) {
                    pacman.setDead(true);
                }
            }
        }
    }
}