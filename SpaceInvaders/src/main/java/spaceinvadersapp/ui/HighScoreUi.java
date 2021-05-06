package spaceinvadersapp.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class HighScoreUi {
    VBox hsVBox;
    TableView<String> hsTable;
    TableColumn ranking;
    TableColumn playerName;
    TableColumn time;
    TableColumn score;
    Button btnHighScoreBackToMainMenu;

    public Scene createHighScoreUi(int WIDTH, int HEIGHT) {
        hsVBox = new VBox();
        hsVBox.setSpacing(20);
        hsVBox.setPadding(new Insets(20, 20, 20, 20));
        hsVBox.setPrefSize(WIDTH, HEIGHT);

        hsTable = new TableView<>();
        hsTable.setPrefSize(Integer.MAX_VALUE, Integer.MAX_VALUE);

        ranking = new TableColumn("#");
        ranking.setStyle("-fx-font-size:30");
        ranking.setMinWidth(100);

        playerName = new TableColumn("Name");
        playerName.setStyle("-fx-font-size:30");
        playerName.setMinWidth(420);

        time = new TableColumn("Time");
        time.setStyle("-fx-font-size:30");
        time.setMinWidth(200);

        score = new TableColumn("Score");
        score.setStyle("-fx-font-size:30");
        score.setMinWidth(200);

        hsTable.getColumns().addAll(ranking, playerName, time, score);

        btnHighScoreBackToMainMenu = new Button("Back to Main Menu");
        btnHighScoreBackToMainMenu.setStyle("-fx-font-size:30");
        hsVBox.setAlignment(Pos.CENTER);

        hsVBox.getChildren().addAll(hsTable, btnHighScoreBackToMainMenu);

        return new Scene(hsVBox);
    }
}
