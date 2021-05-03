package spaceinvadersapp.domain;

import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polygon;
import javafx.geometry.Point2D;

import java.util.Objects;

public abstract class Shape {
    private final Polygon shape;
    private boolean alive;
    private Point2D movement;

    public Shape(Polygon polygon, Color color, int x, int y) {
        this.shape = polygon;
        this.shape.setFill(color);
        this.shape.setTranslateX(x);
        this.shape.setTranslateY(y);
        this.alive = true;
        this.movement = new Point2D(0, 0);
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

    public void moveUp() {
        this.shape.setTranslateY(this.shape.getTranslateY() - 5);
    }

    public boolean outOfBounds() {
        return this.shape.getTranslateY() < 0;
    }

    public void setAlive(boolean value) {
        this.alive = value;
    }

    public boolean getAlive() {
        return !this.alive;
    }

    public Point2D getMovement() {
        return this.movement;
    }

    public boolean collision(Shape target) {
        return this.shape.getBoundsInParent().intersects(target.getShape().getBoundsInParent());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Shape)) {
            return false;
        }
        Shape shape1 = (Shape) o;
        return Objects.equals(shape, shape1.shape);
    }
}
