package spaceinvadersapp.ui;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import spaceinvadersapp.domain.PlayerShip;

public class GameUi {
    public Pane pane;
    public Text pointsText;
    public PlayerShip playerShip;
    public Label pressEscToPause;

    public GameUi(int width, int height) {
        this.pane = new Pane();
        this.pane.setPrefSize(width, height);

        this.pointsText = new Text(width - 150, 20, "Points: 0");
        this.pointsText.setStyle("-fx-font-size:20");
        this.playerShip = new PlayerShip(width / 2, 650, Color.ORANGERED);
        this.pressEscToPause = new Label("Press ESC to pause/resume game");
        this.pressEscToPause.setStyle("-fx-font-size:20");

        this.pane.getChildren().addAll(this.pointsText, this.playerShip.getShape(), this.pressEscToPause);
    }

    public Scene getScene() {
        return new Scene(this.pane);
    }
}
