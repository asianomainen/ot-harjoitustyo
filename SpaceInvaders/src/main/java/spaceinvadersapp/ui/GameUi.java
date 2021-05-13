package spaceinvadersapp.ui;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import spaceinvadersapp.domain.PlayerShip;

import java.util.concurrent.atomic.AtomicInteger;

public class GameUi {
    public Pane pane;
    public Text pointsText;
    public Text gameTimeText;
    public PlayerShip playerShip;

    public GameUi(int width, int height) {
        this.pane = new Pane();
        this.pane.setPrefSize(width, height);

        this.pointsText = new Text(width - 150, 20, "Points: 0");
        this.pointsText.setStyle("-fx-font-size:20");

        this.gameTimeText = new Text(width - 138, 60, "Time: 0");
        this.gameTimeText.setStyle("-fx-font-size:20");

        Label pressEscToPause = new Label("Press ESC to pause/resume game");
        pressEscToPause.setStyle("-fx-font-size:20");

        this.playerShip = new PlayerShip(width / 2, 650, Color.ORANGERED);

        this.pane.getChildren().addAll(this.pointsText, this.playerShip.getShape(), pressEscToPause, this.gameTimeText);
    }

    public Scene getScene() {
        return new Scene(this.pane);
    }
}
