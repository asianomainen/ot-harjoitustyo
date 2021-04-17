package spaceinvaders.domain;

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

public class SpaceInvadersApplication extends Application {
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
        Platform.runLater(pane::requestFocus);
        pane.requestFocus();
        pane.setPrefSize(WIDTH, HEIGHT);

        PlayerShip ship = new PlayerShip(640, 650);

        Button gameBackButton = new Button("Back to Main Menu");
        gameBackButton.setDefaultButton(false);
        gameBackButton.setTranslateX(0);
        gameBackButton.setTranslateY(0);
        gameBackButton.setOnAction(event -> stage.setScene(mainMenu));

        pane.getChildren().addAll(ship.getShape(), gameBackButton);

        Scene game = new Scene(pane);

        Map<KeyCode, Boolean> pressedKeys = new HashMap<>();

        game.setOnKeyPressed(event -> {
            pressedKeys.put(event.getCode(), Boolean.TRUE);
        });

        game.setOnKeyReleased(event -> {
            pressedKeys.put(event.getCode(), Boolean.FALSE);
        });

        new AnimationTimer() {

            @Override
            public void handle(long presentTime) {
                if (pressedKeys.getOrDefault(KeyCode.LEFT, false)) {
                    ship.moveLeft();
                }

                if (pressedKeys.getOrDefault(KeyCode.RIGHT, false)) {
                    ship.moveRight();
                }
            }
        }.start();


        // Create scene for settings menu
        GridPane stgGrid = new GridPane();
        stgGrid.setAlignment(Pos.CENTER);
        stgGrid.setPrefSize(WIDTH, HEIGHT);
        stgGrid.setHgap(20);
        stgGrid.setVgap(20);

        CheckBox soundCheck = new CheckBox("Sound ON");
        soundCheck.setSelected(true);
        soundCheck.setStyle("-fx-font-size:40");

        CheckBox invertColours = new CheckBox("Invert colours");
        invertColours.setStyle("-fx-font-size:40");

        Button stgBackButton = new Button("Back to Main Menu");
        stgBackButton.setStyle("-fx-font-size:30");
        GridPane.setHalignment(stgBackButton, HPos.CENTER);

        stgGrid.add(soundCheck, 0, 0);
        stgGrid.add(invertColours, 0, 1);
        stgGrid.add(stgBackButton, 0, 2);

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

        Button hsBackButton = new Button("Back to Main Menu");
        hsBackButton.setStyle("-fx-font-size:30");
        hsVBox.setAlignment(Pos.CENTER);

        hsVBox.getChildren().addAll(hsTable, hsBackButton);

        Scene hsMenu = new Scene(hsVBox);


        // Start game button functions
        btnStart.setOnAction(event -> stage.setScene(game));


        // Settings button functions
        btnSettings.setOnAction(event -> stage.setScene(stgMenu));
        stgBackButton.setOnAction(event -> stage.setScene(mainMenu));
        soundCheck.setOnAction(event -> {
            if (soundCheck.isSelected()) {
                soundCheck.setText("Sound ON");
            } else {
                soundCheck.setText("Sound OFF");
            }
        });
        invertColours.setOnAction((event) -> {
            if (invertColours.isSelected()) {
                color.setPaint(Color.WHITE);
                ship.setColor(Color.GREEN);
            } else {
                color.setPaint(Color.BLACK);
                ship.setColor(Color.ORANGERED);
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
        hsBackButton.setOnAction(event -> stage.setScene(mainMenu));


        // Exit button functions
        btnExit.setOnAction(event -> stage.close());


        stage.setResizable(false);
        stage.setScene(mainMenu);
        stage.setTitle("Space Invaders");
        stage.show();
    }

    public static void main(String[] args) {
        launch(SpaceInvadersApplication.class);
    }
}