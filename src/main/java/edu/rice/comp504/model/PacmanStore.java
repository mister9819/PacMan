package edu.rice.comp504.model;

import edu.rice.comp504.model.cmd.EatenCmd;
import edu.rice.comp504.model.cmd.SwitchStratCmd;
import edu.rice.comp504.model.cmd.UpdateStateCmd;
import edu.rice.comp504.model.map.MapObject;
import edu.rice.comp504.model.map.MapStore;
import edu.rice.comp504.model.object.AObject;
import edu.rice.comp504.model.object.ObjectFac;
import edu.rice.comp504.model.object.character.Ghost;
import edu.rice.comp504.model.object.character.GhostStatus;
import edu.rice.comp504.model.object.character.Pacman;
import edu.rice.comp504.model.strategy.StrategyFactory;

import java.awt.*;
import java.awt.geom.Point2D;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PacmanStore {
    private PropertyChangeSupport pcs;
    private MapStore mapStore;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    private int level;

    public int getNumberOfGhosts() {
        return numberOfGhosts;
    }

    public void setNumberOfGhosts(int numberOfGhosts) {
        this.numberOfGhosts = numberOfGhosts;
    }

    private int numberOfGhosts;
    private Pacman pacman;
    private HashMap<Point, AObject> mapObjects;
    private static PacmanStore ONLY;
    private StrategyFactory strategyFactory;
    private ObjectFac objectFac;
    private int tileSize;
    private int qps = 25;
    private final int frightenTime = qps * 10; // 10 seconds
    private List<Ghost> ghostList;
    private int timer;

    /**
     * Constructor.
     */
    private PacmanStore() {
        pcs = new PropertyChangeSupport(this);
        tileSize = 32;
        mapStore = MapStore.make();
        strategyFactory = StrategyFactory.make();
        tileSize = MapStore.make().getTileSize();
        mapObjects = new HashMap<>();
        objectFac = ObjectFac.make();
        ghostList = new ArrayList<>();
    }

    /**
     * Make a pacman store.
     *
     * @return A pacman store.
     */
    public static PacmanStore make() {
        if (ONLY == null) {
            ONLY = new PacmanStore();
        }
        return ONLY;
    }


    /**
     * Initialize the game.
     *
     * @param level          current level
     * @param numberOfGhosts number of ghosts
     * @return A 2d array representation of game map
     */
    public int[][] init(int level, int numberOfGhosts) {
        this.level = level;
        timer = 0;
        // Clear objects
        mapObjects.clear();
        for (PropertyChangeListener pcl : pcs.getPropertyChangeListeners("Entities")) {
            pcs.removePropertyChangeListener("Entities", pcl);
        }
        // Get map
        int[][] mapArr = mapStore.getMap(level);
        for (int y = 0; y < mapArr.length; y++) {
            for (int x = 0; x < mapArr[y].length; x++) {
                int mapObj = mapArr[y][x];
                if (mapObj == MapObject.PACMAN) {
                    int lastScore = 0;
                    if (pacman != null && !pacman.isDead()) {
                        lastScore = pacman.getScore();
                    }
                    pacman = (Pacman) objectFac.make(MapObject.iObject.get(mapObj), x * tileSize, y * tileSize);
                    pacman.setOrigin(new Point(x * tileSize, y * tileSize));
                    pacman.setScore(lastScore);
                    pcs.addPropertyChangeListener("Entities", pacman);
                } else if ((mapObj == MapObject.INKY && numberOfGhosts >= 1) ||
                        (mapObj == MapObject.BLINKY && numberOfGhosts >= 2) ||
                        (mapObj == MapObject.CLYDE && numberOfGhosts >= 3) ||
                        mapObj == MapObject.PINKY && numberOfGhosts >= 4) {
                    Ghost ghost = (Ghost) objectFac.make(MapObject.iObject.get(mapObj), x * tileSize, y * tileSize);
                    ghost.setOrigin(new Point(x * tileSize, y * tileSize));
                    ghostList.add(ghost);
                    pcs.addPropertyChangeListener("Entities", ghost);
                }
            }
        }
        return mapArr;
    }


    /**
     * @return 2d array representing the game map.
     */
    public int[][] reset() {
        return null;
    }

    public void keypress(int key) {
        pacman.setIntendDir(key);
    }

    /**
     * Get the canvas dimensions.
     *
     * @return The canvas dimensions
     */
    public static Point2D getCanvasDims() {
        return null;
    }

    /**
     * Set the canvas dimensions.
     *
     * @param dimss The canvas width (x) and height (y).
     */
    public static void setCanvasDims(String dimss) {
    }

    /**
     * Call the update method on all the ball observers to update their position in the ball world.
     */
    public Response update() {
        // TODO: only pass object around pacman
        UpdateStateCmd updateStateCmd = new UpdateStateCmd(pcs, mapObjects, tileSize, pacman);
        EatenCmd eatenCmd = new EatenCmd(pacman, tileSize);
        pcs.firePropertyChange("Entities", null, updateStateCmd);
        pcs.firePropertyChange("Entities", null, eatenCmd);
        detectTreasureCollide();
        updateGhostStrategy();
        timer++;
        if (timer % (15 * qps) == 0) {
            MapStore.make().generateFruit();
        }
        return new Response(0, 0, this.pacman.getScore(), pacman.getLives() <= 0, pacman.getLives(),
                pcs.getPropertyChangeListeners("Entities"), new ArrayList<>(mapObjects.values()),
                MapStore.make().getCurrentMap());
    }

    private void detectTreasureCollide() {
        int score = MapStore.make().detectDot(pacman.getPosition());
        pacman.setScore(pacman.getScore() + score);
        if (score == 50) {  // pill
            for (Ghost ghost : ghostList) {
                if (ghost.getGhostStatus() != GhostStatus.dead) {
                    ghost.setGhostStatus(GhostStatus.frightened);
                    ghost.setFrightenTime(0);
                }
            }
        }
    }

    private void updateGhostStrategy() {
        for (Ghost ghost : ghostList) {
            SwitchStratCmd switchCmd = null;
            if (ghost.getGhostStatus() == GhostStatus.dead) {
                if (!ghost.getStrategy().getName().equals("retreat")) {
                    switchCmd = new SwitchStratCmd("retreat");
                    pacman.setScore(pacman.getScore() + 200);
                }
                if (ghost.getPosition().equals(ghost.getOrigin())) {
                    switchCmd = resetGhostStrategy(ghost);
                    ghost.setGhostStatus(GhostStatus.normal);
                    ghost.setFrightenTime(0);
                }
            } else if (ghost.getGhostStatus() == GhostStatus.frightened) {
                if (ghost.getFrightenTime() >= frightenTime) {
                    switchCmd = resetGhostStrategy(ghost);
                    ghost.setGhostStatus(GhostStatus.normal);
                    ghost.setFrightenTime(0);
                } else if (ghost.getFrightenTime() == 0) {
//                    ghost.setDirection((ghost.getDirection() - 35) % 4 + 37);
                    switchCmd = new SwitchStratCmd("random");
                }
            }
            if (switchCmd != null) {
                switchCmd.execute(ghost);
            }
        }
    }

    private SwitchStratCmd resetGhostStrategy(Ghost ghost) {
        SwitchStratCmd switchCmd = null;
        switch (ghost.getGhostName()) {
            case "pinky":
            case "blinky":
            case "inky":
                switchCmd = new SwitchStratCmd("chase");
                break;
            case "clyde":
                switchCmd = new SwitchStratCmd("random");
                break;
            default:
                break;
        }
        return switchCmd;
    }

    /**
     * Add a ball that will listen for a property change (i.e. time elapsed)
     *
     * @param pcl The ball
     */
    private void addObjectToStore(PropertyChangeListener pcl) {
        // TODO: fill in
        pcs.addPropertyChangeListener("theClock", pcl);
    }

    public PropertyChangeListener[] getObjectsFromStore() {
        return pcs.getPropertyChangeListeners("theClock");
    }


    public static int randomInt(int min, int max) {
        return (int) (Math.random() * (max - min)) + min;
    }

    public static double randomDouble(int min, int max) {
        return (Math.random() * (max - min)) + min;
    }
}
