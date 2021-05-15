package spaceinvadersapp.domain;

import spaceinvadersapp.ui.GameUi;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Class that checks for dead shapes and removes them.
 */

public class ShapeRemover {

    /**
     * Removes all objects from the game.
     * The players ship is not removed as it
     * is done automatically when a game
     * is started.
     *
     * @param   playerBullets   array list of the players bullets
     * @param   enemyBullets    array list of enemy bullets
     * @param   walls           array list of walls
     * @param   gameUi          the game ui to update changes to the game
     */

    public void removeAllShapes(ArrayList<PlayerBullet> playerBullets, ArrayList<EnemyBullet> enemyBullets, ArrayList<EnemyShip> enemyShips, ArrayList<GameWall> walls, GameUi gameUi) {
        playerBullets.forEach(bullet -> gameUi.pane.getChildren().remove(bullet.getShape()));
        playerBullets.removeAll(new ArrayList<>(playerBullets));
        enemyBullets.forEach(bullet -> gameUi.pane.getChildren().remove(bullet.getShape()));
        enemyBullets.removeAll(new ArrayList<>(enemyBullets));
        enemyShips.forEach(ship -> gameUi.pane.getChildren().remove(ship.getShape()));
        enemyShips.removeAll(new ArrayList<>(enemyShips));
        walls.forEach(wall -> gameUi.pane.getChildren().remove(wall.getShape()));
        walls.removeAll(new ArrayList<>(walls));
    }

    /**
     * Removes all dead player ships.
     *
     * @param   playerShips     array list of player's ships
     * @param   gameUi          the game ui to update changes to the game
     */

    public void removeDeadPlayerShips(ArrayList<PlayerShip> playerShips, GameUi gameUi) {
        playerShips.stream()
                .filter(ship -> !ship.isAlive() || ship.outOfBounds())
                .forEach(ship -> gameUi.pane.getChildren().remove(ship.getShape()));
        playerShips.removeAll(playerShips.stream()
                .filter(ship -> !ship.isAlive() || ship.outOfBounds())
                .collect(Collectors.toList()));
    }

    /**
     * Removes all dead enemy ships.
     *
     * @param   enemyShips      array list of enemies
     * @param   gameUi          the game ui to update changes to the game
     */

    public void removeDeadEnemyShips(ArrayList<EnemyShip> enemyShips, GameUi gameUi) {
        enemyShips.stream()
                .filter(ship -> !ship.isAlive() || ship.outOfBounds())
                .forEach(ship -> gameUi.pane.getChildren().remove(ship.getShape()));
        enemyShips.removeAll(enemyShips.stream()
                .filter(ship -> !ship.isAlive() || ship.outOfBounds())
                .collect(Collectors.toList()));
    }

    /**
     * Removes all dead player bullets.
     *
     * @param   playerBullets   array list of the players bullets
     * @param   gameUi          the game ui to update changes to the game
     */

    public void removeDeadPlayerBullets(ArrayList<PlayerBullet> playerBullets, GameUi gameUi) {
        playerBullets.stream()
                .filter(bullet -> !bullet.isAlive() || bullet.outOfBounds())
                .forEach(bullet -> gameUi.pane.getChildren().remove(bullet.getShape()));
        playerBullets.removeAll(playerBullets.stream()
                .filter(bullet -> !bullet.isAlive() || bullet.outOfBounds())
                .collect(Collectors.toList()));
    }

    /**
     * Removes all dead player bullets.
     *
     * @param   enemyBullets    array list of enemy bullets
     * @param   gameUi          the game ui to update changes to the game
     */

    public void removeDeadEnemyBullets(ArrayList<EnemyBullet> enemyBullets, GameUi gameUi) {
        enemyBullets.stream()
                .filter(bullet -> !bullet.isAlive() || bullet.outOfBounds())
                .forEach(bullet -> gameUi.pane.getChildren().remove(bullet.getShape()));
        enemyBullets.removeAll(enemyBullets.stream()
                .filter(bullet -> !bullet.isAlive() || bullet.outOfBounds())
                .collect(Collectors.toList()));
    }

    /**
     * Removes all dead walls.
     *
     * @param   walls           array list of walls
     * @param   gameUi          the game ui to update changes to the game
     */

    public void removeDeadWalls(ArrayList<GameWall> walls, GameUi gameUi) {
        walls.stream()
                .filter(wall -> !wall.isAlive())
                .forEach(wall -> gameUi.pane.getChildren().remove(wall.getShape()));
        walls.removeAll(walls.stream()
                .filter(wall -> !wall.isAlive())
                .collect(Collectors.toList()));
    }
}