package spaceinvadersapp.domain;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 * Class for making a wall in the game.
 * Extends abstract class Shape.
 *
 * @see spaceinvadersapp.domain.Shape
 */

public class GameWall extends Shape {
    private int lives;

    /**
     * Creates a new wall and assigns 10 lives to it.
     */

    public GameWall(int x, int y, Color color) {
        super(new Polygon(-50, 10, -50, -10, 50, -10, 50, 10), color, x, y);
        this.lives = 10;
    }

    /**
     * Removes a life from the wall.
     */

    public void die() {
        this.lives--;
    }

    /**
     * Returns the amount of lives the wall has.
     */

    public int getLives() {
        return this.lives;
    }
}