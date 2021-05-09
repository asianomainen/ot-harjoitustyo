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

    public void die() {
        this.lives--;
    }

    public boolean isImmortal() {
        return this.immortal;
    }

    public void setImmortal(boolean value) {
        this.immortal = value;
    }

    public int getLives() {
        return this.lives;
    }
}
