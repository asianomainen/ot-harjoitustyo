package spaceinvadersapp.domain;

public class HighScore {
    public String name;
    public int time;
    public int points;

    public HighScore(String name, int time, int points) {
        this.name = name;
        this.time = time;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public int getTime() {
        return time;
    }

    public int getPoints() {
        return points;
    }
}