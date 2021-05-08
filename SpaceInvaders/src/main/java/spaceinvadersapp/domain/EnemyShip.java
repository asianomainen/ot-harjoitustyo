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

    public EnemyShip(int x, int y, Color color) {
        super(new Polygon(-20, 0, -20, -15, 0, -12, 20, -15, 20, 0, 15, 7, 10, 0, 5, 0, 0, 15, -5, 0, -10, 0, -15, 7), color, x, y);
    }

    public void move(int counter) {
        if (counter == 0) {
            changeDirection();
        }

        if (!direction) {
            super.moveLeft();
        } else {
            super.moveRight();
        }
    }

    public void changeDirection() {
        this.direction = !this.direction;
    }
}