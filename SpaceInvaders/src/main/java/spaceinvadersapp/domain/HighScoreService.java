package spaceinvadersapp.domain;

import com.google.api.services.sheets.v4.model.BatchGetValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import javafx.scene.control.TableView;
import spaceinvadersapp.dao.FileHighScoreDao;
import spaceinvadersapp.dao.GoogleAuthorizeUtil;
import spaceinvadersapp.dao.HighScoreDao;
import spaceinvadersapp.dao.SheetsServiceUtil;

/**
 * Class that handles the creation of new high scores.
 */

public class HighScoreService {
    GoogleAuthorizeUtil googleAuthorizeUtil;
    SheetsServiceUtil sheetsServiceUtil;
    HighScoreDao hsDao;

    /**
     * Creates a new high score manager.
     *
     * @param   gau   google authorization class. Reads the json file to check for credentials.
     * @param   ssu   google sheets service class. Checks that the user is authorized to use the Google Sheet API.
     *
     * @see spaceinvadersapp.dao.GoogleAuthorizeUtil
     * @see spaceinvadersapp.dao.SheetsServiceUtil
     */

    public HighScoreService(GoogleAuthorizeUtil gau, SheetsServiceUtil ssu, String spreadsheetID) {
        this.googleAuthorizeUtil = gau;
        this.sheetsServiceUtil = ssu;
        this.hsDao = new FileHighScoreDao(spreadsheetID);
    }

    /**
     * Creates a new high score manager.
     *
     * @param   highScore   adds a new high score to the database.
     */

    public boolean addNewHighScore(HighScore highScore) {
        try {
            hsDao.addHighScore(highScore.getName(), highScore.getTime(), highScore.getPoints());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Makes a database query for all the data in the database.
     *
     * @return    BatchGetValuesResponse   the result of the databse query.
     */

    public BatchGetValuesResponse getAllHighScores() {
        BatchGetValuesResponse result;
        try {
            result = hsDao.getHighScores();
        } catch (Exception e) {
            return null;
        }
        return result;
    }

    /**
     * Writes the TOP 10 high scores to the high score table.
     */

    public void writeScoresToTable(TableView<HighScore> hsTable) {
        BatchGetValuesResponse range = getAllHighScores();
        ValueRange names = range.getValueRanges().get(0);
        ValueRange times = range.getValueRanges().get(1);
        ValueRange points = range.getValueRanges().get(2);

        if (names.getValues() == null) {
            return;
        }

        for (int i = 0; i < names.getValues().size(); i++) {
            hsTable.getItems().add(new HighScore(names.getValues().get(i).get(0).toString(),
                    String.valueOf(times.getValues().get(i).get(0)),
                    String.valueOf(points.getValues().get(i).get(0))));
        }
    }
}