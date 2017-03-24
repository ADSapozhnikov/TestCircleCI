/**
 * Created by Eugene.Korogodsky on 07.02.2017.
 */
import TestTools.Variables;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.CustomElementCollection;
import com.google.gdata.data.spreadsheet.ListEntry;
import com.google.gdata.data.spreadsheet.ListFeed;
import com.google.gdata.util.ServiceException;

import javax.naming.AuthenticationException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class GooglePrivateSpreadSheetReader {
    // stolen from here http://www.awasthiashish.com/2015/10/read-data-from-google-spreadsheet.html
    public static void main(String[] args) throws ClassNotFoundException, AuthenticationException, MalformedURLException, IOException, ServiceException {


        Variables.buFunnelsLinks = new ArrayList<String> ();
        Variables.buFunnelsTitles = new ArrayList<String> ();
        Variables.currentFunnelsLinks = new ArrayList<String> ();
        Variables.currentFunnelsTitles = new ArrayList<String> ();
        Variables.currentFunnelsSecondStepLinks = new ArrayList<String> ();
        Variables.currentFunnelsSecondStepTitles = new ArrayList<String> ();
        Variables.currentDomainsLinks = new ArrayList<String> ();
        Variables.totalFunnelsLinks = new ArrayList<String> ();
        Variables.totalFunnelsTitles = new ArrayList<String> ();

        SpreadsheetService service = new SpreadsheetService("QAFAS");
        //NB! new SpreadsheetService("....") have to be unique!!!

        // TODO: Authorize the service object for a specific user (see other sections)
//        try {

        String shits[] = Variables.googleShits2;


        int i = 0;
        while (i != shits.length) {

            URL url = new URL (shits[i]);


            // Get Feed of Spreadsheet url
            ListFeed lf = service.getFeed (url, ListFeed.class);

            if((url).toString ().contains ("1/public/values") )
            {
                for (ListEntry le : lf.getEntries ()) {
                    CustomElementCollection cec = le.getCustomElements ();
                    //Pass column name to access it's cell values
                    Variables.currentDomainsLinks.add(cec.getValue ("URL"));
                }
            }

            else if ((url).toString ().contains ("2/public/values")) {
                //Iterate over feed to get cell value
                for (ListEntry le : lf.getEntries ()) {
                    CustomElementCollection cec = le.getCustomElements ();
                    //Pass column name to access it's cell values
                    Variables.currentFunnelsLinks.add(cec.getValue ("URL"));
                    Variables.currentFunnelsTitles.add(cec.getValue ("Title"));
                    Variables.currentFunnelsSecondStepLinks.add(cec.getValue ("Second-Step-URL"));
                    Variables.currentFunnelsSecondStepTitles.add(cec.getValue ("Second-Step-Title"));
                }
            }
            else if ((url).toString ().contains ("3/public/values")) {
                for (ListEntry le : lf.getEntries ()) {
                    CustomElementCollection cec = le.getCustomElements ();
                    //Pass column name to access it's cell values
                    Variables.buFunnelsLinks.add(cec.getValue ("URL"));
                    Variables.buFunnelsTitles.add(cec.getValue ("Title"));
                }
            }
            i++;

        }
//            }catch (IOException e) {
//                e.printStackTrace();
//            } catch (ServiceException e) {
//                e.printStackTrace();
//            }
        System.out.println ("++++++++++++++++++++++++++");
        for (String domain : Variables.currentDomainsLinks) {
            System.out.println ("Domain: " + domain);
        }
        System.out.println ("==========================");
        int j = 0;
        while (j != Variables.currentFunnelsLinks.size ()) {
            System.out.println ("Funnel: " + Variables.currentFunnelsLinks.get (j)
                    + " Title: " + Variables.currentFunnelsTitles.get (j)
                    + " Step2 URL: " + Variables.currentFunnelsSecondStepLinks.get (j)
                    + " Step2 Title: " + Variables.currentFunnelsSecondStepTitles.get (j));
            j++;
        }
        System.out.println ("==========================");
        int k = 0;
        while (k != Variables.buFunnelsLinks.size ()) {
            System.out.println ("BU-Funnel: " + Variables.buFunnelsLinks.get (k)
                    + " Title: " + Variables.buFunnelsTitles.get (k));
            k++;
        }
        System.out.println ("==========================");

        System.out.println ("Total Domains Quantity is " + Variables.currentDomainsLinks.size ());
        System.out.println ("Total Funnels Quantity is " + Variables.currentFunnelsLinks.size ());
        System.out.println ("Total BU-Funnels Quantity is " + Variables.buFunnelsLinks.size ());


        int l = 0;
        while (l != Variables.currentDomainsLinks.size ())

        {int m = 0;
            while ( m != Variables.currentFunnelsLinks.size ())
            {

                Variables.totalFunnelsLinks.add (Variables.currentDomainsLinks.get (l)+"/"+Variables.currentFunnelsLinks.get(m));
                Variables.totalFunnelsTitles. add (Variables.currentFunnelsTitles.get(m));
                m++;
            }
            l++;
        }



        int m = 0;
        while (m != Variables.buFunnelsLinks.size ()) {
            Variables.totalFunnelsLinks.add (Variables.buFunnelsLinks.get (m));
            Variables.totalFunnelsTitles.add (Variables.buFunnelsTitles.get (m));
            m++;
        }
        System.out.println (Variables.totalFunnelsLinks.size () + " "+Variables.totalFunnelsTitles.size ());
    }


}



