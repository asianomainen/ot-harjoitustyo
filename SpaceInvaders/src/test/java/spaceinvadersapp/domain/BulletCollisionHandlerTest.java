package spaceinvadersapp.domain;

import de.saxsys.javafx.test.JfxRunner;
import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import spaceinvadersapp.ui.GameUi;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;

@RunWith(JfxRunner.class)
public class BulletCollisionHandlerTest {
    BulletCollisionHandler handler;
    GameUi gameUi;
    ArrayList<PlayerShip> playerShips;
    ArrayList<PlayerBullet> playerBullets;
    ArrayList<EnemyShip> enemyShips;
    ArrayList<EnemyBullet> enemyBullets;
    ArrayList<GameWall> walls;
    AtomicInteger gamePoints;

    @Before
    public void setUp() {
        handler = new BulletCollisionHandler();
        gameUi = new GameUi(960, 720);
        playerShips = new ArrayList<>();
        playerBullets = new ArrayList<>();
        enemyShips = new ArrayList<>();
        enemyBullets = new ArrayList<>();
        walls = new ArrayList<>();
        gamePoints = new AtomicInteger();
        gamePoints.addAndGet(500);
    }

    @Test
    public void handlePlayerShotsWorksWithPlayerBulletsAndEnemies() {
        PlayerBullet pBullet = new PlayerBullet(15, 15, Color.BEIGE);
        EnemyShip eShip = new EnemyShip(15, 15, Color.BEIGE);
        playerBullets.add(pBullet);
        enemyShips.add(eShip);
        gameUi.pane.getChildren().addAll(pBullet.getShape(), eShip.getShape());
        handler.handlePlayerShots(playerBullets, enemyShips, walls, gamePoints, gameUi);

        assertFalse(pBullet.isAlive());
    }

    @Test
    public void handlePlayerShotsWorksWithPlayerBulletsAndWalls() {
        PlayerBullet pBullet = new PlayerBullet(15, 15, Color.BEIGE);
        GameWall wall = new GameWall(15, 15, Color.BEIGE);
        wall.setLives(1);
        playerBullets.add(pBullet);
        walls.add(wall);
        gameUi.pane.getChildren().addAll(pBullet.getShape(), wall.getShape());
        handler.handlePlayerShots(playerBullets, enemyShips, walls, gamePoints, gameUi);

        assertFalse(pBullet.isAlive());
    }

    @Test
    public void handleEnemyShotsWorksWithEnemyBulletsAndPlayers() {
        EnemyBullet eBullet = new EnemyBullet(15, 15, Color.BEIGE);
        PlayerShip pShip = new PlayerShip(15, 15, Color.BEIGE);
        pShip.setLives(1);
        enemyBullets.add(eBullet);
        playerShips.add(pShip);
        gameUi.pane.getChildren().addAll(eBullet.getShape(), pShip.getShape());
        handler.handleEnemyShots(enemyBullets, playerShips, walls, gamePoints, gameUi);

        assertFalse(eBullet.isAlive());
    }

    @Test
    public void handleEnemyShotsWorksWithEnemyBulletsAndWalls() {
        EnemyBullet eBullet = new EnemyBullet(15, 15, Color.BEIGE);
        GameWall wall = new GameWall(15, 15, Color.BEIGE);
        wall.setLives(1);
        enemyBullets.add(eBullet);
        walls.add(wall);
        gameUi.pane.getChildren().addAll(eBullet.getShape(), wall.getShape());
        handler.handleEnemyShots(enemyBullets, playerShips, walls, gamePoints, gameUi);

        assertFalse(eBullet.isAlive());
    }
}
