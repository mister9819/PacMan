package edu.rice.comp504.model.object;

import edu.rice.comp504.model.object.character.Direction;
import edu.rice.comp504.model.object.character.Ghost;
import edu.rice.comp504.model.object.character.Pacman;
import edu.rice.comp504.model.object.treasure.Dot;
import edu.rice.comp504.model.object.treasure.Fruit;
import edu.rice.comp504.model.object.treasure.Pill;
import edu.rice.comp504.model.strategy.StrategyFactory;

import java.awt.*;

/**
 ** Object factory to generate various type of objects.
 */
public class ObjectFac implements IObjectFac {

    private static ObjectFac ONLY;

    private ObjectFac() {
    }

    /**
     * Make an Object factory.
     * @return the desired object factory.
     */
    public static ObjectFac make() {
        if (ONLY == null) {
            ONLY = new ObjectFac();
        }
        return ONLY;
    }

    @Override
    public AObject make(String type, int positionX, int positionY) {
        Point position = new Point(positionX, positionY);
        switch (type) {
//            case "dot":
//                return new Dot(position);
//            case "pill":
//                return new Pill(position);
//            case "fruit":
//                return new Fruit(position);
            case "pacman":
                return new Pacman(position, StrategyFactory.make().make("pacman"), 3, Direction.RIGHT);  // todo:  load params from config file
            case "pinky":
                return new Ghost(position, StrategyFactory.make().make("chase"), Direction.UP, "pinky");
            case "clyde":
                return new Ghost(position, StrategyFactory.make().make("random"), Direction.UP, "clyde");
            case "blinky":
                return new Ghost(position, StrategyFactory.make().make("chase"), Direction.UP, "blinky");
            case "inky":
                return new Ghost(position, StrategyFactory.make().make("chase"), Direction.UP, "inky");
            default:
                throw new Error("treasure type error");
        }
    }
}
