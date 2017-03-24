import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.WebDriver;

public class Browser {

    public static WebDriver getDriver(String browser) throws MalformedURLException {
        return new WebDriver(new URL("http://localhost:4444/wd/hub"), getBrowserCapabilities(browser));

    }

    private static DesiredCapabilities getBrowserCapabilities(String browserType) {
 //       System.setProperty("webdriver.chrome.driver","C:\\JavaWorkSpace\\web_drivers\\chromedriver.exe");
        switch (browserType) {
            case "firefox":
                System.out.println("Opening firefox driver");
                return DesiredCapabilities.firefox();
            case "chrome":
                System.out.println("Opening chrome driver");
                return DesiredCapabilities.chrome();
            case "IE":
                System.out.println("Opening IE driver");
                return DesiredCapabilities.internetExplorer();
            default:
                System.out.println("browser : " + browserType + " is invalid, Launching Firefox as browser of choice..");
                return DesiredCapabilities.firefox();
        }
    }
}