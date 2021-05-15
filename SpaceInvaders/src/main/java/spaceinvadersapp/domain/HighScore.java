package spaceinvadersapp.domain;

import javafx.beans.property.SimpleStringProperty;

public class HighScore {
    public SimpleStringProperty name;
    public SimpleStringProperty time;
    public SimpleStringProperty points;

    public HighScore(String name, String time, String points) {
        this.name = new SimpleStringProperty(name);
        this.time = new SimpleStringProperty(time);
        this.points = new SimpleStringProperty(points);
    }

    public String getName() {
        return name.get();
    }

    public String getTime() {
        return time.get();
    }

    public String getPoints() {
        return points.get();
    }
}