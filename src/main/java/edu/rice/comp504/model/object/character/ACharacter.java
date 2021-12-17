package edu.rice.comp504.model.object.character;

import edu.rice.comp504.model.object.AObject;
import edu.rice.comp504.model.strategy.IUpdateStrategy;

import java.awt.*;
import java.beans.PropertyChangeListener;

public abstract class ACharacter extends AObject implements PropertyChangeListener {
    protected IUpdateStrategy strategy;
    protected int direction;
    protected Point velocity;
    protected Point origin;
    protected boolean dead;

    /**
     * Constructor.
     * @param name current name.
     * @param position current strategy.
     * @param s current strategy.
     * @param direction current direction.
     */
    public ACharacter(String name, Point position, IUpdateStrategy s, int direction, Point velocity) {
        super(name, position);
        strategy = s;
        this.direction = direction;
        dead = false;
        this.velocity = velocity;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Point getOrigin() {
        return origin;
    }

    public void setOrigin(Point origin) {
        this.origin = origin;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public Point getVelocity() {
        return velocity;
    }

    public void setVelocity(Point velocity) {
        this.velocity = velocity;
    }

    public abstract boolean detectNoCollision(int direction);

    public abstract void updatePos();

    public IUpdateStrategy getStrategy() {
        return this.strategy;
    }

    public void setStrategy(IUpdateStrategy strategy) {
        this.strategy = strategy;
    }

}
