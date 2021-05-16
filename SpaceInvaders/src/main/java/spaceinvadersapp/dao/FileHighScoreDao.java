package spaceinvadersapp.dao;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.BatchGetValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FileHighScoreDao implements HighScoreDao {
    private Sheets sheetsService;
    private final String spreadsheetID;

    public FileHighScoreDao(String spreadsheetID) {
        this.spreadsheetID = spreadsheetID;
        try {
            sheetsService = SheetsServiceUtil.getSheetsService();
        } catch (IOException | GeneralSecurityException e) {
            System.out.println("Getting the Google Sheets credentials has failed: ");
            e.printStackTrace();
        }
    }

    @Override
    public boolean addHighScore(String name, String time, String points) {
        ValueRange appendBody = new ValueRange().setValues(Collections.singletonList(Arrays.asList(name, Integer.valueOf(time), Integer.valueOf(points))));

        try {
            sheetsService.spreadsheets().values()
                    .append(spreadsheetID, "All scores!A4", appendBody)
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
        List<String> ranges = Arrays.asList("TOP10!A3:A12", "TOP10!B3:B12", "TOP10!C3:C12");
        return sheetsService.spreadsheets().values()
                .batchGet(spreadsheetID)
                .setRanges(ranges)
                .execute();
    }
}