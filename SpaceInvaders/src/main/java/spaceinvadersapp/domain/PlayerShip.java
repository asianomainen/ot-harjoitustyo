package spaceinvadersapp.domain;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 * class for making a player ship
 * extends abstract class Shape
 *
 * @see spaceinvadersapp.domain.Shape
 */

public class PlayerShip extends Shape {

    public PlayerShip(int x, int y, Color color) {
        super(new Polygon(-20, 12, -20, 0, -17, -4, -3, -4, -1, -10, 1, -10, 3, -4, 17, -4, 20, 0, 20, 12), color, x, y);
    }

    @Override
    public void moveLeft() {
        if (this.getShape().getTranslateX() < 20) {
            return;
        }

        super.moveLeft();
    }

    @Override
    public void moveRight() {
<<<<<<< HEAD
        if (this.getShape().getTranslateX() > 940) {
=======
        if (this.getShape().getTranslateX() > 94git 0) {
>>>>>>> 5ed42312498614d8f488b7aa4cc8b7f25026df22
            return;
        }

        super.moveRight();
    }
}
