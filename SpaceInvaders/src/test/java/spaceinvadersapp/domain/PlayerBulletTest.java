package spaceinvadersapp.domain;

import javafx.scene.paint.Color;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerBulletTest {

    @Test
    public void equalWhenSameObject() {
        PlayerBullet bullet1 = new PlayerBullet(15, 15, Color.BEIGE);
        PlayerBullet bullet2 = bullet1;
        assertEquals(bullet1, bullet2);
    }

    @Test
    public void notEqualWhenDifferentType() {
        PlayerBullet bullet = new PlayerBullet(15, 15, Color.BEIGE);
        Object object = new Object();
        assertNotEquals(bullet, object);
    }

    @Test
    public void notEqualWhenDifferentShape() {
        PlayerBullet bullet = new PlayerBullet(15, 15, Color.BEIGE);
        PlayerShip ship = new PlayerShip(15, 15, Color.BEIGE);
        assertNotEquals(ship.getShape(), bullet.getShape());
    }

    @Test
    public void moveLeftWorks() {
        PlayerBullet bullet = new PlayerBullet(15, 15, Color.BEIGE);
        bullet.moveLeft();
        assertEquals(11.5, bullet.getShape().getTranslateX(), 0);
    }

    @Test
    public void moveRightWorks() {
        PlayerBullet bullet = new PlayerBullet(15, 15, Color.BEIGE);
        bullet.moveRight();
        assertEquals(18.5, bullet.getShape().getTranslateX(), 0);
    }

    @Test
    public void moveUpWorks() {
        PlayerBullet bullet = new PlayerBullet(15, 15, Color.BEIGE);
        bullet.moveUp();
        assertEquals(5, bullet.getShape().getTranslateY(), 0);
    }

    @Test
    public void returnsTrueWhenOutOfBounds() {
        PlayerBullet bullet = new PlayerBullet(15, -5, Color.BEIGE);
        assertTrue(bullet.outOfBounds());
    }

    @Test
    public void returnsFalseWhenNotOutOfBounds() {
        PlayerBullet bullet = new PlayerBullet(15, 5, Color.BEIGE);
        assertFalse(bullet.outOfBounds());
    }

    @Test
    public void returnsTrueWhenAlive() {
        PlayerBullet bullet = new PlayerBullet(15, 5, Color.BEIGE);
        assertTrue(bullet.getAlive());
    }

    @Test
    public void returnsFalseWhenNotAlive() {
        PlayerBullet bullet = new PlayerBullet(15, 5, Color.BEIGE);
        bullet.setAlive(false);
        assertFalse(bullet.getAlive());
    }

    @Test
    public void returnsTrueWhenColliding() {
        PlayerBullet bullet1 = new PlayerBullet(15, 5, Color.BEIGE);
        PlayerBullet bullet2 = new PlayerBullet(15, 5, Color.BEIGE);
        assertTrue(bullet1.collision(bullet2));
    }

    @Test
    public void returnsFalseWhenNotColliding() {
        PlayerBullet bullet1 = new PlayerBullet(15, 5, Color.BEIGE);
        PlayerBullet bullet2 = new PlayerBullet(115, 105, Color.BEIGE);
        assertFalse(bullet1.collision(bullet2));
    }
}