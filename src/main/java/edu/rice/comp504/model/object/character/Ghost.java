package edu.rice.comp504.model.object.character;

import edu.rice.comp504.model.cmd.ICharacterCmd;
import edu.rice.comp504.model.map.MapStore;
import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

/**
 * A ghost character.
 */
public class Ghost extends ACharacter {
    protected String ghostName;
    private int frightenTime;
    protected GhostStatus ghostStatus = GhostStatus.normal;

    public int getFrightenTime() {
        return frightenTime;
    }

    public void setFrightenTime(int frightenTime) {
        this.frightenTime = frightenTime;
    }

    public GhostStatus getGhostStatus() {
        return ghostStatus;
    }

    public void setGhostStatus(GhostStatus ghostStatus) {
        this.ghostStatus = ghostStatus;
    }

    /**
     * Ghost constructor.
     *
     * @param position  ghost position.
     * @param strategy  ghost strategy.
     * @param direction ghost direction.
     * @param name      ghost name.
     */
    public Ghost(Point position, IUpdateStrategy strategy, int direction, String name) {
        super("ghost", position, strategy, direction, new Point(9, 9));
        this.ghostName = name;
        frightenTime = 0;
    }

    /**
     * Get ghost name.
     *
     * @return ghost name.
     */
    public String getGhostName() {
        return ghostName;
    }

    /**
     * Detect whether there is a collision.
     *
     * @param direction current direction.
     * @return whether there is a collision.
     */
    @Override
    public boolean detectNoCollision(int direction) {
        Point nextPos;
        switch (direction) {
            case Direction.UP:
                nextPos = new Point(this.position.x, this.position.y - this.velocity.y);
                break;
            case Direction.RIGHT:
                nextPos = new Point(this.position.x + this.velocity.x, this.position.y);
                break;
            case Direction.DOWN:
                nextPos = new Point(this.position.x, this.position.y + this.velocity.y);
                break;
            case Direction.LEFT:
                nextPos = new Point(this.position.x - this.velocity.x, this.position.y);
                break;
            default:
                return false;
        }
        return MapStore.make().detectWall(nextPos);
    }

    /**
     * Rank the next ghost direction.
     *
     * @param directions list of directions.
     * @param distance   current distance.
     */
    public static void rankDir(ArrayList<Integer> directions, Point distance) {
        ArrayList<Integer> xDir = new ArrayList<>();
        ArrayList<Integer> yDir = new ArrayList<>();
        if (distance.x > 0) {
            xDir.add(Direction.RIGHT);
            xDir.add(Direction.LEFT);
        } else {
            xDir.add(Direction.LEFT);
            xDir.add(Direction.RIGHT);
        }
        if (distance.y > 0) {
            yDir.add(Direction.DOWN);
            yDir.add(Direction.UP);
        } else {
            yDir.add(Direction.UP);
            yDir.add(Direction.DOWN);
        }
        if (Math.abs(distance.x) >= Math.abs(distance.y)) {
            directions.add(xDir.get(0));
            directions.add(yDir.get(0));
            directions.add(yDir.get(1));
            directions.add(xDir.get(1));
        } else {
            directions.add(yDir.get(0));
            directions.add(xDir.get(0));
            directions.add(xDir.get(1));
            directions.add(yDir.get(1));
        }
    }

    /**
     * Update state of the character when the property change event occurs by executing the command.
     */
    public void propertyChange(PropertyChangeEvent e) {
        ((ICharacterCmd) e.getNewValue()).execute(this);
    }

    /**
     * Update position.
     */
    public void updatePos() {
        Point nextPos;
        switch (direction) {
            case Direction.UP:
                nextPos = new Point(this.position.x, this.position.y - this.velocity.y);
                break;
            case Direction.RIGHT:
                nextPos = new Point(this.position.x + this.velocity.x, this.position.y);
                break;
            case Direction.DOWN:
                nextPos = new Point(this.position.x, this.position.y + this.velocity.y);
                break;
            case Direction.LEFT:
                nextPos = new Point(this.position.x - this.velocity.x, this.position.y);
                break;
            default:
                return;
        }
        this.setPosition(nextPos);
    }
}
