package spaceinvaders.domain;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class PlayerShip extends Shape {

    public PlayerShip(int x, int y) {
        //super (new Polygon(100, 70, 100, 60, 110, 50, 120, 50, 123, 40, 127, 40, 130, 50, 140, 50, 150, 60, 150, 70), x, y);
        super (new Polygon(-20, 12, -20, 0, -17, -4, -3, -4, -1, -10, 1, -10, 3, -4, 17, -4, 20, 0, 20, 12), Color.ORANGERED, x, y);
    }
}
