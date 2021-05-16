package spaceinvadersapp.domain;

import javafx.scene.paint.Color;
import org.junit.Test;

import static org.junit.Assert.*;

public class WallTest {

    @Test
    public void equalWhenSameObject() {
        GameWall wall1 = new GameWall(15, 15, Color.BEIGE);
        GameWall wall2 = wall1;
        assertEquals(wall1, wall2);
    }

    @Test
    public void notEqualWhenDifferentType() {
        GameWall wall = new GameWall(15, 15, Color.BEIGE);
        Object object = new Object();
        assertNotEquals(wall, object);
    }

    @Test
    public void notEqualWhenDifferentShape() {
        GameWall wall = new GameWall(15, 15, Color.BEIGE);
        PlayerBullet bullet = new PlayerBullet(15, 15, Color.BEIGE);
        assertNotEquals(wall.getShape(), bullet.getShape());
    }

    @Test
    public void returnsCorrectAmountOfLives() {
        GameWall wall = new GameWall(15, 15, Color.BEIGE);
        assertEquals(10, wall.getLives(), 0);
    }

    @Test
    public void returnsCorrectAmountOfLivesWhenPlayerHasDied() {
        GameWall wall = new GameWall(15, 15, Color.BEIGE);
        wall.die();
        wall.die();
        assertEquals(8, wall.getLives(), 0);
    }
}