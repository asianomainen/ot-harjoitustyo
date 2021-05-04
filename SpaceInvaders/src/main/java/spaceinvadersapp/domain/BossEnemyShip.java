package spaceinvadersapp.domain;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 * Class for making a boss enemy ship.
 * Extends abstract class Shape.
 *
 * @see spaceinvadersapp.domain.Shape
 */

public class BossEnemyShip extends Shape {

    public BossEnemyShip(int x, int y, Color color) {
        super(new Polygon(-20, 10, -20, -5, 0, -10, 20, -5, 20, 10, 15, 5, 10, 10, 5, 5, 0, 10, -5, 5, -10, 10, -15, 5, -20, 10), color, x, y);
    }
}
