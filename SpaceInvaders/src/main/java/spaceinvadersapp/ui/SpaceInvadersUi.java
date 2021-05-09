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
    private boolean isPaused = false;
    private MainMenuUi mainMenuUi;
    private Scene mainMenuScene;
    private GameUi gameUi;
    private Scene gameScene;
    private SettingsUi settingsUi;
    private Scene settingsMenuScene;
    private HighScoreUi highScoreUi;
    private Scene highScoreMenuScene;
    private HashMap<KeyCode, Boolean> pressedKeys;
    private ArrayList<PlayerShip> players;
    private ArrayList<PlayerBullet> playerBullets;
    private ArrayList<EnemyShip> enemies;
    private ArrayList<EnemyBullet> enemyBullets;
    private ArrayList<BossEnemyShip> bosses;
    private AtomicInteger points;

    @Override
    public void init() {
        // DAO


        // Application logic
        // Create UI for main menu
        mainMenuUi = new MainMenuUi(WIDTH, HEIGHT);
        mainMenuScene = mainMenuUi.getScene();

        //Create scene for game
        gameUi = new GameUi(WIDTH, HEIGHT);
        gameScene = gameUi.getScene();

        // Create scene for settings menu
        settingsUi = new SettingsUi(WIDTH, HEIGHT);
        settingsMenuScene = settingsUi.getScene();

        // Create scene for High Scores
        highScoreUi = new HighScoreUi(WIDTH, HEIGHT);
        highScoreMenuScene = highScoreUi.getScene();

        /*        // Create scene for pause menu popup in game
        PauseGameUi pauseGameUi = new PauseGameUi();
        Popup gamePausePopup = pauseGameUi.createPauseGameUi(WIDTH, HEIGHT);*/

        // Creates hash map for handling pressed keys during game
        pressedKeys = new HashMap<>();

        // Creates array lists for handling shape creation and collision
        players = new ArrayList<>();
        playerBullets = new ArrayList<>();
        enemies = new ArrayList<>();
        enemyBullets = new ArrayList<>();
        bosses = new ArrayList<>();

        // Creates atomic integer for counting points during game
        points = new AtomicInteger();
    }

    @Override
    public void start(Stage stage) {

        //bosses.add(new BossEnemyShip(WIDTH / 2, 20, Color.CYAN));
        players.add(gameUi.playerShip);

        gameScene.setOnKeyPressed(event -> pressedKeys.put(event.getCode(), Boolean.TRUE));
        gameScene.setOnKeyReleased(event -> pressedKeys.put(event.getCode(), Boolean.FALSE));


        AnimationTimer animation = new AnimationTimer() {
            long previousEnemyMovement = 0;
            long enemyMovementIncrement = 1_100_000_000;
            long immortalTime = 0;
            int killableCounter = 0;
            int level = 1;
            int enemyMovementCounter = 17;
            double time = 0.0;

            @Override
            public void handle(long presentTime) {
                if (enemies.size() == 0 && level <= 5) {
                    spawnEnemies();
                    enemies.forEach(enemy -> gameUi.pane.getChildren().add(enemy.getShape()));
                    enemyMovementCounter = 17;
                    enemyMovementIncrement -= 200_000_000;
                    level++;
                }
                System.out.println(enemyMovementIncrement);
                if (!isPaused) {
                    time += 0.02;
                    if (pressedKeys.getOrDefault(KeyCode.LEFT, false)) {
                        gameUi.playerShip.moveLeft();
                    }

                    if (pressedKeys.getOrDefault(KeyCode.RIGHT, false)) {
                        gameUi.playerShip.moveRight();
                    }

                    if (pressedKeys.getOrDefault(KeyCode.SPACE, false) && playerBullets.size() < 1) {
                    //if (pressedKeys.getOrDefault(KeyCode.SPACE, false)) {
                        if (gameUi.playerShip.isAlive()) {
                            PlayerBullet playerBullet = new PlayerBullet((int) gameUi.playerShip.getShape().getTranslateX(), (int) gameUi.playerShip.getShape().getTranslateY(), Color.BLACK);
                            playerBullets.add(playerBullet);
                            gameUi.pane.getChildren().add(playerBullet.getShape());
                        }
                    }

                    playerBullets.forEach(Shape::moveUp);
                    enemyBullets.forEach(Shape::moveDown);

                    // Enemy movement
                    if (presentTime - previousEnemyMovement >= enemyMovementIncrement) {
                        enemyMovementCounter++;
                        if (enemyMovementCounter >= 34) {
                            enemyMovementCounter = 0;
                        }
                        enemies.forEach(Enemy -> Enemy.move(enemyMovementCounter));
                        previousEnemyMovement = presentTime;
                    }

                    // Checks is player is immortal
                    if (immortalTime > 0) {
                        if (killableCounter >= 12) {
                            gameUi.playerShip.getShape().setFill(Color.ORANGERED);
                            gameUi.playerShip.setImmortal(false);
                            killableCounter = 0;
                            immortalTime = 0;
                        } else {
                            if (presentTime - immortalTime >= 250_000_000) {
                                killableCounter++;
                                if (gameUi.playerShip.getShape().getFill() == Color.ORANGERED) {
                                    gameUi.playerShip.getShape().setFill(Color.BLACK);
                                } else {
                                    gameUi.playerShip.getShape().setFill(Color.ORANGERED);
                                }

                                immortalTime = presentTime;
                            }
                        }
                    }

                    // Enemy shooting
                    if (enemies.size() >= 1) {
                        if (time > 1) {
                            if (Math.random() > 0.5) {
                                int random = randomNumberGenerator(0, enemies.size() - 1);
                                EnemyBullet enemyBullet = new EnemyBullet((int) enemies.get(random).getShape().getTranslateX(), (int) enemies.get(random).getShape().getTranslateY(), Color.CHOCOLATE);
                                enemyBullets.add(enemyBullet);
                                gameUi.pane.getChildren().add(enemyBullet.getShape());
                            }

                            time = 0;
                        }
                    }

                    //bulletCollisionHandler(playerBullets, enemies, gameUi, points, 100);

                    playerBullets.forEach(bullet -> enemies.forEach(enemy -> {
                        if (bullet.collision(enemy)) {
                            bullet.setAlive(false);
                            enemy.setAlive(false);
                            gameUi.pointsText.setText("Points: " + (points.addAndGet(100)));
                        }
                    }));

                    enemyBullets.forEach(bullet -> players.forEach(player -> {
                        if (bullet.collision(player) && !player.isImmortal()) {
                            bullet.setAlive(false);
                            if (player.getLives() <= 1) {
                                player.setAlive(false);
                            }
                            immortalTime++;
                            player.die();
                            player.setImmortal(true);
                            gameUi.pointsText.setText("Points: " + (points.addAndGet(-500)));
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

                gameScene.setOnKeyPressed(event -> {
                             /*                       System.out.println("Yep!\n\n\n\n\n\n\n\n\n\n\n\n");

                        if (!isPaused) {
                            isPaused = true;
                            gameUi.pane.setEffect(new GaussianBlur());
                            gamePausePopup.show(stage);
                            //pop-up here
                        }

                        if (isPaused) {
                            isPaused = false;
                            gamePausePopup.hide();
                        }*/

                    pressedKeys.put(event.getCode(), Boolean.TRUE);
                    if (event.getCode().equals(KeyCode.ESCAPE)) {
                        isPaused = !isPaused;
                    }
                });
            }
        };
        animation.start();

        // Start game button functions
        mainMenuUi.btnStart.setOnAction(event -> stage.setScene(gameScene));

        // Settings button functions
        mainMenuUi.btnSettings.setOnAction(event -> stage.setScene(settingsMenuScene));
        settingsUi.btnSettingsBackToMainMenu.setOnAction(event -> stage.setScene(mainMenuScene));
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
        mainMenuUi.btnHighScores.setOnAction(event -> stage.setScene(highScoreMenuScene));
        highScoreUi.btnHighScoreBackToMainMenu.setOnAction(event -> stage.setScene(mainMenuScene));

        // Exit button functions
        mainMenuUi.btnExit.setOnAction(event -> stage.close());

        // Finalizes stage
        stage.setResizable(false);
        stage.setScene(mainMenuScene);
        stage.setTitle("Space Invaders");
        stage.show();
    }

    @Override
    public void stop() {
    }

    public static int randomNumberGenerator(int min, int max) {
        // Add one to max to include it in possible random numbers generated
        max++;
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public void spawnEnemies() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                enemies.add(new EnemyShip((230 + i * 55), (65 + j * 65), Color.PURPLE));
            }
        }
    }

/*    public static void bulletCollisionHandler(ArrayList<?> bullets, ArrayList<?> target, GameUi gameUi, AtomicInteger pointCounter, int points) {
        bullets.forEach(bullet -> target.forEach(enemy -> {
            if (bullet.collision(enemy)) {
                bullet.setAlive(false);
                enemy.setAlive(false);
                gameUi.pointsText.setText("Points: " + (pointCounter.addAndGet(points)));
            }
        }));
    }*/

    public static void main(String[] args) {
        launch(SpaceInvadersUi.class);
    }
}