package spaceinvadersapp.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import spaceinvadersapp.domain.HighScore;

/**
 * Creates the high score menu UI.
 */

public class HighScoreUi {
    public VBox hsVBox;
    public Button btnHighScoreBackToMainMenu;
    public TableView<HighScore> hsTable;

    public HighScoreUi(int width, int height) {
        this.hsVBox = new VBox();
        this.hsVBox.setSpacing(20);
        this.hsVBox.setPadding(new Insets(20, 20, 90, 20));
        this.hsVBox.setPrefSize(width, height);

        hsTable = new TableView<>();
        hsTable.setPrefSize(Integer.MAX_VALUE, Integer.MAX_VALUE);

        TableColumn playerNameColumn = new TableColumn<>("Name");
        playerNameColumn.setStyle("-fx-font-size:30");
        playerNameColumn.setMinWidth(418);

        TableColumn timeColumn = new TableColumn<>("Time (s)");
        timeColumn.setStyle("-fx-font-size:30");
        timeColumn.setMinWidth(200);

        TableColumn pointsColumn = new TableColumn<>("Points");
        pointsColumn.setStyle("-fx-font-size:30");
        pointsColumn.setMinWidth(300);
        
        playerNameColumn.setCellValueFactory(new PropertyValueFactory<HighScore, String>("name"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<HighScore, String>("time"));
        pointsColumn.setCellValueFactory(new PropertyValueFactory<HighScore, String>("points"));

        //hsTable.getColumns().addAll(rankingColumn, playerNameColumn, timeColumn, pointsColumn);
        hsTable.getColumns().addAll(playerNameColumn, timeColumn, pointsColumn);

        this.btnHighScoreBackToMainMenu = new Button("Back to Main Menu");
        this.btnHighScoreBackToMainMenu.setStyle("-fx-font-size:30");
        this.hsVBox.setAlignment(Pos.CENTER);

        this.hsVBox.getChildren().addAll(hsTable, this.btnHighScoreBackToMainMenu);
    }

    /**
     * Creates new a new Scene from the UI.
     *
     * @return scene returns a new scene from the UI
     */

    public Scene getScene() {
        return new Scene(this.hsVBox);
    }
}
