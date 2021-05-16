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

/*        for (int i = 0; i < 3; i++) {
            playerShips.add(new PlayerShip(15, 15, Color.BEIGE));
            playerBullets.add(new PlayerBullet(15, 15, Color.BEIGE));
            enemyShips.add(new EnemyShip(15, 15, Color.BEIGE));
            enemyBullets.add(new EnemyBullet(15, 15, Color.BEIGE));
            walls.add(new GameWall(15, 15, Color.BEIGE));
        }*/
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

/*    @Test
    public void handlePlayerShotsWorks() {
        for (int i = 0; i < 3; i++) {
            PlayerBullet pBullet1 = new PlayerBullet(15 * i, 15 * i, Color.BEIGE);
            EnemyShip eShip = new EnemyShip(15 * i, 15 * i, Color.BEIGE);
            PlayerBullet pBullet2 = new PlayerBullet(50 * i, 50 * i, Color.BEIGE);
            GameWall wall = new GameWall(50 * i, 50 * i, Color.BEIGE);
            wall.setLives(1);
            playerBullets.add(pBullet1);
            playerBullets.add(pBullet2);
            enemyShips.add(eShip);
            walls.add(wall);
            gameUi.pane.getChildren().addAll(pBullet1.getShape(), pBullet2.getShape(), eShip.getShape(), wall.getShape());
        }

        for (PlayerBullet bullet : playerBullets) {
            for (EnemyShip ship : enemyShips) {
                bullet.collision(ship);
                handler.handlePlayerShots(playerBullets, enemyShips, walls, gamePoints, gameUi);
            }
        }

        for (PlayerBullet bullet : playerBullets) {
            for (GameWall wall : walls) {
                if (bullet.collision(wall)) {
                    handler.handlePlayerShots(playerBullets, enemyShips, walls, gamePoints, gameUi);
                }
            }
        }

        int total = playerBullets.size() + enemyShips.size() + walls.size();
        assertEquals(0, total);
    }*/
}
