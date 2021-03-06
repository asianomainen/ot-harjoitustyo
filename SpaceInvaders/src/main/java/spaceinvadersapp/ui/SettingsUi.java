package spaceinvadersapp.ui;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;

/**
 * Creates the settings menu UI.
 */

public class SettingsUi {
    public GridPane stgGrid;
    public CheckBox invertColours;
    public Button btnSettingsBackToMainMenu;

    public SettingsUi(int width, int height) {
        this.stgGrid = new GridPane();
        this.stgGrid.setAlignment(Pos.CENTER);
        this.stgGrid.setPrefSize(width, height);
        this.stgGrid.setHgap(20);
        this.stgGrid.setVgap(20);

        this.invertColours = new CheckBox("Invert colours");
        this.invertColours.setStyle("-fx-font-size:40");

        this.btnSettingsBackToMainMenu = new Button("Back to Main Menu");
        this.btnSettingsBackToMainMenu.setStyle("-fx-font-size:30");
        GridPane.setHalignment(this.btnSettingsBackToMainMenu, HPos.CENTER);

        this.stgGrid.add(this.invertColours, 0, 0);
        this.stgGrid.add(this.btnSettingsBackToMainMenu, 0, 1);
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
