package spaceinvadersapp.ui;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 * Creates the main menu UI.
 */

public class MainMenuUi {
    public Button btnStart;
    public Button btnSettings;
    public Button btnHighScores;
    public Button btnExit;
    public VBox vbButtons;

    public MainMenuUi(int width, int height) {
        this.vbButtons = new VBox();
        this.vbButtons.setSpacing(20);
        this.vbButtons.setAlignment(Pos.CENTER);
        this.vbButtons.setPrefSize(width, height);

        this.btnStart = new Button("Start/Resume Game");
        this.btnStart.setStyle("-fx-font-size:40");
        this.btnSettings = new Button("Settings");
        this.btnSettings.setStyle("-fx-font-size:40");
        this.btnHighScores = new Button("High Scores");
        this.btnHighScores.setStyle("-fx-font-size:40");
        this.btnExit = new Button("Exit");
        this.btnExit.setStyle("-fx-font-size:40");

        this.vbButtons.getChildren().addAll(this.btnStart, this.btnSettings, this.btnHighScores, this.btnExit);
    }

    /**
     * Creates new a new Scene from the UI.
     *
     * @return Scene returns a new scene from the UI
     */

    public Scene getScene() {
        return new Scene(this.vbButtons);
    }
}
