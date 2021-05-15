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

/*        TableColumn rankingColumn = new TableColumn("#");
        rankingColumn.setStyle("-fx-font-size:30");
        rankingColumn.setMinWidth(100);*/

        TableColumn playerNameColumn = new TableColumn<>("Name");
        playerNameColumn.setStyle("-fx-font-size:30");
        playerNameColumn.setMinWidth(418);

        TableColumn timeColumn = new TableColumn<>("Time (s)");
        timeColumn.setStyle("-fx-font-size:30");
        timeColumn.setMinWidth(200);

        TableColumn pointsColumn = new TableColumn<>("Points");
        pointsColumn.setStyle("-fx-font-size:30");
        pointsColumn.setMinWidth(300);

        //rankingColumn.setCellValueFactory(new PropertyValueFactory<String, String>("1"));
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

    public Scene getScene() {
        return new Scene(this.hsVBox);
    }
}
