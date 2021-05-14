package spaceinvadersapp.domain;

import spaceinvadersapp.ui.GameUi;
import spaceinvadersapp.ui.SpaceInvadersUi;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class BulletCollisionHandler {

    public void handlePlayerShots(ArrayList<PlayerBullet> playerBullets, ArrayList<EnemyShip> enemyShips, ArrayList<GameWall> walls, AtomicInteger gamePoints, GameUi gameUi) {
        playerBullets.forEach(bullet -> enemyShips.forEach(enemy -> {
            if (bullet.collision(enemy)) {
                bullet.setAlive(false);
                enemy.setAlive(false);
                gameUi.pointsText.setText("Points: " + (gamePoints.addAndGet(100)));
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

    public void handleEnemyShots(ArrayList<EnemyBullet> enemyBullets, ArrayList<PlayerShip> playerShips, ArrayList<GameWall> walls, AtomicInteger gamePoints, GameUi gameUi) {
        enemyBullets.forEach(bullet -> playerShips.forEach(player -> {
            if (bullet.collision(player) && !player.isImmortal()) {
                bullet.setAlive(false);
                if (player.getLives() <= 1) {
                    player.setAlive(false);
                }
                SpaceInvadersUi.immortalTime++;
                player.die();
                player.setImmortal(true);
                gameUi.pointsText.setText("Points: " + (gamePoints.addAndGet(-500)));
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
