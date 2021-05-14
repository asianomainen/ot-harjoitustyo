package spaceinvadersapp.ui;

import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
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
    private Scene gamePausePopup;
    private HashMap<KeyCode, Boolean> pressedKeys;
    private ArrayList<PlayerShip> players;
    private ArrayList<PlayerBullet> playerBullets;
    private ArrayList<EnemyShip> enemies;
    private ArrayList<EnemyBullet> enemyBullets;
    private DeadShapeRemover deadShapeRemover;
    private BulletCollisionHandler bulletCollisionHandler;
    public static long immortalTime = 0;
    private long previousEnemyMovement = 0;
    private long enemyMovementIncrement = 1_100_000_000;
    private int killableCounter = 0;
    private int level = 1;
    private int enemyMovementCounter = 17;
    private double time = 0.0;
    private AtomicInteger gameTime;
    private long startTime = 0;
    private Stage pauseStage;

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

        // Create scene for pause menu popup in game
        pauseGameUi = new PauseGameUi(WIDTH, HEIGHT);
        gamePausePopup = pauseGameUi.getScene();

        // Creates hash map for handling pressed keys during game
        pressedKeys = new HashMap<>();

        // Creates array lists for handling shape creation and collision
        players = new ArrayList<>();
        playerBullets = new ArrayList<>();
        enemies = new ArrayList<>();
        enemyBullets = new ArrayList<>();
        //bosses = new ArrayList<>();

        // Creates atomic integer for counting points during game
        //private ArrayList<BossEnemyShip> bosses;
        AtomicInteger points = new AtomicInteger();
        gameTime = new AtomicInteger();

        deadShapeRemover = new DeadShapeRemover(playerBullets, enemyBullets, players, enemies, gameUi);
        bulletCollisionHandler = new BulletCollisionHandler(playerBullets, enemyBullets, players, enemies, gameUi, points);
    }

    @Override
    public void start(Stage stage) {
        //bosses.add(new BossEnemyShip(WIDTH / 2, 20, Color.CYAN));
        players.add(gameUi.playerShip);

        gameScene.setOnKeyPressed(event -> pressedKeys.put(event.getCode(), Boolean.TRUE));
        gameScene.setOnKeyReleased(event -> pressedKeys.put(event.getCode(), Boolean.FALSE));

        AnimationTimer gameAnimation = new AnimationTimer() {

            @Override
            public void handle(long presentTime) {
                if (startTime == 0) {
                    startTime = presentTime;
                }

                if (enemies.size() == 0 && level <= 5) {
                    spawnEnemies();
                    enemyMovementCounter = 17;
                    enemyMovementIncrement -= 200_000_000;
                    level++;
                }

                if (!isPaused) {
                    time += 0.02;
                    if (pressedKeys.getOrDefault(KeyCode.LEFT, false)) {
                        gameUi.playerShip.moveLeft();
                    }

                    if (pressedKeys.getOrDefault(KeyCode.RIGHT, false)) {
                        gameUi.playerShip.moveRight();
                    }

                    //if (pressedKeys.getOrDefault(KeyCode.SPACE, false)) {
                    if (pressedKeys.getOrDefault(KeyCode.SPACE, false) && playerBullets.size() < 1) {
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

                    playerBullets.forEach(Shape::moveUp);
                    enemyBullets.forEach(Shape::moveDown);
                    deadShapeRemover.removeDeadShapes();
                    bulletCollisionHandler.handleBulletCollisions();

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
                    pauseStage.setScene(gamePausePopup);
                    pauseStage.show();
                    this.stop();
                }
            }
        };

        // Start game button functions
        mainMenuUi.btnStart.setOnAction(event -> {
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

        pauseGameUi.invertColours.setOnAction((event) -> {
            if (pauseGameUi.invertColours.isSelected()) {
                color.setPaint(Color.WHITE);
                settingsUi.invertColours.setSelected(true);
            } else {
                color.setPaint(Color.BLACK);
                settingsUi.invertColours.setSelected(false);
            }

            color.setWidth(Double.MAX_VALUE);
            color.setHeight(Double.MAX_VALUE);
            blend.setBottomInput(color);
            gameUi.pane.setEffect(blend);
            mainMenuUi.vbButtons.setEffect(blend);
            highScoreUi.hsVBox.setEffect(blend);
            settingsUi.stgGrid.setEffect(blend);
            pauseGameUi.stgGrid.setEffect(blend);
        });

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

        settingsUi.invertColours.setOnAction((event) -> {
            if (settingsUi.invertColours.isSelected()) {
                color.setPaint(Color.WHITE);
                pauseGameUi.invertColours.setSelected(true);
            } else {
                color.setPaint(Color.BLACK);
                pauseGameUi.invertColours.setSelected(false);
            }

            color.setWidth(Double.MAX_VALUE);
            color.setHeight(Double.MAX_VALUE);
            blend.setBottomInput(color);
            gameUi.pane.setEffect(blend);
            mainMenuUi.vbButtons.setEffect(blend);
            highScoreUi.hsVBox.setEffect(blend);
            settingsUi.stgGrid.setEffect(blend);
            pauseGameUi.stgGrid.setEffect(blend);
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

        enemies.forEach(enemy -> gameUi.pane.getChildren().add(enemy.getShape()));
    }

    public static void main(String[] args) {
        launch(SpaceInvadersUi.class);
    }
}