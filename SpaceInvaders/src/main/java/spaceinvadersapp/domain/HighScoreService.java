package spaceinvadersapp.domain;

import com.google.api.services.sheets.v4.model.BatchGetValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import javafx.scene.control.TableView;
import spaceinvadersapp.dao.FileHighScoreDao;
import spaceinvadersapp.dao.GoogleAuthorizeUtil;
import spaceinvadersapp.dao.HighScoreDao;
import spaceinvadersapp.dao.SheetsServiceUtil;

public class HighScoreService {
    GoogleAuthorizeUtil googleAuthorizeUtil;
    SheetsServiceUtil sheetsServiceUtil;
    HighScoreDao hsDao;

    public HighScoreService(GoogleAuthorizeUtil gau, SheetsServiceUtil ssu) {
        this.googleAuthorizeUtil = gau;
        this.sheetsServiceUtil = ssu;
        this.hsDao = new FileHighScoreDao();
    }

    public boolean addNewHighScore(HighScore highScore) {
        try {
            hsDao.addHighScore(highScore.getName(), highScore.getTime(), highScore.getPoints());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public BatchGetValuesResponse getAllHighScores() {
        BatchGetValuesResponse result;
        try {
            result = hsDao.getHighScores();
        } catch (Exception e) {
            return null;
        }
        return result;
    }

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