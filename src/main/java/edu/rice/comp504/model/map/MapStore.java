package edu.rice.comp504.model.map;


import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class MapStore {
    private static MapStore ONLY;

    public int getTileSize() {
        return tileSize;
    }

    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }

    private int tileSize;
    private ArrayList<int[][]> maps = new ArrayList<>();

    private int[][] currentMap;

    private MapStore() {
        this.tileSize = 36;
//
//        int[][] map1 = {
//                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
//                {1, 7, 2, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 7, 1},
//                {1, 2, 1, 1, 2, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 2, 1, 1, 2, 1},
//                {1, 2, 1, 1, 2, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 2, 1, 1, 2, 1},
//                {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
//                {1, 2, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 2, 1},
//                {1, 2, 2, 2, 2, 1, 2, 2, 2, 1, 1, 2, 2, 2, 1, 2, 2, 2, 2, 1},
//                {1, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 1},
//                {0, 0, 0, 1, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 1, 0, 0, 0},
//                {0, 0, 0, 1, 2, 1, 2, 1, 10, 10, 10, 10, 1, 2, 1, 2, 1, 0, 0, 0},
//                {1, 1, 1, 1, 2, 1, 2, 1, 6, 3, 5, 4, 1, 2, 1, 2, 1, 1, 1, 1},
//                {0, 0, 0, 0, 2, 2, 2, 1, 10, 10, 10, 10, 1, 2, 2, 2, 0, 0, 0, 0},
//                {1, 1, 1, 1, 2, 1, 2, 1, 10, 10, 10, 10, 1, 2, 1, 2, 1, 1, 1, 1},
//                {0, 0, 0, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 0, 0, 0},
//                {0, 0, 0, 1, 2, 1, 2, 0, 0, 8, 7, 0, 0, 2, 1, 2, 1, 0, 0, 0},
//                {1, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 1},
//                {1, 2, 2, 2, 2, 1, 2, 2, 2, 1, 1, 2, 2, 2, 1, 2, 2, 2, 2, 1},
//                {1, 2, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 2, 1},
//                {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
//                {1, 2, 1, 1, 2, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 2, 1, 1, 2, 1},
//                {1, 2, 1, 1, 2, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 2, 1, 1, 2, 1},
//                {1, 7, 2, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 7, 1},
//                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
//        };

//        int[][] map1 = {
//                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
//                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
//                {1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1},
//                {1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1},
//                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
//                {1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1},
//                {1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1},
//                {1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1},
//                {0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0},
//                {0, 0, 0, 1, 0, 1, 0, 1, 10, 10, 10, 10, 1, 0, 1, 0, 1, 0, 0, 0},
//                {1, 1, 1, 1, 0, 1, 0, 1, 6, 3, 5, 4, 1, 0, 1, 0, 1, 1, 1, 1},
//                {0, 0, 0, 0, 0, 0, 0, 1, 10, 10, 10, 10, 1, 0, 0, 0, 0, 0, 0, 0},
//                {1, 1, 1, 1, 0, 1, 0, 1, 10, 10, 10, 10, 1, 0, 1, 0, 1, 1, 1, 1},
//                {0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0},
//                {0, 0, 0, 1, 0, 1, 0, 0, 0, 8, 0, 0, 0, 2, 1, 0, 1, 0, 0, 0},
//                {1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1},
//                {1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1},
//                {1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1},
//                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
//                {1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1},
//                {1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1},
//                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
//                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
//        };

        // first map with T
        // 25 * 25
        // 240 small dots
        // 4 large dots
        int[][] map1 = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 7, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 7, 1},
                {1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1},
                {1, 2, 1, 2, 2, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 2, 2, 2, 1, 2, 1},
                {1, 2, 1, 2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 2, 1, 2, 1},
                {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
                {1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1},
                {1, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 1, 1, 1, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 1},
                {1, 2, 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 2, 1},
                {1, 2, 1, 2, 2, 2, 2, 2, 2, 1, 2, 1, 1, 1, 2, 1, 2, 2, 2, 2, 2, 2, 1, 2, 1},
                {1, 2, 2, 2, 1, 2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 2, 1, 2, 2, 2, 1},
                {1, 1, 1, 2, 1, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 1, 2, 1, 1, 1},
                {0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 8, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1},
                {1, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 1},
                {1, 2, 1, 2, 1, 2, 1, 1, 2, 1, 0, 1, 10, 1, 0, 1, 2, 1, 1, 2, 1, 2, 1, 2, 1},
                {1, 2, 1, 2, 1, 2, 1, 1, 2, 1, 0, 1, 6, 1, 0, 1, 2, 1, 1, 2, 1, 2, 1, 2, 1},
                {1, 2, 1, 2, 1, 2, 2, 2, 2, 0, 0, 1, 5, 1, 0, 0, 2, 2, 2, 2, 1, 2, 1, 2, 1},
                {1, 2, 0, 2, 1, 1, 1, 1, 1, 1, 0, 1, 4, 1, 0, 1, 1, 1, 1, 1, 1, 2, 0, 2, 1},
                {1, 2, 1, 2, 2, 2, 2, 2, 2, 2, 0, 1, 3, 1, 0, 2, 2, 2, 2, 2, 2, 2, 1, 2, 1},
                {1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 2, 1, 2, 1},
                {1, 2, 1, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 2, 1, 2, 1},
                {1, 2, 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 2, 1},
                {1, 7, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 7, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };

        // second map with TEAM
        // 25 * 25
        // 240 small dots
        // 4 large dots
        int[][] map2 = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 7, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 7, 1},
                {1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1},
                {1, 2, 2, 2, 1, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 1, 2, 1, 2, 2, 2, 2, 2, 1},
                {1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 2, 1},
                {1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 2, 2, 2, 2, 2, 2, 1, 2, 1, 2, 2, 2, 2, 2, 1},
                {1, 2, 1, 2, 1, 2, 1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1},
                {1, 2, 1, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
                {1, 2, 1, 2, 1, 2, 1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1},
                {1, 2, 1, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 1, 2, 1, 2, 1},
                {1, 2, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 2, 2, 1},
                {1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 0, 1, 1, 1, 0, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1},
                {1, 2, 2, 2, 2, 2, 2, 2, 1, 1, 0, 0, 0, 0, 0, 1, 1, 2, 2, 2, 2, 2, 2, 2, 1},
                {1, 2, 1, 1, 1, 1, 1, 2, 0, 0, 0, 1, 10, 1, 0, 0, 0, 2, 1, 1, 1, 1, 1, 2, 1},
                {1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 0, 1, 6, 1, 0, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1},
                {1, 2, 2, 2, 2, 2, 2, 2, 1, 1, 0, 1, 5, 1, 0, 1, 1, 2, 2, 2, 1, 2, 2, 2, 1},
                {1, 2, 1, 1, 1, 1, 1, 2, 2, 2, 0, 1, 4, 1, 0, 2, 2, 2, 1, 2, 2, 2, 1, 2, 1},
                {1, 2, 1, 10, 10, 10, 1, 2, 1, 1, 0, 1, 3, 1, 0, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1},
                {1, 2, 1, 1, 1, 1, 1, 2, 2, 2, 0, 1, 1, 1, 0, 2, 2, 2, 1, 2, 1, 2, 1, 2, 1},
                {1, 2, 1, 2, 0, 2, 1, 2, 1, 1, 0, 0, 0, 0, 0, 1, 1, 2, 1, 2, 0, 2, 1, 2, 1},
                {1, 2, 1, 2, 1, 2, 1, 2, 1, 1, 2, 1, 1, 1, 2, 1, 1, 2, 1, 2, 1, 2, 1, 2, 1},
                {1, 7, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 7, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };

        this.maps.add(map1);
        this.maps.add(map2);
//        this.maps.add(map3);
//        this.currentMap = maps.get(0).clone();
    }

    /**
     * Get dimensions of the canvas.
     *
     * @return canvas dimensions
     */
    public Point getDims() {
        return new Point(currentMap[0].length * tileSize, currentMap.length * tileSize);
    }

    /**
     * Counts current dots.
     *
     * @param map current map
     * @return dot count
     */
    private int dotCount(int[][] map) {
        int numDots = 0;
        for (int[] ints : map) {
            for (int j : ints) {
                numDots += j == 1 ? 1 : 0;
            }
        }
        return numDots;
    }

    /**
     * Make a map store.
     *
     * @return a map store.
     */
    public static MapStore make() {
        if (ONLY == null) {
            ONLY = new MapStore();
        }
        return ONLY;
    }

    /**
     * Get current map.
     *
     * @return current map.
     */
    public int[][] getCurrentMap() {
        return currentMap;
    }

    /**
     * Ger current map based on level.
     *
     * @param level current level.
     * @return desired map.
     */
    public int[][] getMap(int level) {
//        this.currentMap = maps.get(level-1).clone();
        //System.out.println(level);
        if (level > 0 && level <= maps.size()) {
            this.currentMap = new int[maps.get(level - 1).length][maps.get(level - 1)[0].length];
            for (int i = 0; i < maps.get(level - 1).length; i++) {
                this.currentMap[i] = maps.get(level - 1)[i].clone();
            }
            return this.currentMap;
        } else {
            System.out.println("level error");
            return new int[][]{{}};
        }
    }

    private void generateObjects() {
        for (int[] ints : currentMap) {
            for (int j = 0; j < currentMap.length; j++) {
                //TODO: generate treasures in the pacman store
                switch (ints[j]) {
                    case 10:
                        break;
                    case 11:
                        break;
                    case 12:
                        break;
                    default:
                }
            }
        }
    }

    /**
     * Detect whether the next position is wall.
     *
     * @param nextPos next position.
     * @return whether the next position is wall.
     */
    public boolean detectWall(Point nextPos) {
        int pX = nextPos.x; //position of x
        int pY = nextPos.y; //position of y
        int rX = (pX % tileSize == 0) ? 0 : 1; //range of x
        int rY = (pY % tileSize == 0) ? 0 : 1; //range of y

        pX = pX / tileSize;
        pY = pY / tileSize;

        for (int i = pX; i <= pX + rX; i++) {
            for (int j = pY; j <= pY + rY; j++) {
                if (i < 0 || i >= currentMap[0].length) {
                    return false;
                }
                if (currentMap[j][i] == MapObject.WALL) {
                    return false; //hit wall
                }
            }
        }
        return true;
    }

    /**
     * Detect whether the current position is dot.
     *
     * @param pos current position.
     * @return whether the current position is dot.
     */
    public int detectDot(Point pos) {
        int pX = (int) (Math.floor(pos.getX() / tileSize)); //position of x
        int pY = (int) (Math.floor(pos.getY() / tileSize)); //position of y
        pX = pX == -1 ? 0 : pX;
        switch (currentMap[pY][pX]) {
            case MapObject.DOT:
                currentMap[pY][pX] = 0;
                return 10;
            case MapObject.PILL:
                currentMap[pY][pX] = 0;
                return 50;
            case MapObject.FRUIT:
                currentMap[pY][pX] = 0;
                return 100;
            default:
                return 0;
        }
    }
    /**
     * generate Fruit at random location.
     */
    public void generateFruit() {
        Random random = new Random();
        int randX = random.nextInt(currentMap.length);
        int randY = random.nextInt(currentMap[0].length);
        while (currentMap[randX][randY] != 0) {
            randX = random.nextInt(currentMap.length);
            randY = random.nextInt(currentMap[0].length);
        }
        currentMap[randX][randY] = 9;
//        System.out.println(randX + " " + randY);
    }
}
