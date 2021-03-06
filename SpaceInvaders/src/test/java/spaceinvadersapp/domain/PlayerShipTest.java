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
        PlayerShip ship = new PlayerShip(50, 15, Color.BEIGE);
        ship.moveLeft();
        assertEquals(46.5, ship.getShape().getTranslateX(), 0);
    }

    @Test
    public void doesNotMoveLeftIfTooCloseToEdge() {
        PlayerShip ship = new PlayerShip(15, 15, Color.BEIGE);
        ship.moveLeft();
        assertEquals(20, ship.getShape().getTranslateX(), 0);
    }

    @Test
    public void moveRightWorks() {
        PlayerShip ship = new PlayerShip(15, 15, Color.BEIGE);
        ship.moveRight();
        assertEquals(18.5, ship.getShape().getTranslateX(), 0);
    }

    @Test
    public void doesNotMoveRightIfTooCloseToEdge() {
        PlayerShip ship = new PlayerShip(950, 15, Color.BEIGE);
        ship.moveRight();
        assertEquals(940, ship.getShape().getTranslateX(), 0);
    }

    @Test
    public void moveUpWorks() {
        PlayerShip ship = new PlayerShip(15, 15, Color.BEIGE);
        ship.moveUp();
        assertEquals(5, ship.getShape().getTranslateY(), 0);
    }

    @Test
    public void moveDownWorks() {
        PlayerShip ship = new PlayerShip(15, 15, Color.BEIGE);
        ship.moveDown();
        assertEquals(19.5, ship.getShape().getTranslateY(), 0);
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

    @Test
    public void returnsCorrectAmountOfLives() {
        PlayerShip ship = new PlayerShip(15, 5, Color.BEIGE);
        assertEquals(3, ship.getLives(), 0);
    }

    @Test
    public void returnsCorrectAmountOfLivesWhenPlayerHasDied() {
        PlayerShip ship = new PlayerShip(15, 5, Color.BEIGE);
        ship.die();
        assertEquals(2, ship.getLives(), 0);
    }

    @Test
    public void returnsFalseWhenNotImmortal() {
        PlayerShip ship = new PlayerShip(15, 5, Color.BEIGE);
        assertFalse(ship.isImmortal());
    }

    @Test
    public void returnsTrueWhenImmortal() {
        PlayerShip ship = new PlayerShip(15, 5, Color.BEIGE);
        ship.setImmortal(true);
        assertTrue(ship.isImmortal());
    }

    @Test
    public void returnsCorrectAmountOfLivesWhenLivesAreSet() {
        PlayerShip ship = new PlayerShip(15, 5, Color.BEIGE);
        ship.setLives(1);
        assertEquals(1, ship.getLives(), 0);
    }
}