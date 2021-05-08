package spaceinvadersapp.ui;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import spaceinvadersapp.domain.PlayerShip;

public class GameUi {
    Pane pane;
    Text pointsText;
    PlayerShip playerShip;
    Label pressEscToPause;

    public Scene createGameUi(int WIDTH, int HEIGHT) {
        pane = new Pane();
        pane.setPrefSize(WIDTH, HEIGHT);

        pointsText = new Text(WIDTH - 150, 20, "Points: 0");
        pointsText.setStyle("-fx-font-size:20");
        playerShip = new PlayerShip(WIDTH / 2, 650, Color.ORANGERED);
        pressEscToPause = new Label("Press ESC to pause/resume game");
        pressEscToPause.setStyle("-fx-font-size:20");

        pane.getChildren().addAll(pointsText, playerShip.getShape(), pressEscToPause);

        return new Scene(pane);
    }
}
