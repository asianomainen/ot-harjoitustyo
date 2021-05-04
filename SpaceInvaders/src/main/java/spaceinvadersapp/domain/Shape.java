package spaceinvadersapp.domain;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import java.util.Objects;

/**
 * Abstract class for all shapes in the game.
 */

public abstract class Shape {
    private final Polygon shape;
    private boolean alive;

    /**
     * Creates a new shape.
     *
     * @param   polygon   shape of the object as a polygon
     * @param   color     color of the object
     * @param   x         x coordinate of the object
     * @param   y         y coordinate of the object
     */

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
     * Moves the ship to the left.
     * Decreases the current X coordinate value of the object by 3.5
     */

    public void moveLeft() {
        this.shape.setTranslateX(this.shape.getTranslateX() - 3.5);
    }

    /**
     * Moves the ship to the right.
     * Increases the current X coordinate value of the object by 3.5
     */

    public void moveRight() {
        this.shape.setTranslateX(this.shape.getTranslateX() + 3.5);
    }

    /**
     * Moves player bullets up.
     * Decreases the current Y coordinate value of the object by 10.0
     */

    public void moveUp() {
        this.shape.setTranslateY(this.shape.getTranslateY() - 10);
    }

    /**
     * Moves enemy bullets down.
     * Increases the current Y coordinate value of the object by 7.5
     */

    public void moveDown() {
        this.shape.setTranslateY(this.shape.getTranslateY() + 7.5);
    }

    /**
     * For checking if object is out of bounds (out of the application window size).
     *
     * Object is out of the window size if:
     * Value of Y coordinate is below 0
     * Value of Y coordinate is above 720
     * Value of X coordinate is below 0
     * Value of X coordinate is above 960
     *
     * @return true if the object is out of bounds, false if not
     */

    public boolean outOfBounds() {
        return this.shape.getTranslateY() < 0 || this.shape.getTranslateY() > 720 || this.shape.getTranslateX() < 0 || this.shape.getTranslateX() > 960;
    }

    /**
     * Enables an object to be set as alive (true) or dead (false).
     *
     * @param   value   boolean value for alive (true) or dead (false)
     */

    public void setAlive(boolean value) {
        this.alive = value;
    }

    /**
     * For checking if an object is alive (has not been hit by bullet).
     *
     * @return true if the object is alive, false if not
     */

    public boolean isAlive() {
        return this.alive;
    }

    /**
     * Checks if two objects are colliding.
     *
     * @param   target   the object to compare with
     *
     * @return true if the objects are colliding, false if not
     */

    public boolean collision(Shape target) {
        return this.shape.getBoundsInParent().intersects(target.getShape().getBoundsInParent());
    }

    /**
     * Compares two objects to determine if they are equal.
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