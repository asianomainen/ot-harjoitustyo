package spaceinvadersapp.domain;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.util.Objects;

public abstract class Shape {
    private final Polygon shape;

    public Shape(Polygon polygon, Color color, int x, int y) {
        this.shape = polygon;
        this.shape.setFill(color);
        this.shape.setTranslateX(x);
        this.shape.setTranslateY(y);
    }

    public Polygon getShape() {
        return this.shape;
    }

    public void moveLeft() {
        this.shape.setTranslateX(this.shape.getTranslateX() - 3.5);
    }

    public void moveRight() {
        this.shape.setTranslateX(this.shape.getTranslateX() + 3.5);
    }

    public double getX() {
        return this.shape.getTranslateX();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Shape)) return false;
        Shape shape1 = (Shape) o;
        return Objects.equals(shape, shape1.shape);
    }
}
