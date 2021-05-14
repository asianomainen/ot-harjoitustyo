package spaceinvadersapp.ui;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class GameOverHighScoreUi {
    Button btnNewGame;
    GridPane highScoreGrid;
    Button btnPauseBackToMainMenu;

    public GameOverHighScoreUi(int width, int height) {
        this.highScoreGrid = new GridPane();
        this.highScoreGrid.setAlignment(Pos.CENTER);
        this.highScoreGrid.setPrefSize(width / 2.0, height / 2.0);
        this.highScoreGrid.setHgap(10);
        this.highScoreGrid.setVgap(10);
        this.highScoreGrid.setStyle("-fx-background-color: white,#b5b5b5, " +
                                    "linear-gradient(#fdfdfd, #e1e1e1), " +
                                    "linear-gradient(#eeeeee, #d9d9d9)");

        this.btnNewGame = new Button("New Game");
        this.btnNewGame.setStyle("-fx-font-size:20");
        GridPane.setHalignment(btnNewGame, HPos.CENTER);

        this.btnPauseBackToMainMenu = new Button("Back to Main Menu");
        this.btnPauseBackToMainMenu.setStyle("-fx-font-size:20");
        GridPane.setHalignment(btnPauseBackToMainMenu, HPos.CENTER);

        this.highScoreGrid.add(this.btnNewGame, 0, 0);
        this.highScoreGrid.add(this.btnPauseBackToMainMenu, 0, 1);
    }

    public Scene getScene() {
        return new Scene(this.highScoreGrid);
    }
}

