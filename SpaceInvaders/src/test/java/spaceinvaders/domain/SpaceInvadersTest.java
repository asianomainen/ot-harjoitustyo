package spaceinvaders.domain;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SpaceInvadersTest extends ApplicationTest {

    public static int WIDTH = 1000;
    public static int HEIGHT = 700;
    ColorInput color = new ColorInput();
    Blend blend = new Blend(BlendMode.DIFFERENCE);
    Button btnStart;
    private Button btnSettings;
    Button btnHighScores;
    Button btnExit;

    @Override
    public void start(Stage stage) {
        // Create scene for main menu
        VBox vbButtons = new VBox();
        vbButtons.setSpacing(20);
        vbButtons.setAlignment(Pos.CENTER);
        vbButtons.setPrefSize(WIDTH, HEIGHT);

        Scene mainMenu = new Scene(vbButtons);

        btnStart = new Button("Start Game");
        btnStart.setStyle("-fx-font-size:40");
        btnSettings = new Button("Settings");
        btnSettings.setStyle("-fx-font-size:40");
        btnHighScores = new Button("High Scores");
        btnHighScores.setStyle("-fx-font-size:40");
        btnExit = new Button("Exit");
        btnExit.setStyle("-fx-font-size:40");

        vbButtons.getChildren().addAll(btnStart, btnSettings, btnHighScores, btnExit);


        // Create scene for settings menu
        GridPane stgGrid = new GridPane();
        stgGrid.setAlignment(Pos.CENTER);
        stgGrid.setPrefSize(WIDTH, HEIGHT);
        stgGrid.setHgap(20);
        stgGrid.setVgap(20);

        Scene stgMenu = new Scene(stgGrid);

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


        // Create scene for High Scores
        VBox hsVBox = new VBox();
        hsVBox.setSpacing(20);
        hsVBox.setPadding(new Insets(20, 20, 20, 20));
        hsVBox.setPrefSize(WIDTH, HEIGHT);

        TableView<String> hsTable = new TableView<>();
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
        hsTable.setPrefSize(Integer.MAX_VALUE, Integer.MAX_VALUE);


        // Setting button actions
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

            } else {
                color.setPaint(Color.BLACK);

            }
            color.setWidth(Double.MAX_VALUE);
            color.setHeight(Double.MAX_VALUE);
            blend.setBottomInput(color);
            vbButtons.setEffect(blend);
            hsVBox.setEffect(blend);
            stgGrid.setEffect(blend);
        });


        // High Scores button functions
        btnHighScores.setOnAction(event -> stage.setScene(hsMenu));
        hsBackButton.setOnAction(event -> stage.setScene(mainMenu));


        // Exit button functions
        btnExit.setOnAction(event -> stage.close());


        stage.setScene(mainMenu);
        stage.setTitle("Space Invaders");
        stage.show();
    }

    @Test
    public void startGameButtonHasCorrectText() {
        Assertions.assertThat(btnStart).toString().equals("Start Game");
    }

    @Test
    public void settingsButtonHasCorrectText() {
        Assertions.assertThat(btnSettings).toString().equals("Settings");
    }

    @Test
    public void highScoresButtonHasCorrectText() {
        Assertions.assertThat(btnHighScores).toString().equals("High Scores");
    }

    @Test
    public void exitButtonHasCorrectText() {
        Assertions.assertThat(btnExit).toString().equals("Exit");
    }
}
