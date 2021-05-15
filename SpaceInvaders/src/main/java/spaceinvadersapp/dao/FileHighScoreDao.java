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
    private final String spreadsheetID = "1e9IFMWunUko426Z4eMbGNMGwWqPIPVMd3q1gWKos9IU";

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
        ValueRange appendBody = new ValueRange().setValues(Collections.singletonList(Arrays.asList(name, time, points)));

        try {
            sheetsService.spreadsheets().values()
                    .append(spreadsheetID, "A4", appendBody)
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
        List<String> ranges = Arrays.asList("E3:E12", "F3:F12", "G3:G12");
        return sheetsService.spreadsheets().values()
                .batchGet(spreadsheetID)
                .setRanges(ranges)
                .execute();
    }
}