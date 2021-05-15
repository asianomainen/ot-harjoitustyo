package spaceinvadersapp.dao;

import java.util.ArrayList;

public interface HighScoreDao {

    boolean addHighScore(String name, int time, int points) throws Exception;

    ArrayList<String> getHighScores() throws Exception;

    void setSpreadsheetId(String id);
}
