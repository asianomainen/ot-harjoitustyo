package spaceinvadersapp.domain;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 * class for making enemy bullets
 * extends abstract class Shape
 *
 * @see spaceinvadersapp.domain.Shape
 */

public class EnemyBullet extends Shape {

    public EnemyBullet(int x, int y, Color color) {
        super(new Polygon(-1, 1, -1, 8, 0, 13, 1, 8, 1, 1, 0, 2), color, x, y);
    }
}
