package spaceinvadersapp.domain;

import javafx.scene.paint.Color;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
}