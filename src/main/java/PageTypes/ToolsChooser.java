package PageTypes;

import TestTools.ToolBox;
import org.openqa.selenium.remote.RemoteWebDriver;

import static TestTools.Variables.*;


public class ToolsChooser {

    public static RemoteWebDriver driver;
    //    Logger log;
//    Logger log2;
    public static ToolBox tools;
    //private org.testng.ITestResult ITestResult;

    public ToolsChooser(RemoteWebDriver driver) {
        this.driver = driver;
//        log = Logger.getLogger (getClass ());
//        log2 = Logger.getLogger (getClass ());
        tools = new ToolBox (driver);
    }

    // пишемо у лог-файл те, що замовлять
    public static void protocolirenChooser() {
        tools.protocolirenTool (writeSomethingIntoLogFile);
    }


    //   public static void protocoliren3Chooser() {tools.protocoliren3Tool (writeSomethingIntoLogFile3);}

    public static void sendEmail() {
        ToolBox.sendEmailTool (new String[]{sendEmailTo, sendEmailFrom, sendEmailHost});
    }

    public static void emailAttachmentChooser() {
        ToolBox.emailAttachmentTool (new String[]{sendEmailTo, sendEmailFrom, sendEmailHost});
    }


    public static void clickOnButtonChooser() {
        tools.clickOnButtonTool (currentButtonToClick);
    }


    //перевірка URL сторінки на яку ми потрапили
    public String whatUrlWeGotToChooser(String currentURL) {
        return tools.whatUrlWeGotToTool (currentURL);
    }

    public void whatUrlWeAreAtDEVorPRODChooser(String currentURL) {
        tools.whatUrlWeAreAtDEVorPRODTool (currentURL);
    }

}
