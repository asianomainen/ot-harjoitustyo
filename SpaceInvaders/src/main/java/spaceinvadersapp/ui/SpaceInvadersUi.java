package spaceinvadersapp.ui;

import javafx.scene.text.Text;
import spaceinvadersapp.domain.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * class for creating the ui
 */

public class SpaceInvadersUi extends Application {
    private final static int WIDTH = 960;
    private final static int HEIGHT = 720;
    private final ColorInput color = new ColorInput();
    private final Blend blend = new Blend(BlendMode.DIFFERENCE);
    private double time = 0.0;

    @Override
    public void start(Stage stage) {
        // Create scene for main menu
        VBox vbButtons = new VBox();
        vbButtons.setSpacing(20);
        vbButtons.setAlignment(Pos.CENTER);
        vbButtons.setPrefSize(WIDTH, HEIGHT);

        Button btnStart = new Button("Start Game");
        btnStart.setStyle("-fx-font-size:40");
        Button btnSettings = new Button("Settings");
        btnSettings.setStyle("-fx-font-size:40");
        Button btnHighScores = new Button("High Scores");
        btnHighScores.setStyle("-fx-font-size:40");
        Button btnExit = new Button("Exit");
        btnExit.setStyle("-fx-font-size:40");

        vbButtons.getChildren().addAll(btnStart, btnSettings, btnHighScores, btnExit);

        Scene mainMenu = new Scene(vbButtons);


        //Create scene for game
        Pane pane = new Pane();
        pane.setPrefSize(WIDTH, HEIGHT);

        Text pointsText = new Text(WIDTH - 150, 20, "Points: 0");
        pointsText.setStyle("-fx-font-size:20");
        PlayerShip playerShip = new PlayerShip(640, 650, Color.ORANGERED);
        Label pressEscToPause = new Label("Press ESC to return to Main Menu");
        AtomicInteger points = new AtomicInteger();

        pane.getChildren().addAll(pointsText, playerShip.getShape(), pressEscToPause);

        Scene game = new Scene(pane);

        Map<KeyCode, Boolean> pressedKeys = new HashMap<>();
        ArrayList<PlayerShip> players = new ArrayList<>();
        ArrayList<PlayerBullet> playerBullets = new ArrayList<>();
        ArrayList<EnemyShip> enemies = new ArrayList<>();
        ArrayList<EnemyBullet> enemyBullets = new ArrayList<>();
        ArrayList<BossEnemyShip> bosses = new ArrayList<>();

        for (int i = 3; i <= 12; i++) {
            for (int j = 1; j <= 5; j++) {
                enemies.add(new EnemyShip((i * 60), (j * 65), Color.PURPLE));
            }
        }

        enemies.forEach(enemy -> pane.getChildren().add(enemy.getShape()));
        bosses.add(new BossEnemyShip(WIDTH / 2, 20, Color.CYAN));
        players.add(playerShip);

        game.setOnKeyPressed(event -> {
            pressedKeys.put(event.getCode(), Boolean.TRUE);

            if (event.getCode().equals(KeyCode.ESCAPE)) {
                stage.setScene(mainMenu);
                pressedKeys.clear();
            }
        });

        game.setOnKeyReleased(event -> pressedKeys.put(event.getCode(), Boolean.FALSE));

        new AnimationTimer() {

            @Override
            public void handle(long presentTime) {
                time += 0.05;
                if (pressedKeys.getOrDefault(KeyCode.LEFT, false)) {
                    playerShip.moveLeft();
                }

                if (pressedKeys.getOrDefault(KeyCode.RIGHT, false)) {
                    playerShip.moveRight();
                }

                if (pressedKeys.getOrDefault(KeyCode.SPACE, false) && playerBullets.size() < 1) {
                    PlayerBullet playerBullet = new PlayerBullet((int) playerShip.getShape().getTranslateX(), (int) playerShip.getShape().getTranslateY(), Color.BLACK);
                    playerBullets.add(playerBullet);
                    pane.getChildren().add(playerBullet.getShape());
                }

                if (time > 2) {
                    if (Math.random() > 0.5) {
                        int random = randomNumberGenerator(0, enemies.size() - 1);
                        EnemyBullet enemyBullet = new EnemyBullet((int) enemies.get(random).getShape().getTranslateX(), (int) enemies.get(random).getShape().getTranslateY(), Color.CHOCOLATE);
                        enemyBullets.add(enemyBullet);
                        pane.getChildren().add(enemyBullet.getShape());
                    }

                    time = 0;
                }

                playerBullets.forEach(Shape::moveUp);
                enemyBullets.forEach(Shape::moveDown);

                playerBullets.stream()
                        .filter(Shape::outOfBounds)
                        .forEach(bullet -> pane.getChildren().remove(bullet.getShape()));
                playerBullets.removeAll(playerBullets.stream()
                        .filter(Shape::outOfBounds)
                        .collect(Collectors.toList()));

                enemyBullets.stream()
                        .filter(Shape::outOfBounds)
                        .forEach(bullet -> pane.getChildren().remove(bullet.getShape()));
                enemyBullets.removeAll(enemyBullets.stream()
                        .filter(Shape::outOfBounds)
                        .collect(Collectors.toList()));

                playerBullets.forEach(bullet -> enemies.forEach(enemy -> {
                    if (bullet.collision(enemy)) {
                        bullet.setAlive(false);
                        enemy.setAlive(false);
                        pointsText.setText("Points: " + (points.addAndGet(100)));
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
                        pointsText.setText("Points: " + (points.addAndGet(500)));
                    }
                }));

                playerBullets.stream()
                        .filter(bullet -> !bullet.isAlive())
                        .forEach(bullet -> pane.getChildren().remove(bullet.getShape()));
                playerBullets.removeAll(playerBullets.stream()
                        .filter(bullet -> !bullet.isAlive())
                        .collect(Collectors.toList()));

                enemyBullets.stream()
                        .filter(bullet -> !bullet.isAlive())
                        .forEach(bullet -> pane.getChildren().remove(bullet.getShape()));
                enemyBullets.removeAll(enemyBullets.stream()
                        .filter(bullet -> !bullet.isAlive())
                        .collect(Collectors.toList()));

                enemies.stream()
                        .filter(enemy -> !enemy.isAlive())
                        .forEach(enemy -> pane.getChildren().remove(enemy.getShape()));
                enemies.removeAll(enemies.stream()
                        .filter(enemy -> !enemy.isAlive())
                        .collect(Collectors.toList()));

                players.stream()
                        .filter(enemy -> !enemy.isAlive())
                        .forEach(enemy -> pane.getChildren().remove(enemy.getShape()));
                players.removeAll(players.stream()
                        .filter(enemy -> !enemy.isAlive())
                        .collect(Collectors.toList()));

                bosses.stream()
                        .filter(boss -> !boss.isAlive())
                        .forEach(boss -> pane.getChildren().remove(boss.getShape()));
                bosses.removeAll(bosses.stream()
                        .filter(boss -> !boss.isAlive())
                        .collect(Collectors.toList()));
            }
        }.start();


        // Create scene for settings menu
        GridPane stgGrid = new GridPane();
        stgGrid.setAlignment(Pos.CENTER);
        stgGrid.setPrefSize(WIDTH, HEIGHT);
        stgGrid.setHgap(20);
        stgGrid.setVgap(20);

        CheckBox soundCheckBox = new CheckBox("Sound ON");
        soundCheckBox.setSelected(true);
        soundCheckBox.setStyle("-fx-font-size:40");

        CheckBox invertColours = new CheckBox("Invert colours");
        invertColours.setStyle("-fx-font-size:40");

        Button btnSettingsBackToMainMenu = new Button("Back to Main Menu");
        btnSettingsBackToMainMenu.setStyle("-fx-font-size:30");
        GridPane.setHalignment(btnSettingsBackToMainMenu, HPos.CENTER);

        stgGrid.add(soundCheckBox, 0, 0);
        stgGrid.add(invertColours, 0, 1);
        stgGrid.add(btnSettingsBackToMainMenu, 0, 2);

        Scene stgMenu = new Scene(stgGrid);


        // Create scene for High Scores
        VBox hsVBox = new VBox();
        hsVBox.setSpacing(20);
        hsVBox.setPadding(new Insets(20, 20, 20, 20));
        hsVBox.setPrefSize(WIDTH, HEIGHT);

        TableView<String> hsTable = new TableView<>();
        hsTable.setPrefSize(Integer.MAX_VALUE, Integer.MAX_VALUE);

        TableColumn ranking = new TableColumn("#");
        ranking.setStyle("-fx-font-size:30");
        ranking.setMinWidth(100);

        TableColumn playerName = new TableColumn("Name");
        playerName.setStyle("-fx-font-size:30");
        playerName.setMinWidth(420);

        TableColumn time = new TableColumn("Time");
        time.setStyle("-fx-font-size:30");
        time.setMinWidth(200);

        TableColumn score = new TableColumn("Score");
        score.setStyle("-fx-font-size:30");
        score.setMinWidth(200);

        hsTable.getColumns().addAll(ranking, playerName, time, score);

        Button btnHighScoreBackToMainMenu = new Button("Back to Main Menu");
        btnHighScoreBackToMainMenu.setStyle("-fx-font-size:30");
        hsVBox.setAlignment(Pos.CENTER);

        hsVBox.getChildren().addAll(hsTable, btnHighScoreBackToMainMenu);

        Scene hsMenu = new Scene(hsVBox);


        // Start game button functions
        btnStart.setOnAction(event -> stage.setScene(game));


        // Settings button functions
        btnSettings.setOnAction(event -> stage.setScene(stgMenu));
        btnSettingsBackToMainMenu.setOnAction(event -> stage.setScene(mainMenu));
        soundCheckBox.setOnAction(event -> {
            if (soundCheckBox.isSelected()) {
                soundCheckBox.setText("Sound ON");
            } else {
                soundCheckBox.setText("Sound OFF");
            }
        });
        invertColours.setOnAction((event) -> {
            if (invertColours.isSelected()) {
                color.setPaint(Color.WHITE);
            } else {
                color.setPaint(Color.BLACK);
            }

            color.setWidth(Double.MAX_VALUE);
            color.setHeight(Double.MAX_VALUE);
            blend.setBottomInput(color);
            pane.setEffect(blend);
            vbButtons.setEffect(blend);
            hsVBox.setEffect(blend);
            stgGrid.setEffect(blend);
        });


        // High Scores button functions
        btnHighScores.setOnAction(event -> stage.setScene(hsMenu));
        btnHighScoreBackToMainMenu.setOnAction(event -> stage.setScene(mainMenu));


        // Exit button functions
        btnExit.setOnAction(event -> stage.close());


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