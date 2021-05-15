package spaceinvadersapp.domain;

import spaceinvadersapp.dao.FileHighScoreDao;
import spaceinvadersapp.dao.GoogleAuthorizeUtil;
import spaceinvadersapp.dao.HighScoreDao;
import spaceinvadersapp.dao.SheetsServiceUtil;

import java.util.ArrayList;
import java.util.List;

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

    public List<String> getAllHighScores() {
        ArrayList<String> list;
        try {
            list = hsDao.getHighScores();
        } catch (Exception e) {
            return new ArrayList<>();
        }
        return list;
    }
}
