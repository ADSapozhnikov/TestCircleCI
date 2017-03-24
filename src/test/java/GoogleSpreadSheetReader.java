/**
 * Created by Eugene.Korogodsky on 22.11.2016.
 */


import TestTools.Variables;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.util.ServiceException;

import javax.naming.AuthenticationException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GoogleSpreadSheetReader {


    public static void main(String[] args)
            throws AuthenticationException, MalformedURLException, IOException, ServiceException {
        Variables.todayFunnelsList = new ArrayList<String> ();
        SpreadsheetService service =
                new SpreadsheetService ("QA1");

        // TODO: Authorize the service object for a specific user (see other sections)

        // Define the URL to request. This should never change.
        // It was used sub-cycling to get data from al pages of GoogleSpreadSheet
        String shits[] = Variables.googleShits;

        int k = 0;
        while (k != shits.length) {
            URL SPREADSHEET_FEED_URL = new URL (shits[k]);

            // Make a request to the API and get all spreadsheets.
            SpreadsheetFeed feed = service.getFeed (SPREADSHEET_FEED_URL, SpreadsheetFeed.class);
            List<SpreadsheetEntry> spreadsheets = feed.getEntries ();

            // Iterate through all of the spreadsheets returned
            for (SpreadsheetEntry spreadsheet : spreadsheets) {
                // Print the title of this spreadsheet to the screen
                int i = 0;
                String a = spreadsheet.getTitle ().getPlainText ();
                Variables.todayFunnelsList.add (a);
                i++;
            }


            int i = 0;
            int j = Variables.todayFunnelsList.size ();

            while (i != j) {

                System.out.println (i + 1 + " " + Variables.todayFunnelsList.get (i));
                i++;
            }
            k++;
        }
        System.out.println ("Total quantity of funnels are " + Variables.todayFunnelsList.size ());
    }
}

