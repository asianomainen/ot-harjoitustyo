package spaceinvadersapp.domain;

import spaceinvadersapp.ui.GameUi;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class DeadShapeRemover {
    ArrayList<PlayerBullet> playerBullets;
    ArrayList<EnemyBullet> enemyBullets;
    ArrayList<PlayerShip> playerShips;
    ArrayList<EnemyShip> enemyShips;
    GameUi gameUi;

    public DeadShapeRemover(ArrayList<PlayerBullet> playerBullets, ArrayList<EnemyBullet> enemyBullets, ArrayList<PlayerShip> playerShips, ArrayList<EnemyShip> enemyShips, GameUi gameUi) {
        this.playerBullets = playerBullets;
        this.enemyBullets = enemyBullets;
        this.playerShips = playerShips;
        this.enemyShips = enemyShips;
        this.gameUi = gameUi;
    }

    public void removeDeadShapes() {
        removeDeadPlayerBullets(playerBullets);
        removeDeadEnemyBullets(enemyBullets);
        removeDeadPlayerShips(playerShips);
        removeDeadEnemyShips(enemyShips);
    }

    private void removeDeadPlayerBullets(ArrayList<PlayerBullet> playerBullets) {
        playerBullets.stream()
                .filter(bullet -> !bullet.isAlive() || bullet.outOfBounds())
                .forEach(bullet -> gameUi.pane.getChildren().remove(bullet.getShape()));
        playerBullets.removeAll(playerBullets.stream()
                .filter(bullet -> !bullet.isAlive() || bullet.outOfBounds())
                .collect(Collectors.toList()));
    }

    private void removeDeadEnemyBullets(ArrayList<EnemyBullet> enemyBullets) {
        enemyBullets.stream()
                .filter(bullet -> !bullet.isAlive() || bullet.outOfBounds())
                .forEach(bullet -> gameUi.pane.getChildren().remove(bullet.getShape()));
        enemyBullets.removeAll(enemyBullets.stream()
                .filter(bullet -> !bullet.isAlive() || bullet.outOfBounds())
                .collect(Collectors.toList()));
    }

    private void removeDeadPlayerShips(ArrayList<PlayerShip> playerShips) {
        playerShips.stream()
                .filter(ship -> !ship.isAlive() || ship.outOfBounds())
                .forEach(ship -> gameUi.pane.getChildren().remove(ship.getShape()));
        playerShips.removeAll(playerShips.stream()
                .filter(ship -> !ship.isAlive() || ship.outOfBounds())
                .collect(Collectors.toList()));
    }

    private void removeDeadEnemyShips(ArrayList<EnemyShip> enemyShips) {
        enemyShips.stream()
                .filter(ship -> !ship.isAlive() || ship.outOfBounds())
                .forEach(ship -> gameUi.pane.getChildren().remove(ship.getShape()));
        enemyShips.removeAll(enemyShips.stream()
                .filter(ship -> !ship.isAlive() || ship.outOfBounds())
                .collect(Collectors.toList()));
    }
}
