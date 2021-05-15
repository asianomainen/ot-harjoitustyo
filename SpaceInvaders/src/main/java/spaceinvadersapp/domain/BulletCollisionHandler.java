package spaceinvadersapp.domain;

import spaceinvadersapp.ui.GameUi;
import spaceinvadersapp.ui.SpaceInvadersUi;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class that handles the collision of bullets.
 *
 */

public class BulletCollisionHandler {

    /**
     * Handles all bullets fired by the player.
     *
     * @param   playerBullets   array list of the players bullets
     * @param   enemyShips      array list of enemies
     * @param   walls           array list of walls
     * @param   gamePoints      atomic integer to keep track of points
     * @param   gameUi          the game ui to update changes to the game
     */

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

    /**
     * Handles all bullets fired by the enemy.
     *
     * @param   enemyBullets    array list of enemy bullets
     * @param   playerShips     array list of player's ships
     * @param   walls           array list of walls
     * @param   gamePoints      atomic integer to keep track of points
     * @param   gameUi          the game ui to update changes to the game
     */

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
