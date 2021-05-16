package spaceinvadersapp.domain;

import de.saxsys.javafx.test.JfxRunner;
import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import spaceinvadersapp.ui.GameUi;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(JfxRunner.class)
public class ShapeRemoverTest {
    ShapeRemover remover;
    GameUi gameUi;
    ArrayList<PlayerShip> playerShips;
    ArrayList<PlayerBullet> playerBullets;
    ArrayList<EnemyShip> enemyShips;
    ArrayList<EnemyBullet> enemyBullets;
    ArrayList<GameWall> walls;

    @Before
    public void setUp() {
        remover = new ShapeRemover();
        gameUi = new GameUi(960, 720);
        playerShips = new ArrayList<>();
        playerBullets = new ArrayList<>();
        enemyShips = new ArrayList<>();
        enemyBullets = new ArrayList<>();
        walls = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            playerShips.add(new PlayerShip(15, 15, Color.BEIGE));
            playerBullets.add(new PlayerBullet(15, 15, Color.BEIGE));
            enemyShips.add(new EnemyShip(15, 15, Color.BEIGE));
            enemyBullets.add(new EnemyBullet(15, 15, Color.BEIGE));
            walls.add(new GameWall(15, 15, Color.BEIGE));
        }
    }

    @Test
    public void removeAllShapesWorks() {
        remover.removeAllShapes(playerBullets, enemyBullets, enemyShips, walls, gameUi);
        int total = playerBullets.size() + enemyBullets.size() + enemyShips.size() + walls.size();
        assertEquals(0, total);
    }

    @Test
    public void removeDeadPlayerShipsWorksWhenDead() {
        for (PlayerShip ship : playerShips) {
            ship.setAlive(false);
        }
        remover.removeDeadPlayerShips(playerShips, gameUi);
        assertEquals(0, playerShips.size());
    }

    @Test
    public void removeDeadPlayerShipsWorksWhenOutOfBounds() {
        for (PlayerShip ship : playerShips) {
            ship.getShape().setTranslateX(-100);
        }
        remover.removeDeadPlayerShips(playerShips, gameUi);
        assertEquals(0, playerShips.size());
    }

    @Test
    public void removeDeadEnemyShipsWorksWhenDead() {
        for (EnemyShip ship : enemyShips) {
            ship.setAlive(false);
        }
        remover.removeDeadEnemyShips(enemyShips, gameUi);
        assertEquals(0, enemyShips.size());
    }

    @Test
    public void removeDeadEnemyShipsWorksWhenOutOfBounds() {
        for (EnemyShip ship : enemyShips) {
            ship.getShape().setTranslateX(-100);
        }
        remover.removeDeadEnemyShips(enemyShips, gameUi);
        assertEquals(0, enemyShips.size());
    }

    @Test
    public void removeDeadPlayerBulletsWorksWhenDead() {
        for (PlayerBullet bullet : playerBullets) {
            bullet.setAlive(false);
        }
        remover.removeDeadPlayerBullets(playerBullets, gameUi);
        assertEquals(0, playerBullets.size());
    }

    @Test
    public void removeDeadPlayerBulletsWorksWhenOutOfBounds() {
        for (PlayerBullet bullet : playerBullets) {
            bullet.getShape().setTranslateX(-100);
        }
        remover.removeDeadPlayerBullets(playerBullets, gameUi);
        assertEquals(0, playerBullets.size());
    }

    @Test
    public void removeDeadEnemyBulletsWorksWhenDead() {
        for (EnemyBullet bullet : enemyBullets) {
            bullet.setAlive(false);
        }
        remover.removeDeadEnemyBullets(enemyBullets, gameUi);
        assertEquals(0, enemyBullets.size());
    }

    @Test
    public void removeDeadEnemyBulletsWorksWhenOutOfBounds() {
        for (EnemyBullet bullet : enemyBullets) {
            bullet.getShape().setTranslateX(-100);
        }
        remover.removeDeadEnemyBullets(enemyBullets, gameUi);
        assertEquals(0, enemyBullets.size());
    }

    @Test
    public void removeDeadWallsWorksWhenDead() {
        for (GameWall wall : walls) {
            wall.setAlive(false);
        }
        remover.removeDeadWalls(walls, gameUi);
        assertEquals(0, walls.size());
    }
}
