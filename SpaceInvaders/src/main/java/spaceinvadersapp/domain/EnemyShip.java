package spaceinvadersapp.domain;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class EnemyShip extends Shape {

    public EnemyShip(int x, int y, Color color) {
        super(new Polygon(-20, 0, -20, -15, 0, -12, 20, -15, 20, 0, 15, 7, 10, 0, 5, 0, 0, 15, -5, 0, -10, 0, -15, 7, -20, 0), color, x, y);
    }
}
