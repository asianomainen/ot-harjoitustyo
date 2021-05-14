package spaceinvadersapp.ui;

import javafx.scene.control.CheckBox;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import spaceinvadersapp.domain.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.*;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

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
    private PauseGameUi pauseGameUi;
    private Scene gamePausePopupScene;
    private GameOverHighScoreUi gameOverHighScoreUi;
    private Scene gameOverHighScoreScene;
    private HashMap<KeyCode, Boolean> pressedKeys;
    private ArrayList<PlayerShip> players;
    private ArrayList<PlayerBullet> playerBullets;
    private ArrayList<EnemyShip> enemies;
    private ArrayList<EnemyBullet> enemyBullets;
    private ArrayList<GameWall> walls;
    private ShapeRemover shapeRemover;
    private BulletCollisionHandler bulletCollisionHandler;
    public static long immortalTime = 0;
    private long previousEnemyMovement = 0;
    private long enemyMovementIncrement = 1_100_000_000;
    private int killableCounter = 0;
    private int level = 5;
    private int enemyMovementCounter = 17;
    private double time = 0.0;
    private AtomicInteger gameTime;
    private long startTime = 0;
    private Stage pauseStage;
    private Stage highScoreStage;

    @Override
    public void init() {
        // DAO


        // UI
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

        // Create scene for pause menu popup in game
        pauseGameUi = new PauseGameUi(WIDTH, HEIGHT);
        gamePausePopupScene = pauseGameUi.getScene();

        // Create scene for high score popup after game
        gameOverHighScoreUi = new GameOverHighScoreUi(WIDTH, HEIGHT);
        gameOverHighScoreScene = gameOverHighScoreUi.getScene();


        // APPLICATION LOGIC
        // Creates hash map for handling pressed keys during game
        pressedKeys = new HashMap<>();

        // Creates array lists for handling shape creation and collision
        players = new ArrayList<>();
        playerBullets = new ArrayList<>();
        enemies = new ArrayList<>();
        enemyBullets = new ArrayList<>();
        walls = new ArrayList<>();

        // Creates atomic integer for keeping track of game time
        gameTime = new AtomicInteger();

        shapeRemover = new ShapeRemover(playerBullets, enemyBullets, players, enemies, walls, gameUi);
        bulletCollisionHandler = new BulletCollisionHandler(playerBullets, enemyBullets, players, enemies, walls, gameUi, new AtomicInteger());
    }

    @Override
    public void start(Stage stage) {
        players.add(gameUi.playerShip);

        gameScene.setOnKeyPressed(event -> pressedKeys.put(event.getCode(), Boolean.TRUE));
        gameScene.setOnKeyReleased(event -> pressedKeys.put(event.getCode(), Boolean.FALSE));

        AnimationTimer gameAnimation = new AnimationTimer() {

            @Override
            public void handle(long presentTime) {
                playerBullets.forEach(Shape::moveUp);
                enemyBullets.forEach(Shape::moveDown);
                shapeRemover.removeDeadShapes();
                bulletCollisionHandler.handleBulletCollisions();

                if (startTime == 0) {
                    startTime = presentTime;
                }

                if (level == 1) {
                    spawnWalls();
                }

                if (enemies.size() == 0 && level <= 5) {
                    spawnEnemies();
                    enemyMovementCounter = 17;
                    enemyMovementIncrement -= 200_000_000;
                    level++;
                }

                if (enemies.size() == 0 && level == 6) {
                    highScoreStage = new Stage();
                    highScoreStage.initModality(Modality.APPLICATION_MODAL);
                    highScoreStage.initStyle(StageStyle.UNDECORATED);
                    highScoreStage.initOwner(stage);
                    highScoreStage.setScene(gameOverHighScoreScene);
                    highScoreStage.show();
                    this.stop();
                }

                if (!isPaused) {
                    time += 0.02;
                    if (pressedKeys.getOrDefault(KeyCode.LEFT, false)) {
                        gameUi.playerShip.moveLeft();
                    }

                    if (pressedKeys.getOrDefault(KeyCode.RIGHT, false)) {
                        gameUi.playerShip.moveRight();
                    }

                    if (pressedKeys.getOrDefault(KeyCode.SPACE, false)) {
                        if (gameUi.playerShip.isAlive()) {
                            PlayerBullet playerBullet = new PlayerBullet((int) gameUi.playerShip.getShape().getTranslateX(),
                                    (int) gameUi.playerShip.getShape().getTranslateY(),
                                    Color.BLACK);
                            playerBullets.add(playerBullet);
                            gameUi.pane.getChildren().add(playerBullet.getShape());
                        }
                    }

                    gameScene.setOnKeyPressed(event -> {
                        pressedKeys.put(event.getCode(), Boolean.TRUE);
                        if (event.getCode().equals(KeyCode.ESCAPE)) {
                            isPaused = !isPaused;
                        }
                    });

                    // Game timer
                    if (presentTime - startTime >= 1_000_000_000) {
                        gameUi.gameTimeText.setText("Time: " + (gameTime.addAndGet(1)));
                        startTime = presentTime;
                    }

                    // Enemy movement
                    if (presentTime - previousEnemyMovement >= enemyMovementIncrement) {
                        enemyMovementCounter++;
                        if (enemyMovementCounter >= 34) {
                            enemyMovementCounter = 0;
                        }
                        enemies.forEach(Enemy -> Enemy.move(enemyMovementCounter));
                        previousEnemyMovement = presentTime;
                    }

                    // Checks if player has been shot and makes them immortal for 3 seconds
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
                                EnemyBullet enemyBullet = new EnemyBullet((int) enemies.get(random).getShape().getTranslateX(),
                                        (int) enemies.get(random).getShape().getTranslateY(),
                                        Color.CHOCOLATE);
                                enemyBullets.add(enemyBullet);
                                gameUi.pane.getChildren().add(enemyBullet.getShape());
                            }

                            time = 0;
                        }
                    }
                } else {
                    pauseStage = new Stage();
                    pauseStage.initModality(Modality.APPLICATION_MODAL);
                    pauseStage.initStyle(StageStyle.UNDECORATED);
                    pauseStage.initOwner(stage);
                    pauseStage.setScene(gamePausePopupScene);
                    pauseStage.show();
                    this.stop();
                }
            }
        };

        // Start game button functions
        mainMenuUi.btnStart.setOnAction(event -> {
            initNewGame();
            stage.setScene(gameScene);
            gameAnimation.start();
        });

        // Pause menu button functions
        pauseGameUi.btnResume.setOnAction(event -> {
            pauseStage.close();
            isPaused = false;
            pressedKeys.clear();
            gameAnimation.start();
        });

        applyColorSettings(pauseGameUi.invertColours, settingsUi.invertColours);

        pauseGameUi.btnPauseBackToMainMenu.setOnAction(event -> {
            stage.setScene(mainMenuScene);
            pauseStage.close();
            pressedKeys.clear();
        });

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

        applyColorSettings(settingsUi.invertColours, pauseGameUi.invertColours);

        // High Scores button functions
        mainMenuUi.btnHighScores.setOnAction(event -> stage.setScene(highScoreMenuScene));
        highScoreUi.btnHighScoreBackToMainMenu.setOnAction(event -> stage.setScene(mainMenuScene));

        // Game over High Scores button functions
        gameOverHighScoreUi.btnNewGame.setOnAction(event -> {
            initNewGame();
            highScoreStage.close();
            stage.setScene(gameScene);
            gameAnimation.start();
        });

        gameOverHighScoreUi.btnPauseBackToMainMenu.setOnAction(event -> {
            highScoreStage.close();
            pressedKeys.clear();
            stage.setScene(mainMenuScene);
        });

        // Exit button functions
        mainMenuUi.btnExit.setOnAction(event -> stage.close());

        // Finalizes stage
        stage.setResizable(false);
        stage.setScene(mainMenuScene);
        stage.setTitle("Space Invaders");
        stage.show();
    }

    private void applyColorSettings(CheckBox invertButton1, CheckBox invertButton2) {
        invertButton1.setOnAction((event) -> {
            if (invertButton1.isSelected()) {
                color.setPaint(Color.WHITE);
                invertButton2.setSelected(true);
            } else {
                color.setPaint(Color.BLACK);
                invertButton2.setSelected(false);
            }

            color.setWidth(Double.MAX_VALUE);
            color.setHeight(Double.MAX_VALUE);
            blend.setBottomInput(color);
            gameUi.pane.setEffect(blend);
            mainMenuUi.vbButtons.setEffect(blend);
            highScoreUi.hsVBox.setEffect(blend);
            settingsUi.stgGrid.setEffect(blend);
            pauseGameUi.stgGrid.setEffect(blend);
            gameOverHighScoreUi.highScoreGrid.setEffect(blend);
        });
    }

    public static int randomNumberGenerator(int min, int max) {
        // Add one to max to include it in possible random numbers generated
        max++;
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public void spawnWalls() {
        for (int i = 0; i < 4; i++) {
            walls.add(new GameWall((215 + i * 175), 580, Color.BLACK));
        }

        walls.forEach(wall -> gameUi.pane.getChildren().add(wall.getShape()));
    }

    public void spawnEnemies() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                enemies.add(new EnemyShip((230 + i * 55), (65 + j * 65), Color.PURPLE));
            }
        }

        enemies.forEach(enemy -> gameUi.pane.getChildren().add(enemy.getShape()));
    }

    public void initNewGame() {
        immortalTime = 0;
        previousEnemyMovement = 0;
        enemyMovementIncrement = 1_100_000_000;
        killableCounter = 0;
        level = 5;
        enemyMovementCounter = 17;
        time = 0.0;
        pressedKeys.clear();
        shapeRemover.removeAllShapes(playerBullets, enemyBullets, enemies, walls, gameUi);
        gameUi.playerShip.getShape().setTranslateX(WIDTH / 2.0);
        gameUi.playerShip.setLives(3);
        gameUi.gameTimeText.setText("Time: 0");
        gameUi.pointsText.setText("Points: 0");
    }

    public static void main(String[] args) {
        launch(SpaceInvadersUi.class);
    }
}