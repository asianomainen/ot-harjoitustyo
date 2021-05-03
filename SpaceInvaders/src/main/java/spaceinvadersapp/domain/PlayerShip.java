package spaceinvadersapp.domain;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

/**
 * Class for making a player ship
 */

public class PlayerShip extends Shape {

    public PlayerShip(int x, int y, Color color) {
        super(new Polygon(-20, 12, -20, 0, -17, -4, -3, -4, -1, -10, 1, -10, 3, -4, 17, -4, 20, 0, 20, 12), color, x, y);
    }
}
