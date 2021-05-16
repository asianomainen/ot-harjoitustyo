package spaceinvadersapp.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class HighScoreTest {
    HighScore highScore;

    @Before
    public void setUp() {
        highScore = new HighScore("Seppo", "122", "24000");
    }

    @Test
    public void getNameWorks() {
        assertEquals("Seppo", highScore.getName());
    }

    @Test
    public void getTimeWorks() {
        assertEquals("122", highScore.getTime());
    }

    @Test
    public void getPointsWorks() {
        assertEquals("24000", highScore.getPoints());
    }
}
