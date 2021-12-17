package edu.rice.comp504.model.object.character;

import edu.rice.comp504.model.cmd.ICharacterCmd;
import edu.rice.comp504.model.map.MapStore;
import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.awt.*;
import java.beans.PropertyChangeEvent;

public class Pacman extends ACharacter {
    protected int lives;
    protected int intendDir;
    protected int score;

    /**
     * Pacman constructor.
     *
     * @param position  current position.
     * @param strategy  current strategy.
     * @param lives     current live number.
     * @param direction current direction.
     */
    public Pacman(Point position, IUpdateStrategy strategy, int lives, int direction) {
        super("pacman", position, strategy, direction, new Point(12, 12));
        this.lives = lives;
        this.intendDir = direction;
        this.score = 0;
    }

    /**
     * Detect whether there is a collision.
     *
     * @param direction current direction
     * @return whether there is a collision.
     */
    @Override
    public boolean detectNoCollision(int direction) {
        //todo
        return false;
    }

    /**
     * Update state of the ball when the property change event occurs by executing the command.
     */
    public void propertyChange(PropertyChangeEvent e) {
        ((ICharacterCmd) e.getNewValue()).execute(this);
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getIntendDir() {
        return intendDir;
    }

    public void setIntendDir(int intendDir) {
        this.intendDir = intendDir;
    }

    /**
     * Update position.
     */
    public void updatePos() {
        //detect intend movement
        Point nextPos1;
        if (intendDir >= Direction.LEFT && intendDir <= Direction.DOWN) {
            switch (intendDir) {
                case Direction.UP:
                    nextPos1 = new Point(this.position.x, this.position.y - this.velocity.y);
                    break;
                case Direction.RIGHT:
                    nextPos1 = new Point(this.position.x + this.velocity.x, this.position.y);
                    break;
                case Direction.DOWN:
                    nextPos1 = new Point(this.position.x, this.position.y + this.velocity.y);
                    break;
                case Direction.LEFT:
                    nextPos1 = new Point(this.position.x - this.velocity.x, this.position.y);
                    break;
                default:
                    return;
            }
            if (MapStore.make().detectWall(nextPos1)) {
                this.direction = this.intendDir;
                this.setPosition(nextPos1);
                return;
            }
        }

        //detect current movement
        Point nextPos2;
        switch (direction) {
            case Direction.UP:
                nextPos2 = new Point(this.position.x, this.position.y - this.velocity.y);
                break;
            case Direction.RIGHT:
                nextPos2 = new Point(this.position.x + this.velocity.x, this.position.y);
                break;
            case Direction.DOWN:
                nextPos2 = new Point(this.position.x, this.position.y + this.velocity.y);
                break;
            case Direction.LEFT:
                nextPos2 = new Point(this.position.x - this.velocity.x, this.position.y);
                break;
            default:
                return;
        }
        if (MapStore.make().detectWall(nextPos2)) {
            this.setPosition(nextPos2);
            return;
        }
        int dimX = MapStore.make().getDims().x;
        if (nextPos2.x >= (dimX - 32) && nextPos2.x < dimX) {
            this.setPosition(nextPos2);
        } else if (nextPos2.x >= dimX) {
            this.setPosition(new Point(0, nextPos2.y));
        } else if (nextPos2.x <= 0) {
            this.setPosition(new Point(MapStore.make().getDims().x - 32, nextPos2.y));
        }
    }

}
