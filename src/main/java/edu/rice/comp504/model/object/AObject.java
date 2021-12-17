package edu.rice.comp504.model.object;

import java.awt.Point;

public abstract class AObject {
    protected Point position;
    protected String status;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }


    protected AObject(String name, Point pos) {
        position = pos;
        this.status = "n";
        this.name = name;
    }
}
