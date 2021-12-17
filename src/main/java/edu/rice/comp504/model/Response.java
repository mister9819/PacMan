package edu.rice.comp504.model;

import edu.rice.comp504.model.object.AObject;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class Response {
    int x;
    int y;
    int score;
    boolean isGameOver;
    int lives;
    PropertyChangeListener[] characters;
    ArrayList<AObject> treasures;
    int[][] map;

    /**
     * Response constructor.
     * @param x x position
     * @param y y position
     * @param score current score
     * @param isGameOver whether the game is over
     * @param lives number of lives
     * @param characters all characters in the game
     * @param treasures all treasures in the game
     * @param map current map
     */
    public Response(int x, int y, int score, boolean isGameOver, int lives, PropertyChangeListener[] characters, ArrayList<AObject> treasures, int[][] map) {
        this.x = x;
        this.y = y;
        this.score = score;
        this.isGameOver = isGameOver;
        this.lives = lives;
        this.characters = characters;
        this.treasures = treasures;
        this.map = map;
    }
}
