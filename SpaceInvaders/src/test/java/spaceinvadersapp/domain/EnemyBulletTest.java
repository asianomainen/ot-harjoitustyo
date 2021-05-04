package spaceinvadersapp.domain;

import javafx.scene.paint.Color;
import org.junit.Test;

import static org.junit.Assert.*;

public class EnemyBulletTest {

    @Test
    public void equalWhenSameObject() {
        EnemyBullet bullet1 = new EnemyBullet(15, 15, Color.BEIGE);
        EnemyBullet bullet2 = bullet1;
        assertEquals(bullet1, bullet2);
    }

    @Test
    public void notEqualWhenDifferentType() {
        EnemyBullet bullet = new EnemyBullet(15, 15, Color.BEIGE);
        Object object = new Object();
        assertNotEquals(bullet, object);
    }

    @Test
    public void notEqualWhenDifferentShape() {
        EnemyBullet bullet = new EnemyBullet(15, 15, Color.BEIGE);
        PlayerShip ship = new PlayerShip(15, 15, Color.BEIGE);
        assertNotEquals(ship.getShape(), bullet.getShape());
    }

    @Test
    public void moveLeftWorks() {
        EnemyBullet bullet = new EnemyBullet(15, 15, Color.BEIGE);
        bullet.moveLeft();
        assertEquals(11.5, bullet.getShape().getTranslateX(), 0);
    }

    @Test
    public void moveRightWorks() {
        EnemyBullet bullet = new EnemyBullet(15, 15, Color.BEIGE);
        bullet.moveRight();
        assertEquals(18.5, bullet.getShape().getTranslateX(), 0);
    }

    @Test
    public void moveUpWorks() {
        EnemyBullet bullet = new EnemyBullet(15, 15, Color.BEIGE);
        bullet.moveUp();
        assertEquals(5, bullet.getShape().getTranslateY(), 0);
    }

    @Test
    public void returnsTrueWhenOutOfBounds() {
        EnemyBullet bullet = new EnemyBullet(15, -5, Color.BEIGE);
        assertTrue(bullet.outOfBounds());
    }

    @Test
    public void returnsFalseWhenNotOutOfBounds() {
        EnemyBullet bullet = new EnemyBullet(15, 5, Color.BEIGE);
        assertFalse(bullet.outOfBounds());
    }

    @Test
    public void returnsTrueWhenYSmallerThanZero() {
        EnemyBullet bullet = new EnemyBullet(15, -5, Color.BEIGE);
        assertTrue(bullet.outOfBounds());
    }

    @Test
    public void returnsTrueWhenYLargerThan720() {
        EnemyBullet bullet = new EnemyBullet(15, 730, Color.BEIGE);
        assertTrue(bullet.outOfBounds());
    }

    @Test
    public void returnsTrueWhenXSmallerThanZero() {
        EnemyBullet bullet = new EnemyBullet(-15, 15, Color.BEIGE);
        assertTrue(bullet.outOfBounds());
    }

    @Test
    public void returnsTrueWhenXLargerThan960() {
        EnemyBullet bullet = new EnemyBullet(970, 50, Color.BEIGE);
        assertTrue(bullet.outOfBounds());
    }

    @Test
    public void returnsTrueWhenAlive() {
        EnemyBullet bullet = new EnemyBullet(15, 5, Color.BEIGE);
        assertTrue(bullet.isAlive());
    }

    @Test
    public void returnsFalseWhenNotAlive() {
        EnemyBullet bullet = new EnemyBullet(15, 5, Color.BEIGE);
        bullet.setAlive(false);
        assertFalse(bullet.isAlive());
    }

    @Test
    public void returnsTrueWhenColliding() {
        EnemyBullet bullet1 = new EnemyBullet(15, 5, Color.BEIGE);
        EnemyBullet bullet2 = new EnemyBullet(15, 5, Color.BEIGE);
        assertTrue(bullet1.collision(bullet2));
    }

    @Test
    public void returnsFalseWhenNotColliding() {
        EnemyBullet bullet1 = new EnemyBullet(15, 5, Color.BEIGE);
        EnemyBullet bullet2 = new EnemyBullet(115, 105, Color.BEIGE);
        assertFalse(bullet1.collision(bullet2));
    }
}