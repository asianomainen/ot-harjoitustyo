package spaceinvadersapp.dao;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;

public class FileHighScoreDao implements HighScoreDao {
    private Sheets sheetsService;
    public String SPREADSHEET_ID = "1e9IFMWunUko426Z4eMbGNMGwWqPIPVMd3q1gWKos9IU";

    public FileHighScoreDao() {
        try {
            sheetsService = SheetsServiceUtil.getSheetsService();
        } catch (IOException | GeneralSecurityException e) {
            System.out.println("Getting the Google Sheets credentials has failed: ");
            e.printStackTrace();
        }
    }

    @Override
    public boolean addHighScore(String name, int time, int points) throws Exception {
        ValueRange appendBody = new ValueRange()
                .setValues(Arrays.asList(Arrays.asList(name, time, points)));

        try {
            sheetsService.spreadsheets().values()
                    .append(SPREADSHEET_ID, "B2", appendBody)
                    .setValueInputOption("RAW")
                    .setInsertDataOption("INSERT_ROWS")
                    .setIncludeValuesInResponse(true)
                    .execute();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<String> getHighScores() throws Exception {
        return null;
    }

    @Override
    public void setSpreadsheetId(String id) {
        this.SPREADSHEET_ID = id;
    }
}