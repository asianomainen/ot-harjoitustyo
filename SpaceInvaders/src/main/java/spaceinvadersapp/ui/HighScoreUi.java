package spaceinvadersapp.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class HighScoreUi {
    public VBox hsVBox;
    public Button btnHighScoreBackToMainMenu;

    public HighScoreUi(int width, int height) {
        this.hsVBox = new VBox();
        this.hsVBox.setSpacing(20);
        this.hsVBox.setPadding(new Insets(20, 20, 20, 20));
        this.hsVBox.setPrefSize(width, height);

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

        this.btnHighScoreBackToMainMenu = new Button("Back to Main Menu");
        this.btnHighScoreBackToMainMenu.setStyle("-fx-font-size:30");
        this.hsVBox.setAlignment(Pos.CENTER);

        this.hsVBox.getChildren().addAll(hsTable, this.btnHighScoreBackToMainMenu);
    }

    public Scene getScene() {
        return new Scene(this.hsVBox);
    }
}
