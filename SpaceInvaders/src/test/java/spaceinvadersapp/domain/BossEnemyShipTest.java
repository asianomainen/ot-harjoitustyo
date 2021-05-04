package spaceinvadersapp.domain;

import javafx.scene.paint.Color;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class BossEnemyShipTest {

    @Test
    public void equalWhenSameObject() {
        BossEnemyShip ship1 = new BossEnemyShip(15, 15, Color.BEIGE);
        BossEnemyShip ship2 = ship1;
        assertEquals(ship1, ship2);
    }

    @Test
    public void notEqualWhenDifferentType() {
        BossEnemyShip ship = new BossEnemyShip(15, 15, Color.BEIGE);
        Object object = new Object();
        assertNotEquals(ship, object);
    }

    @Test
    public void notEqualWhenDifferentShape() {
        BossEnemyShip ship = new BossEnemyShip(15, 15, Color.BEIGE);
        PlayerBullet bullet = new PlayerBullet(15, 15, Color.BEIGE);
        assertNotEquals(ship.getShape(), bullet.getShape());
    }

    @Test
    public void moveLeftWorks() {
        BossEnemyShip ship = new BossEnemyShip(15, 15, Color.BEIGE);
        ship.moveLeft();
        assertEquals(11.5, ship.getShape().getTranslateX(), 0);
    }

    @Test
    public void moveRightWorks() {
        BossEnemyShip ship = new BossEnemyShip(15, 15, Color.BEIGE);
        ship.moveRight();
        assertEquals(18.5, ship.getShape().getTranslateX(), 0);
    }

    @Test
    public void moveUpWorks() {
        BossEnemyShip ship = new BossEnemyShip(15, 15, Color.BEIGE);
        ship.moveUp();
        assertEquals(5, ship.getShape().getTranslateY(), 0);
    }

    @Test
    public void returnsTrueWhenOutOfBounds() {
        BossEnemyShip ship = new BossEnemyShip(15, -5, Color.BEIGE);
        assertTrue(ship.outOfBounds());
    }

    @Test
    public void returnsFalseWhenNotOutOfBounds() {
        BossEnemyShip ship = new BossEnemyShip(15, 5, Color.BEIGE);
        assertFalse(ship.outOfBounds());
    }

    @Test
    public void returnsTrueWhenAlive() {
        BossEnemyShip ship = new BossEnemyShip(15, 5, Color.BEIGE);
        assertTrue(ship.isAlive());
    }

    @Test
    public void returnsFalseWhenNotAlive() {
        BossEnemyShip ship = new BossEnemyShip(15, 5, Color.BEIGE);
        ship.setAlive(false);
        assertFalse(ship.isAlive());
    }

    @Test
    public void returnsTrueWhenColliding() {
        BossEnemyShip ship1 = new BossEnemyShip(15, 5, Color.BEIGE);
        BossEnemyShip ship2 = new BossEnemyShip(15, 5, Color.BEIGE);
        assertTrue(ship1.collision(ship2));
    }

    @Test
    public void returnsFalseWhenNotColliding() {
        BossEnemyShip ship1 = new BossEnemyShip(15, 5, Color.BEIGE);
        BossEnemyShip ship2 = new BossEnemyShip(115, 105, Color.BEIGE);
        assertFalse(ship1.collision(ship2));
    }
}
