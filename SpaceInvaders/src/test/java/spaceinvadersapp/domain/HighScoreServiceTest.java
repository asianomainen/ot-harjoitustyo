package spaceinvadersapp.domain;

import de.saxsys.javafx.test.JfxRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import spaceinvadersapp.dao.GoogleAuthorizeUtil;
import spaceinvadersapp.dao.SheetsServiceUtil;
import spaceinvadersapp.ui.HighScoreUi;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.*;

@RunWith(JfxRunner.class)
public class HighScoreServiceTest {
    GoogleAuthorizeUtil googleAuthorizeUtil;
    SheetsServiceUtil sheetsServiceUtil;
    HighScoreService hsService;
    HighScoreUi highScoreUi;

    @Before
    public void setUp() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));
        String spreadsheetID = properties.getProperty("testSpreadsheetID");

        googleAuthorizeUtil = new GoogleAuthorizeUtil();
        sheetsServiceUtil = new SheetsServiceUtil();
        hsService = new HighScoreService(googleAuthorizeUtil, sheetsServiceUtil, spreadsheetID);

        highScoreUi = new HighScoreUi(960, 720);
    }

    @Test
    public void addNewHighScoreWorks() {
        HighScore hs = new HighScore("Marko", "22", "6600");
        assertTrue(hsService.addNewHighScore(hs));
    }
}