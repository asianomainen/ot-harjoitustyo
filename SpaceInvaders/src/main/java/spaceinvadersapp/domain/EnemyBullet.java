package spaceinvadersapp.domain;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 * Class for making enemy bullets
 */

public class EnemyBullet extends Shape {

    public EnemyBullet(int x, int y, Color color) {
        super(new Polygon(-1, 1, -1, 8, 0, 13, 1, 8, 1, 1, 0, 2), color, x, y);
    }
}
