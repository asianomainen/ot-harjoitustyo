package spaceinvadersapp.ui;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class SaveHighScoreUi {
    public Button saveScore;
    public Button cancel;
    public GridPane newScoreGrid;
    private final TextField nameInput;
    private Text playerTime;
    private Text playerPoints;

    public SaveHighScoreUi(int width, int height) {
        this.newScoreGrid = new GridPane();
        this.newScoreGrid.setAlignment(Pos.CENTER);
        this.newScoreGrid.setPrefSize(width / 2.0, height / 2.0);
        this.newScoreGrid.setHgap(10);
        this.newScoreGrid.setVgap(10);
        this.newScoreGrid.setStyle("-fx-background-color: white,#b5b5b5, " +
                "linear-gradient(#fdfdfd, #e1e1e1), " +
                "linear-gradient(#eeeeee, #d9d9d9)");

        Label newScoreText = new Label("New High Score!");
        newScoreText.setStyle("-fx-font-size:40");
        GridPane.setHalignment(newScoreText, HPos.CENTER);

        Label nameText = new Label("Type name to save score");
        nameText.setStyle("-fx-font-size:20");
        GridPane.setHalignment(nameText, HPos.CENTER);

        this.nameInput = new TextField();
        GridPane.setHalignment(this.nameInput, HPos.CENTER);

        this.saveScore = new Button("Save High Score");
        this.saveScore.setStyle("-fx-font-size:20");
        GridPane.setHalignment(this.saveScore, HPos.CENTER);

        this.cancel = new Button("Cancel");
        this.cancel.setStyle("-fx-font-size:15");
        GridPane.setHalignment(this.cancel, HPos.CENTER);

        this.newScoreGrid.add(newScoreText, 0, 0);
        this.newScoreGrid.add(nameText, 0, 2);
        this.newScoreGrid.add(this.nameInput, 0, 3);
        this.newScoreGrid.add(this.saveScore, 0, 8);
        this.newScoreGrid.add(this.cancel, 0, 9);
    }

    public Scene getScene() {
        return new Scene(this.newScoreGrid);
    }

    public String getPlayerName() {
        return this.nameInput.getText();
    }

    public void setPlayerTime(int time) {
        this.newScoreGrid.getChildren().remove(playerTime);
        this.playerTime = new Text("Time: " + time);
        playerTime.setStyle("-fx-font-size:30");
        GridPane.setHalignment(this.playerTime, HPos.CENTER);
        this.newScoreGrid.add(this.playerTime, 0, 5);
    }

    public void setPlayerPoints(int points) {
        this.newScoreGrid.getChildren().remove(playerPoints);
        this.playerPoints = new Text("Points: " + points);
        playerPoints.setStyle("-fx-font-size:30");
        GridPane.setHalignment(this.playerPoints, HPos.CENTER);
        this.newScoreGrid.add(this.playerPoints, 0, 6);
    }
}