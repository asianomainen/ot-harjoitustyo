package spaceinvadersapp.ui;

import spaceinvadersapp.domain.PlayerShip;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class SpaceInvadersUi extends Application {
    public static int WIDTH = 1280;
    public static int HEIGHT = 720;
    ColorInput color = new ColorInput();
    Blend blend = new Blend(BlendMode.DIFFERENCE);

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

        PlayerShip playerShip = new PlayerShip(640, 650, Color.ORANGERED);
        Label pressEscToPause = new Label("Press ESC to return to Main Menu");

        pane.getChildren().addAll(playerShip.getShape(), pressEscToPause);

        Scene game = new Scene(pane);

        Map<KeyCode, Boolean> pressedKeys = new HashMap<>();

        game.setOnKeyPressed(event -> {
            pressedKeys.put(event.getCode(), Boolean.TRUE);

            if (event.getCode().equals(KeyCode.ESCAPE)) {
                stage.setScene(mainMenu);
                pressedKeys.clear();
            }
        });

        game.setOnKeyReleased(event -> {
            pressedKeys.put(event.getCode(), Boolean.FALSE);
        });

        new AnimationTimer() {

            @Override
            public void handle(long presentTime) {
                if (pressedKeys.getOrDefault(KeyCode.LEFT, false)) {
                    playerShip.moveLeft();
                }

                if (pressedKeys.getOrDefault(KeyCode.RIGHT, false)) {
                    playerShip.moveRight();
                }
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
        playerName.setMinWidth(700);
        TableColumn time = new TableColumn("Time");
        time.setStyle("-fx-font-size:30");
        time.setMinWidth(200);
        hsTable.getColumns().addAll(ranking, playerName, time);

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

    public static void main(String[] args) {
        launch(SpaceInvadersUi.class);
    }
}