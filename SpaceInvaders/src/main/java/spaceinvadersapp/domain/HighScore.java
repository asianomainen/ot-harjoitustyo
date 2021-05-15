package spaceinvadersapp.domain;

import javafx.beans.property.SimpleStringProperty;

/**
 * Class for creating a new high score.
 */

public class HighScore {
    public SimpleStringProperty name;
    public SimpleStringProperty time;
    public SimpleStringProperty points;

    /**
     * Creates a new high score.
     */

    public HighScore(String name, String time, String points) {
        this.name = new SimpleStringProperty(name);
        this.time = new SimpleStringProperty(time);
        this.points = new SimpleStringProperty(points);
    }

    /**
     * Returns the name of the player who achieved the high score.
     */

    public String getName() {
        return name.get();
    }

    /**
     * Returns the game time of the player who achieved the high score.
     */

    public String getTime() {
        return time.get();
    }

    /**
     * Returns the points of the player who achieved the high score.
     */

    public String getPoints() {
        return points.get();
    }
}