package edu.rice.comp504.model.map;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MapObject {
    public static final int EMPTY = 0;
    public static final int WALL = 1;
    public static final int DOT = 2;
    public static final int PINKY = 3;
    public static final int CLYDE = 4;
    public static final int BLINKY = 5;
    public static final int INKY = 6;
    public static final int PILL = 7;
    public static final int PACMAN = 8;
    public static final int FRUIT = 9;
    public static final int FENCE = 10;

    public static final Map<Integer, String> iObject = new HashMap<>() {
        {
            String[] s = {"empty", "wall", "dot", "pinky", "clyde", "blinky", "inky", "pill", "pacman", "fruit", "fence"};
            for (int i = 0; i <= 10; i++) {
                put(i, s[i]);
            }
        }
    };
}

