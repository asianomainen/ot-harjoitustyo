package spaceinvadersapp.domain;

import spaceinvadersapp.ui.GameUi;
import spaceinvadersapp.ui.SpaceInvadersUi;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.ArrayList;

public class BulletCollisionHandler {
    ArrayList<PlayerBullet> playerBullets;
    ArrayList<EnemyBullet> enemyBullets;
    ArrayList<PlayerShip> playerShips;
    ArrayList<EnemyShip> enemyShips;
    ArrayList<GameWall> walls;
    GameUi gameUi;
    AtomicInteger points;

    public BulletCollisionHandler(ArrayList<PlayerBullet> playerBullets, ArrayList<EnemyBullet> enemyBullets, ArrayList<PlayerShip> playerShips, ArrayList<EnemyShip> enemyShips, ArrayList<GameWall> walls, GameUi gameUi, AtomicInteger points) {
        this.playerBullets = playerBullets;
        this.enemyBullets = enemyBullets;
        this.playerShips = playerShips;
        this.enemyShips = enemyShips;
        this.walls = walls;
        this.gameUi = gameUi;
        this.points = points;
    }

    public void handleBulletCollisions() {
        handlePlayerShots();
        handleEnemyShots();
    }

    private void handlePlayerShots() {
        playerBullets.forEach(bullet -> enemyShips.forEach(enemy -> {
            if (bullet.collision(enemy)) {
                bullet.setAlive(false);
                enemy.setAlive(false);
                gameUi.pointsText.setText("Points: " + (points.addAndGet(100)));
            }
        }));

        playerBullets.forEach(bullet -> walls.forEach(wall -> {
            if (bullet.collision(wall)) {
                bullet.setAlive(false);
                if (wall.getLives() <= 1) {
                    wall.setAlive(false);
                }
                wall.die();
                wall.getShape().setOpacity(wall.getShape().getOpacity() - 0.09);
            }
        }));
    }

    private void handleEnemyShots() {
        enemyBullets.forEach(bullet -> playerShips.forEach(player -> {
            if (bullet.collision(player) && !player.isImmortal()) {
                bullet.setAlive(false);
                if (player.getLives() <= 1) {
                    player.setAlive(false);
                }
                SpaceInvadersUi.immortalTime++;
                player.die();
                player.setImmortal(true);
                gameUi.pointsText.setText("Points: " + (points.addAndGet(-500)));
            }
        }));

        enemyBullets.forEach(bullet -> walls.forEach(wall -> {
            if (bullet.collision(wall)) {
                bullet.setAlive(false);
                if (wall.getLives() <= 1) {
                    wall.setAlive(false);
                }
                wall.die();
                wall.getShape().setOpacity(wall.getShape().getOpacity() - 0.09);
            }
        }));
    }
}
