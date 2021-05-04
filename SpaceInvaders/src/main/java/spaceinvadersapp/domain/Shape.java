package spaceinvadersapp.domain;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import java.util.Objects;

/**
 * Abstract class for all shapes in the game
 */

public abstract class Shape {
    private final Polygon shape;
    private boolean alive;

    public Shape(Polygon polygon, Color color, int x, int y) {
        this.shape = polygon;
        this.shape.setFill(color);
        this.shape.setTranslateX(x);
        this.shape.setTranslateY(y);
        this.alive = true;
    }

    public Polygon getShape() {
        return this.shape;
    }

    /**
     * moves the ship to the left
     */

    public void moveLeft() {
        this.shape.setTranslateX(this.shape.getTranslateX() - 3.5);
    }

    /**
     * moves the ship to the right
     */

    public void moveRight() {
        this.shape.setTranslateX(this.shape.getTranslateX() + 3.5);
    }

    /**
     * moves player bullets up
     */

    public void moveUp() {
        this.shape.setTranslateY(this.shape.getTranslateY() - 10);
    }

    /**
     * moves enemy bullets down
     */

    public void moveDown() {
        this.shape.setTranslateY(this.shape.getTranslateY() + 7.5);
    }

    /**
     * for checking if object is out of bounds (out of the application window size)
     *
     * @return true if the object is out of bounds, false if not
     */

    public boolean outOfBounds() {
        return this.shape.getTranslateY() < 0 || this.shape.getTranslateY() > 720 || this.shape.getTranslateX() < 0 || this.shape.getTranslateX() > 960;
    }

    /**
     * enables an object to be set as alive (true) or dead (false)
     *
     * @param   value   boolean value for alive (true) or dead (false)
     */

    public void setAlive(boolean value) {
        this.alive = value;
    }

    public boolean isAlive() {
        return this.alive;
    }

    /**
     * checks if two objects are colliding
     *
     * @param   target   the object to compare with
     *
     * @return true if the objects are colliding, false if not
     */

    public boolean collision(Shape target) {
        return this.shape.getBoundsInParent().intersects(target.getShape().getBoundsInParent());
    }

    /**
     * compares two objects to determine if they are equal
     *
     * @param   obj   the object to compare with
     *
     * @return true if the objects are equal, false if not
     */

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Shape)) {
            return false;
        }
        Shape shape1 = (Shape) obj;
        return Objects.equals(this.shape, shape1.shape);
    }
}