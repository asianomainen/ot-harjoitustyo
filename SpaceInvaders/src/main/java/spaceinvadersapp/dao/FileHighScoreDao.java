package spaceinvadersapp.dao;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.BatchGetValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

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
    public boolean addHighScore(String name, String time, String points) {
        ValueRange appendBody = new ValueRange().setValues(Arrays.asList(Arrays.asList(name, time, points)));

        try {
            sheetsService.spreadsheets().values()
                    .append(SPREADSHEET_ID, "A4", appendBody)
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
    public BatchGetValuesResponse getHighScores() throws IOException {
        List<String> ranges = Arrays.asList("E3:E12","F3:F12","G3:G12");
        BatchGetValuesResponse readResult = sheetsService.spreadsheets().values()
                .batchGet(SPREADSHEET_ID)
                .setRanges(ranges)
                .execute();
        return readResult;
    }
}