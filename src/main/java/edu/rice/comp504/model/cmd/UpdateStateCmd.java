package edu.rice.comp504.model.cmd;

import edu.rice.comp504.model.object.AObject;
import edu.rice.comp504.model.object.character.*;

import edu.rice.comp504.model.map.MapObject;
import edu.rice.comp504.model.object.character.ACharacter;
import edu.rice.comp504.model.object.character.Pacman;

import java.awt.*;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;

/**
 * The UpdateStateCmd will update the character's state.
 */
public class UpdateStateCmd implements ICharacterCmd {
    private PropertyChangeSupport pcs;
    private Pacman pacman;
    private MapObject mapObjects;
    private HashMap<Point, AObject> objectsMap;
    private int tileSize;

    /**
     * The constructor.
     *
     * @param pcs canvas inner walls.
     */
    public UpdateStateCmd(PropertyChangeSupport pcs, HashMap<Point, AObject> objectsMap, int tileSize, Pacman pacman) {
        this.pcs = pcs;
        this.objectsMap = objectsMap;
        this.tileSize = tileSize;
        this.pacman = pacman;
    }

    /**
     * Update the state of the ACharacter
     *
     * @param context The ACharacter.
     */
    @Override
    public void execute(ACharacter context) {
        if (context.getName().equals("pacman")) {
            Pacman pacman = (Pacman) context;
            if (pacman.isDead()) {
                pacman.setPosition(pacman.getOrigin());
                pacman.setLives(pacman.getLives() - 1);
                pacman.setDead(false);
            } else {
                pacman.getStrategy().updateState(pacman);
            }

//            Point pos = pacman.getPosition();
//            int roundX = (int) Math.round(((double) pos.x )/ tileSize);
//            int roundY = (int) Math.round(((double) pos.y )/ tileSize);
//            Point roundPos = new Point(roundX * tileSize, roundY * tileSize);
//            if(objectsMap.containsKey(roundPos)){
//                AObject object = objectsMap.get(roundPos);
//                EatenCmd.make().execute(object, pacman);
//            }
        } else {
            Ghost ghost = (Ghost) context;
            ghost.getStrategy().updateState(context, pacman);
//            System.out.println("name: " + ghost.getName() + "pos: " + ghost.getPosition().x + ", " + ghost.getPosition().y);
        }
    }
}
