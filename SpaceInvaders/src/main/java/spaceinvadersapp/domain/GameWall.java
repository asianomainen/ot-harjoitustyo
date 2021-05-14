package spaceinvadersapp.domain;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class GameWall extends Shape {
    private int lives;

    public GameWall(int x, int y, Color color) {
        super(new Polygon(-50, 10, -50, -10, 50, -10, 50, 10), color, x, y);
        this.lives = 10;
    }

    public void die() {
        this.lives--;
    }

    public int getLives() {
        return this.lives;
    }
}