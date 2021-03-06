package spaceinvadersapp.ui;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;

/**
 * Creates the pause menu UI.
 */

public class PauseGameUi {
    Button btnResume;
    GridPane stgGrid;
    CheckBox invertColours;
    Button btnPauseBackToMainMenu;

    public PauseGameUi(int width, int height) {
        this.stgGrid = new GridPane();
        this.stgGrid.setAlignment(Pos.CENTER);
        this.stgGrid.setPrefSize(width / 2.0, height / 2.0);
        this.stgGrid.setHgap(10);
        this.stgGrid.setVgap(10);
        this.stgGrid.setStyle("-fx-background-color: white,#b5b5b5, " +
                              "linear-gradient(#fdfdfd, #e1e1e1), " +
                              "linear-gradient(#eeeeee, #d9d9d9)");

        this.btnResume = new Button("Resume Game");
        this.btnResume.setStyle("-fx-font-size:30");

        this.invertColours = new CheckBox("Invert colours");
        this.invertColours.setStyle("-fx-font-size:30");

        this.btnPauseBackToMainMenu = new Button("Back to Main Menu");
        this.btnPauseBackToMainMenu.setStyle("-fx-font-size:20");
        GridPane.setHalignment(btnPauseBackToMainMenu, HPos.CENTER);

        this.stgGrid.add(this.btnResume, 0, 0);
        this.stgGrid.add(this.invertColours, 0, 1);
        this.stgGrid.add(this.btnPauseBackToMainMenu, 0, 2);
    }

    /**
     * Creates new a new Scene from the UI.
     *
     * @return Scene returns a new scene from the UI
     */

    public Scene getScene() {
        return new Scene(this.stgGrid);
    }
}
