package spaceinvadersapp.domain;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 * Class for making an enemy ship.
 * Extends abstract class Shape.
 *
 * @see spaceinvadersapp.domain.Shape
 */

public class EnemyShip extends Shape {
    private boolean direction = false;

    /**
     * Creates a new enemy.
     */

    public EnemyShip(int x, int y, Color color) {
        super(new Polygon(-20, 0, -20, -15, 0, -12, 20, -15, 20, 0, 15, 7, 10, 0, 5, 0, 0, 15, -5, 0, -10, 0, -15, 7), color, x, y);
    }

    /**
     * Moves the enemies left (false) or right (true).
     *
     * Counter keeps track of how many times the enemy has moved.
     * When enemy direction is to be changed, the counter value
     * must be 0.
     *
     * @param   counter   amount of times the enemy has moved
     */

    public void move(int counter) {
        if (counter == 0) {
            changeDirection();
            moveDown();
            return;
        }

        if (!direction) {
            moveLeft();
        } else {
            moveRight();
        }
    }

    /**
     * Overrides the moveLeft() method of the abstract class Shape
     * to move a larger amount.
     */

    @Override
    public void moveLeft() {
        this.getShape().setTranslateX(this.getShape().getTranslateX() - 12.0);
    }

    /**
     * Overrides the moveRight() method of the abstract class Shape
     * to move a larger amount.
     */

    @Override
    public void moveRight() {
        this.getShape().setTranslateX(this.getShape().getTranslateX() + 12.0);
    }

    /**
     * Overrides the moveDown() method of the abstract class Shape
     * to move a larger amount.
     */

    @Override
    public void moveDown() {
        this.getShape().setTranslateY(this.getShape().getTranslateY() + 45);
    }

    /**
     * Changes the direction of movement for enemies.
     * False: move left. True: move right.
     */

    public void changeDirection() {
        this.direction = !this.direction;
    }

    public boolean getDirection() {
        return direction;
    }
}