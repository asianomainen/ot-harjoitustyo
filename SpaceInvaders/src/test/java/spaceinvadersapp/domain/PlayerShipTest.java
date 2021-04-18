package spaceinvadersapp.domain;

import javafx.scene.paint.Color;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
        assertEquals(11.5, ship.getX(), 0);
    }

    @Test
    public void moveRightWorks() {
        PlayerShip ship = new PlayerShip(15, 15, Color.BEIGE);
        ship.moveRight();
        assertEquals(18.5, ship.getX(), 0);
    }
}