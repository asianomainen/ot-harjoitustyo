package spaceinvadersapp.dao;

import com.google.api.services.sheets.v4.model.BatchGetValuesResponse;

public interface HighScoreDao {

    boolean addHighScore(String name, String time, String points) throws Exception;

    BatchGetValuesResponse getHighScores() throws Exception;
}
