package spaceinvadersapp.domain;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 * Class for making a player ship.
 * Extends abstract class Shape.
 *
 * @see spaceinvadersapp.domain.Shape
 */

public class PlayerShip extends Shape {
    private int lives;
    private boolean immortal;

    public PlayerShip(int x, int y, Color color) {
        super(new Polygon(-20, 12, -20, 0, -17, -4, -3, -4, -1, -10, 1, -10, 3, -4, 17, -4, 20, 0, 20, 12), color, x, y);
        this.lives = 3;
        this.immortal = false;
    }

    /**
     * Overrides the moveLeft() method of the abstract class Shape
     * to check if the player's ship is too close to the edge.
     * Will not move the ship if the current X coordinate value
     * is less than 20.
     */

    @Override
    public void moveLeft() {
        if (this.getShape().getTranslateX() <= 20) {
            this.getShape().setTranslateX(20);
            return;
        }

        super.moveLeft();
    }

    /**
     * Overrides the moveRight() method of the abstract class Shape
     * to check if the player's ship is too close to the edge.
     * Will not move the ship if the current X coordinate value
     * is more than 940.
     */

    @Override
    public void moveRight() {
        if (this.getShape().getTranslateX() >= 940) {
            this.getShape().setTranslateX(940);
            return;
        }

        super.moveRight();
    }

    /**
     * Reduces player lives by 1.
     */

    public void die() {
        this.lives--;
    }

    /**
     * For checking if an object is immortal
     * (has been hit by a bullet in the previous 3 seconds).
     *
     * @return true if the object is immortal, false if not
     */

    public boolean isImmortal() {
        return this.immortal;
    }

    /**
     * Enables an object to be set as immortal (true) or vulnerable (false).
     *
     * @param   value   boolean value for immortal (true) or vulnerable (false)
     */

    public void setImmortal(boolean value) {
        this.immortal = value;
    }

    /**
     * Returns amount of lives the player has left.
     *
     * @return amount of lives player has left (1-3)
     */

    public int getLives() {
        return this.lives;
    }

    /**
     * Sets the players lives to the value given as a parameter.
     *
     * @param   lives   int value of the lives
     */

    public void setLives(int lives) {
        this.lives = lives;
    }
}
