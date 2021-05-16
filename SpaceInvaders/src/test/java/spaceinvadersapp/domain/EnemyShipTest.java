package spaceinvadersapp.domain;

import javafx.scene.paint.Color;
import org.junit.Test;

import static org.junit.Assert.*;

public class EnemyShipTest {

    @Test
    public void equalWhenSameObject() {
        EnemyShip ship1 = new EnemyShip(15, 15, Color.BEIGE);
        EnemyShip ship2 = ship1;
        assertEquals(ship1, ship2);
    }

    @Test
    public void notEqualWhenDifferentType() {
        EnemyShip ship = new EnemyShip(15, 15, Color.BEIGE);
        Object object = new Object();
        assertNotEquals(ship, object);
    }

    @Test
    public void notEqualWhenDifferentShape() {
        EnemyShip ship = new EnemyShip(15, 15, Color.BEIGE);
        PlayerBullet bullet = new PlayerBullet(15, 15, Color.BEIGE);
        assertNotEquals(ship.getShape(), bullet.getShape());
    }

    @Test
    public void moveLeftWorks() {
        EnemyShip ship = new EnemyShip(15, 15, Color.BEIGE);
        ship.moveLeft();
        assertEquals(3, ship.getShape().getTranslateX(), 0);
    }

    @Test
    public void moveRightWorks() {
        EnemyShip ship = new EnemyShip(15, 15, Color.BEIGE);
        ship.moveRight();
        assertEquals(27, ship.getShape().getTranslateX(), 0);
    }

    @Test
    public void moveUpWorks() {
        EnemyShip ship = new EnemyShip(15, 15, Color.BEIGE);
        ship.moveUp();
        assertEquals(5, ship.getShape().getTranslateY(), 0);
    }

    @Test
    public void moveDownWorks() {
        EnemyShip ship = new EnemyShip(15, 15, Color.BEIGE);
        ship.moveDown();
        assertEquals(60, ship.getShape().getTranslateY(), 0);
    }

    @Test
    public void moveWorksWhenCounterIsAboveZero() {
        EnemyShip ship = new EnemyShip(15, 15, Color.BEIGE);
        ship.move(18);
        assertEquals(3, ship.getShape().getTranslateX(), 0);
    }

    @Test
    public void moveWorksWhenCounterIsZero() {
        EnemyShip ship = new EnemyShip(15, 15, Color.BEIGE);
        ship.move(0);
        assertEquals(60, ship.getShape().getTranslateY(), 0);
    }

    @Test
    public void moveWorksWhenDirectionIsChanged() {
        EnemyShip ship = new EnemyShip(15, 15, Color.BEIGE);
        ship.changeDirection();
        ship.move(18);
        assertEquals(27, ship.getShape().getTranslateX(), 0);
    }

    @Test
    public void returnsTrueWhenOutOfBounds() {
        EnemyShip ship = new EnemyShip(15, -5, Color.BEIGE);
        assertTrue(ship.outOfBounds());
    }

    @Test
    public void returnsFalseWhenNotOutOfBounds() {
        EnemyShip ship = new EnemyShip(15, 5, Color.BEIGE);
        assertFalse(ship.outOfBounds());
    }

    @Test
    public void returnsTrueWhenAlive() {
        EnemyShip ship = new EnemyShip(15, 5, Color.BEIGE);
        assertTrue(ship.isAlive());
    }

    @Test
    public void returnsFalseWhenNotAlive() {
        EnemyShip ship = new EnemyShip(15, 5, Color.BEIGE);
        ship.setAlive(false);
        assertFalse(ship.isAlive());
    }

    @Test
    public void returnsTrueWhenColliding() {
        EnemyShip ship1 = new EnemyShip(15, 5, Color.BEIGE);
        EnemyShip ship2 = new EnemyShip(15, 5, Color.BEIGE);
        assertTrue(ship1.collision(ship2));
    }

    @Test
    public void returnsFalseWhenNotColliding() {
        EnemyShip ship1 = new EnemyShip(15, 5, Color.BEIGE);
        EnemyShip ship2 = new EnemyShip(115, 105, Color.BEIGE);
        assertFalse(ship1.collision(ship2));
    }

    @Test
    public void directionIsFalseByDefault() {
        EnemyShip ship = new EnemyShip(15, 5, Color.BEIGE);
        assertFalse(ship.getDirection());
    }

    @Test
    public void directionChangesFromLeftToRight() {
        EnemyShip ship = new EnemyShip(15, 5, Color.BEIGE);
        ship.changeDirection();
        assertTrue(ship.getDirection());
    }

    @Test
    public void directionChangesFromRightToLeft() {
        EnemyShip ship = new EnemyShip(15, 5, Color.BEIGE);
        ship.changeDirection();
        ship.changeDirection();
        assertFalse(ship.getDirection());
    }
}
