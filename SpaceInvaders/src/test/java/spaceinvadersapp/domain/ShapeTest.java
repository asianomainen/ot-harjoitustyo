
package spaceinvadersapp.domain;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShapeTest {

    @Test
    public void equalWhenSameShape() {
        Polygon polygon = new Polygon(-2, -2, -2, 2, 2, 2, -2, 2);
        Shape shape1 = new Shape(polygon, Color.BEIGE, 15, 15);
        Shape shape2 = new Shape(polygon, Color.BEIGE, 15, 15);
        assertEquals(shape1, shape2);
    }

    @Test
    public void notEqualWhenDifferentShape() {
        Polygon polygon1 = new Polygon(-2, -2, -2, 2, 2, 2, -2, 2);
        Polygon polygon2 = new Polygon(-3, -3, -3, 3, 3, 3, -3, 3);
        Shape shape1 = new Shape(polygon1, Color.BEIGE, 15, 15);
        Shape shape2 = new Shape(polygon2, Color.BEIGE, 15, 15);
        assertNotEquals(shape1, shape2);
    }

    @Test
    public void notEqualWhenDifferentType() {
        Shape shape = new Shape(new Polygon(-2, -2, -2, 2, 2, 2, -2, 2), Color.BEIGE, 15, 15);
        Object object = new Object();
        assertNotEquals(shape, object);
    }

    @Test
    public void equalPolygonShape() {
        Polygon polygon = new Polygon(-2, -2, -2, 2, 2, 2, -2, 2);
        Shape shape1 = new Shape(polygon, Color.BEIGE, 15, 15);
        Shape shape2 = new Shape(polygon, Color.BEIGE, 15, 15);
        assertEquals(shape1.getShape(), shape2.getShape());
    }

    @Test
    public void moveLeftWorks() {
        Shape shape = new Shape(new Polygon(-2, -2, -2, 2, 2, 2, -2, 2), Color.BEIGE, 15, 15);
        shape.moveLeft();
        assertEquals(11.5, shape.getX(), 0);
    }

    @Test
    public void moveRightWorks() {
        Shape shape = new Shape(new Polygon(-2, -2, -2, 2, 2, 2, -2, 2), Color.BEIGE, 15, 15);
        shape.moveRight();
        assertEquals(18.5, shape.getX(), 0);
    }
}