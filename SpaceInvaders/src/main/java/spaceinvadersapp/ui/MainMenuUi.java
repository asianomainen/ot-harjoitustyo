package spaceinvadersapp.ui;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class MainMenuUi {
    Button btnStart;
    Button btnSettings;
    Button btnHighScores;
    Button btnExit;
    VBox vbButtons;

    public Scene createMainMenu(int WIDTH, int HEIGHT) {
        vbButtons = new VBox();
        vbButtons.setSpacing(20);
        vbButtons.setAlignment(Pos.CENTER);
        vbButtons.setPrefSize(WIDTH, HEIGHT);

        btnStart = new Button("Start Game");
        btnStart.setStyle("-fx-font-size:40");
        btnSettings = new Button("Settings");
        btnSettings.setStyle("-fx-font-size:40");
        btnHighScores = new Button("High Scores");
        btnHighScores.setStyle("-fx-font-size:40");
        btnExit = new Button("Exit");
        btnExit.setStyle("-fx-font-size:40");

        vbButtons.getChildren().addAll(btnStart, btnSettings, btnHighScores, btnExit);

        return new Scene(vbButtons);
    }
}
