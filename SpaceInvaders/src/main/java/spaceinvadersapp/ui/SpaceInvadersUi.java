package spaceinvadersapp.ui;

import spaceinvadersapp.domain.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.*;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Class for creating the ui.
 */

public class SpaceInvadersUi extends Application {
    private final static int WIDTH = 960;
    private final static int HEIGHT = 720;
    private final ColorInput color = new ColorInput();
    private final Blend blend = new Blend(BlendMode.DIFFERENCE);
    private double time = 0.0;
    private boolean isPaused = false;
    int enemyMovementCounter = 60;

    @Override
    public void start(Stage stage) {
        // Create scene for main menu
        MainMenuUi mainMenuUi = new MainMenuUi();
        Scene mainMenu = mainMenuUi.createMainMenu(WIDTH, HEIGHT);

        //Create scene for game
        GameUi gameUi = new GameUi();
        Scene game = gameUi.createGameUi(WIDTH, HEIGHT);

        // Create scene for settings menu
        SettingsUi settingsUi = new SettingsUi();
        Scene settingsMenu = settingsUi.createSettingsUi(WIDTH, HEIGHT);

        // Create scene for High Scores
        HighScoreUi highScoreUi = new HighScoreUi();
        Scene highScoreMenu = highScoreUi.createHighScoreUi(WIDTH, HEIGHT);

        // Creates necessary array lists, hash maps and an atomic integer for game UI
        HashMap<KeyCode, Boolean> pressedKeys = new HashMap<>();
        ArrayList<PlayerShip> players = new ArrayList<>();
        ArrayList<PlayerBullet> playerBullets = new ArrayList<>();
        ArrayList<EnemyShip> enemies = new ArrayList<>();
        ArrayList<EnemyBullet> enemyBullets = new ArrayList<>();
        ArrayList<BossEnemyShip> bosses = new ArrayList<>();
        AtomicInteger points = new AtomicInteger();

        // Spawns enemies
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                enemies.add(new EnemyShip((230 + i * 55), (65 + j * 65), Color.PURPLE));
            }
        }

        enemies.forEach(enemy -> gameUi.pane.getChildren().add(enemy.getShape()));
        bosses.add(new BossEnemyShip(WIDTH / 2, 20, Color.CYAN));
        players.add(gameUi.playerShip);

        game.setOnKeyPressed(event -> pressedKeys.put(event.getCode(), Boolean.TRUE));
        game.setOnKeyReleased(event -> pressedKeys.put(event.getCode(), Boolean.FALSE));

        AnimationTimer animation = new AnimationTimer() {

            @Override
            public void handle(long presentTime) {
                if (!isPaused) {
                    time += 0.02;
                    if (pressedKeys.getOrDefault(KeyCode.LEFT, false)) {
                        gameUi.playerShip.moveLeft();
                    }

                    if (pressedKeys.getOrDefault(KeyCode.RIGHT, false)) {
                        gameUi.playerShip.moveRight();
                    }

                    if (pressedKeys.getOrDefault(KeyCode.SPACE, false) && playerBullets.size() < 1) {
                        PlayerBullet playerBullet = new PlayerBullet((int) gameUi.playerShip.getShape().getTranslateX(), (int) gameUi.playerShip.getShape().getTranslateY(), Color.BLACK);
                        playerBullets.add(playerBullet);
                        gameUi.pane.getChildren().add(playerBullet.getShape());
                    }

                    if (time >= 50 * 0.02) {
                        enemyMovementCounter++;

                        if (enemyMovementCounter >= 120) {
                            enemyMovementCounter = 0;
                        }

                        enemies.forEach(Enemy -> Enemy.move(enemyMovementCounter));
                    }

                    if (time > 1) {
                        if (Math.random() > 0.5) {
                            int random = randomNumberGenerator(0, enemies.size() - 1);
                            EnemyBullet enemyBullet = new EnemyBullet((int) enemies.get(random).getShape().getTranslateX(), (int) enemies.get(random).getShape().getTranslateY(), Color.CHOCOLATE);
                            enemyBullets.add(enemyBullet);
                            gameUi.pane.getChildren().add(enemyBullet.getShape());
                        }

                        time = 0;
                    }

                    playerBullets.forEach(Shape::moveUp);
                    enemyBullets.forEach(Shape::moveDown);

                    playerBullets.forEach(bullet -> enemies.forEach(enemy -> {
                        if (bullet.collision(enemy)) {
                            bullet.setAlive(false);
                            enemy.setAlive(false);
                            gameUi.pointsText.setText("Points: " + (points.addAndGet(100)));
                        }
                    }));

                    enemyBullets.forEach(bullet -> players.forEach(player -> {
                        if (bullet.collision(player)) {
                            bullet.setAlive(false);
                            player.setAlive(false);
                        }
                    }));

                    playerBullets.forEach(bullet -> bosses.forEach(boss -> {
                        if (bullet.collision(boss)) {
                            bullet.setAlive(false);
                            boss.setAlive(false);
                            gameUi.pointsText.setText("Points: " + (points.addAndGet(500)));
                        }
                    }));

                    playerBullets.stream()
                            .filter(bullet -> !bullet.isAlive() || bullet.outOfBounds())
                            .forEach(bullet -> gameUi.pane.getChildren().remove(bullet.getShape()));
                    playerBullets.removeAll(playerBullets.stream()
                            .filter(bullet -> !bullet.isAlive() || bullet.outOfBounds())
                            .collect(Collectors.toList()));

                    enemyBullets.stream()
                            .filter(bullet -> !bullet.isAlive() || bullet.outOfBounds())
                            .forEach(bullet -> gameUi.pane.getChildren().remove(bullet.getShape()));
                    enemyBullets.removeAll(enemyBullets.stream()
                            .filter(bullet -> !bullet.isAlive() || bullet.outOfBounds())
                            .collect(Collectors.toList()));

                    enemies.stream()
                            .filter(enemy -> !enemy.isAlive())
                            .forEach(enemy -> gameUi.pane.getChildren().remove(enemy.getShape()));
                    enemies.removeAll(enemies.stream()
                            .filter(enemy -> !enemy.isAlive())
                            .collect(Collectors.toList()));

                    players.stream()
                            .filter(enemy -> !enemy.isAlive())
                            .forEach(enemy -> gameUi.pane.getChildren().remove(enemy.getShape()));
                    players.removeAll(players.stream()
                            .filter(enemy -> !enemy.isAlive())
                            .collect(Collectors.toList()));

                    bosses.stream()
                            .filter(boss -> !boss.isAlive())
                            .forEach(boss -> gameUi.pane.getChildren().remove(boss.getShape()));
                    bosses.removeAll(bosses.stream()
                            .filter(boss -> !boss.isAlive())
                            .collect(Collectors.toList()));
                }

                game.setOnKeyPressed(event -> {
                    pressedKeys.put(event.getCode(), Boolean.TRUE);
                    if (event.getCode().equals(KeyCode.ESCAPE)) {
                        isPaused = !isPaused;
                    }
                });
            }
        };
        animation.start();

        // Start game button functions
        mainMenuUi.btnStart.setOnAction(event -> stage.setScene(game));

        // Settings button functions
        mainMenuUi.btnSettings.setOnAction(event -> stage.setScene(settingsMenu));
        settingsUi.btnSettingsBackToMainMenu.setOnAction(event -> stage.setScene(mainMenu));
        settingsUi.soundCheckBox.setOnAction(event -> {
            if (settingsUi.soundCheckBox.isSelected()) {
                settingsUi.soundCheckBox.setText("Sound ON");
            } else {
                settingsUi.soundCheckBox.setText("Sound OFF");
            }
        });

        settingsUi.invertColours.setOnAction((event) -> {
            if (settingsUi.invertColours.isSelected()) {
                color.setPaint(Color.WHITE);
            } else {
                color.setPaint(Color.BLACK);
            }

            color.setWidth(Double.MAX_VALUE);
            color.setHeight(Double.MAX_VALUE);
            blend.setBottomInput(color);
            gameUi.pane.setEffect(blend);
            mainMenuUi.vbButtons.setEffect(blend);
            highScoreUi.hsVBox.setEffect(blend);
            settingsUi.stgGrid.setEffect(blend);
        });

        // High Scores button functions
        mainMenuUi.btnHighScores.setOnAction(event -> stage.setScene(highScoreMenu));
        highScoreUi.btnHighScoreBackToMainMenu.setOnAction(event -> stage.setScene(mainMenu));

        // Exit button functions
        mainMenuUi.btnExit.setOnAction(event -> stage.close());

        // Finalizes stage
        stage.setResizable(false);
        stage.setScene(mainMenu);
        stage.setTitle("Space Invaders");
        stage.show();
    }

    public static int randomNumberGenerator(int min, int max) {
        // Add one to max to include it in possible random numbers generated
        max++;
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public static void main(String[] args) {
        launch(SpaceInvadersUi.class);
    }
}