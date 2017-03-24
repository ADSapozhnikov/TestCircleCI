import PageTypes.ToolsChooser;
import TestTools.Variables;
import org.openqa.selenium.Point;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import static TestTools.Variables.currentDate;
import static java.lang.Thread.sleep;

public class BfunnelTest {

    public WebDriver driver;

    ToolsChooser toolsChooser;


    @Test
    public void testPageTitleInChrome() throws Exception {


        GoogleSpreadSheetReader.main (null);
        GooglePrivateSpreadSheetReader.main (null);
        int i = 0;
        int j = Variables.todayFunnelsList.size ();
        //  int m = j+1;


        while (i != j) {

            driver = new WebDriver (DesiredCapabilities.chrome ());
            //     розгорнуто
            //       driver.manage ().window ().maximize ();
            //     сховано
            // driver.manage ().window ().setSize (new Dimension (1920, 1080));
             driver.manage().window().setPosition(new Point (-2000,0));
            //     driver.navigate ().to ("https://accounts.google.com/");

            toolsChooser = new ToolsChooser (driver);


            int k = i + 1;

            // йдемо на потрібну сторінку
            driver.navigate ().to (Variables.todayFunnelsList.get (i));
            //зачистка куків
            //??? ToolsChooser.tools.deleteAllCookiesTool (Variables.todayFunnelsList.get (i));
            sleep (2000);
            Variables.strPageTitle = driver.getTitle ();

            findTitleForThisURL ();




            Variables.writeSomethingIntoLogFile = "";
            ToolsChooser.protocolirenChooser ();

            if (Variables.todayFunnelsList.get (i).startsWith ("http://tracking")) {
                Variables.writeSomethingIntoLogFile = "" + k + " (of total " + j + " - the second list)";
                ToolsChooser.protocolirenChooser ();
                Variables.writeSomethingIntoLogFile = "The " + Variables.todayFunnelsList.get (i);
                ToolsChooser.protocolirenChooser ();
                Variables.writeSomethingIntoLogFile = "leads to ";
                ToolsChooser.protocolirenChooser ();
                Variables.writeSomethingIntoLogFile = Variables.tmpString;
                ToolsChooser.protocolirenChooser ();

                System.out.println ("We are checking:           \"" + Variables.todayFunnelsList.get (i)+"\"");
                System.out.println ("We are at:                 \"" + driver.getCurrentUrl ()+"\"");
                System.out.println ("Variables.appTitle is:     \"" + Variables.appTitle+"\"");
                System.out.println ("Variables.strPageTitle is: \"" + Variables.strPageTitle+"\"");

                if (Variables.strPageTitle.equals (Variables.appTitle)) {

                    Variables.writeSomethingIntoLogFile = "which is available online";
                    ToolsChooser.protocolirenChooser ();
                    Variables.writeSomethingIntoLogFile = "(" + Variables.appTitle + ")";
                    ToolsChooser.protocolirenChooser ();
                    Variables.writeSomethingIntoLogFile = "";
                    ToolsChooser.protocolirenChooser ();
                    Variables.writeSomethingIntoLogFile = "-------------------------------------------";
                    ToolsChooser.protocolirenChooser ();
                } else {
                    Variables.writeSomethingIntoLogFile = "Can't access the " + Variables.tmpString;
                    ToolsChooser.protocolirenChooser ();
                    Variables.writeSomethingIntoLogFile = "";
                    ToolsChooser.protocolirenChooser ();
                    Variables.writeSomethingIntoLogFile = "-------------------------------------------";
                    ToolsChooser.protocolirenChooser ();
                    Variables.sendEmailText = "Can't access the " + Variables.tmpString;
                    ToolsChooser.sendEmail ();
                }


//                Variables.writeSomethingIntoLogFile = "(" + Variables.appTitle + ")";
//                ToolsChooser.protocolirenChooser ( );
            } else {

                System.out.println ("We are checking:           \"" + Variables.todayFunnelsList.get (i)+"\"");
                System.out.println ("We are at:                 \"" + driver.getCurrentUrl ()+"\"");
                System.out.println ("Variables.appTitle is:     \"" + Variables.appTitle+"\"");
                System.out.println ("Variables.strPageTitle is: \"" + Variables.strPageTitle+"\"");

                if (Variables.strPageTitle.equals (Variables.appTitle)) {
                    Variables.writeSomethingIntoLogFile = "" + k + " (of total " + j + " - the first list)";
                    ToolsChooser.protocolirenChooser ();
                    Variables.writeSomethingIntoLogFile = "The " + Variables.todayFunnelsList.get (i);
                    ToolsChooser.protocolirenChooser ();
                    Variables.writeSomethingIntoLogFile = "is available online";
                    ToolsChooser.protocolirenChooser ();
                    Variables.writeSomethingIntoLogFile = "(" + Variables.appTitle + ")";
                    ToolsChooser.protocolirenChooser ();
                } else {
                    Variables.writeSomethingIntoLogFile = "Can't access the " + Variables.todayFunnelsList.get (i);
                    ToolsChooser.protocolirenChooser ();
                    Variables.sendEmailText = "Can't access the " + Variables.todayFunnelsList.get (i);
                    ToolsChooser.sendEmail ();
                }
                Variables.writeSomethingIntoLogFile = "";
                ToolsChooser.protocolirenChooser ();
                Variables.writeSomethingIntoLogFile = "-------------------------------------------";
                ToolsChooser.protocolirenChooser ();

            }


            // Закриваємо браузер
            if (driver != null) {
                System.out.println ("Closing browser");
                driver.quit ();
            }
            i++;
        }
        Variables.sendEmailSubject = "Funnels avalability log of the " + currentDate;
        ToolsChooser.emailAttachmentChooser ();
    }


// У цьому методі записано заголовки (тайтли) усіх фьюнелів
public void findTitleForThisURL() throws Exception {
    sleep (2000);
    Variables.tmpString = driver.getCurrentUrl ();
    int l = 0;

    while (l != Variables.totalFunnelsLinks.size ()) {
        if (Variables.tmpString.startsWith (Variables.totalFunnelsLinks.get (l))) {
            Variables.appTitle = Variables.totalFunnelsTitles.get (l);
        }
        l++;
    }


    }
}



