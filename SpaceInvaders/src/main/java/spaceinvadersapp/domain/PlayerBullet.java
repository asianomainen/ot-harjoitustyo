package spaceinvadersapp.domain;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class PlayerBullet extends Shape {

    public PlayerBullet(int x, int y, Color color) {
        super(new Polygon(-1, -1, -1, -8, 0, -13, 1, -8, 1, -1, 0, -2), color, x, y);
    }
}
