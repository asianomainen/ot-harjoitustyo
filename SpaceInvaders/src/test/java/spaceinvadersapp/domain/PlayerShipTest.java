package spaceinvadersapp.domain;

import javafx.scene.paint.Color;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class PlayerShipTest {

    @Test
    public void equalWhenSameObject() {
        PlayerShip ship1 = new PlayerShip(15, 15, Color.BEIGE);
        PlayerShip ship2 = ship1;
        assertEquals(ship1, ship2);
    }

    @Test
    public void notEqualWhenDifferentType() {
        PlayerShip ship = new PlayerShip(15, 15, Color.BEIGE);
        Object object = new Object();
        assertNotEquals(ship, object);
    }

    @Test
    public void notEqualWhenDifferentShape() {
        PlayerShip ship = new PlayerShip(15, 15, Color.BEIGE);
        PlayerBullet bullet = new PlayerBullet(15, 15, Color.BEIGE);
        assertNotEquals(ship.getShape(), bullet.getShape());
    }

    @Test
    public void moveLeftWorks() {
        PlayerShip ship = new PlayerShip(15, 15, Color.BEIGE);
        ship.moveLeft();
        assertEquals(11.5, ship.getShape().getTranslateX(), 0);
    }

    @Test
    public void moveRightWorks() {
        PlayerShip ship = new PlayerShip(15, 15, Color.BEIGE);
        ship.moveRight();
        assertEquals(18.5, ship.getShape().getTranslateX(), 0);
    }

    @Test
    public void moveUpWorks() {
        PlayerShip ship = new PlayerShip(15, 15, Color.BEIGE);
        ship.moveUp();
        assertEquals(5, ship.getShape().getTranslateY(), 0);
    }

    @Test
    public void returnsTrueWhenOutOfBounds() {
        PlayerShip ship = new PlayerShip(15, -5, Color.BEIGE);
        assertTrue(ship.outOfBounds());
    }

    @Test
    public void returnsFalseWhenNotOutOfBounds() {
        PlayerShip ship = new PlayerShip(15, 5, Color.BEIGE);
        assertFalse(ship.outOfBounds());
    }

    @Test
    public void returnsTrueWhenAlive() {
        PlayerShip ship = new PlayerShip(15, 5, Color.BEIGE);
        assertTrue(ship.isAlive());
    }

    @Test
    public void returnsFalseWhenNotAlive() {
        PlayerShip ship = new PlayerShip(15, 5, Color.BEIGE);
        ship.setAlive(false);
        assertFalse(ship.isAlive());
    }

    @Test
    public void returnsTrueWhenColliding() {
        PlayerShip ship1 = new PlayerShip(15, 5, Color.BEIGE);
        PlayerShip ship2 = new PlayerShip(15, 5, Color.BEIGE);
        assertTrue(ship1.collision(ship2));
    }

    @Test
    public void returnsFalseWhenNotColliding() {
        PlayerShip ship1 = new PlayerShip(15, 5, Color.BEIGE);
        PlayerShip ship2 = new PlayerShip(115, 105, Color.BEIGE);
        assertFalse(ship1.collision(ship2));
    }
}