package spaceinvadersapp.ui;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;

public class SettingsUi {
    GridPane stgGrid;
    CheckBox soundCheckBox;
    CheckBox invertColours;
    Button btnSettingsBackToMainMenu;

    public Scene createSettingsUi(int WIDTH, int HEIGHT) {
        stgGrid = new GridPane();
        stgGrid.setAlignment(Pos.CENTER);
        stgGrid.setPrefSize(WIDTH, HEIGHT);
        stgGrid.setHgap(20);
        stgGrid.setVgap(20);

        soundCheckBox = new CheckBox("Sound ON");
        soundCheckBox.setSelected(true);
        soundCheckBox.setStyle("-fx-font-size:40");

        invertColours = new CheckBox("Invert colours");
        invertColours.setStyle("-fx-font-size:40");

        btnSettingsBackToMainMenu = new Button("Back to Main Menu");
        btnSettingsBackToMainMenu.setStyle("-fx-font-size:30");
        GridPane.setHalignment(btnSettingsBackToMainMenu, HPos.CENTER);

        stgGrid.add(soundCheckBox, 0, 0);
        stgGrid.add(invertColours, 0, 1);
        stgGrid.add(btnSettingsBackToMainMenu, 0, 2);

        return new Scene(stgGrid);
    }
}
