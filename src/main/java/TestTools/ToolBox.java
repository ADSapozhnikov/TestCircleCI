package TestTools;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import static TestTools.Config.getCfgValue;
import static TestTools.Config.ui;
import static TestTools.Variables.currentDate;


/**
        * Created by Eugene.Korogodsky on 9/11/2016.
        * УВАГА!!! бібліотеку javax.mail maven самостійно не підтягує - треба її додавати вручну
        * звідси: http://www.java2s.com/Code/Jar/j/Downloadjavaxmailjar.htm
        * і класти до папки з jar-файлами, а потім прописати у Project Structure / Libraries
        * або прописати залежність у POM.xml - файлі
        */

public class ToolBox {

    RemoteWebDriver driver;
    Logger log;
 //   Logger log2;
    //    Logger log3;
    WebDriverWait wait;

    public ToolBox(RemoteWebDriver driver) {
        this.driver = driver;
        // перший логгер
        log = Logger.getLogger ("firstLogger");
        // другий логгер

        // вгадай: який це № логгеру?
        //log3 = Logger.getLogger ("thirdLogger");
        wait = new WebDriverWait (driver, 20);

    }

    //	Цей метод просто пише в лог-файл те, що отримує на вході
    public void protocolirenTool(String somethingToBeProtocoliren) {
        log.info (somethingToBeProtocoliren);
    }



// Цей метод надсилає емаіл з Укр.нет

    public static void sendEmailTool(String[] args) {

        String to = Variables.sendEmailTo; // адреса отримувача повідомлення
        String cc = Variables.sendEmailTo2; // адреса отримувача копії повідомлення
        String cc2 = Variables.sendEmailTo4; // адреса отримувача SMS
        String from = Variables.sendEmailFrom; // адреса відправника повідомлення
        String host = Variables.sendEmailHost; // SMTP сервер, що використовується для відправлення повідомлення
        int port = Variables.sendEmailPort; // порт SMTP серверу

        // Создание свойств, получение сессии
        Properties props = new Properties ();

        // При использовании статического метода Transport.send()
        // необходимо указать через какой хост будет передано сообщение
        props.put ("mail.smtp.host", host);
        // Если почтовый сервер использует SSL
        props.put ("mail.smtp.ssl.enable", "true");
        // Указываем порт SMTP сервера.
        props.put ("mail.smtp.port", port);
        // Большинство SMTP серверов, используют авторизацию.
        props.put ("mail.smtp.auth", "true");
        // Включение debug-режима
        props.put ("mail.debug", "true");
        // Авторизируемся.
        Session session = Session.getDefaultInstance (props, new javax.mail.Authenticator () {
            // Указываем логин пароль, от почты, с которой будем отправлять сообщение.
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                //return new PasswordAuthentication("dorado2@ukr.net", "barbatsutsa");
                return new PasswordAuthentication (Variables.sendEmailFrom, Variables.sendEmailPass);
            }
        });

        try {
            // Создание объекта сообщения
            Message msg = new MimeMessage (session);

            // Установка атрибутов сообщения
            msg.setFrom (new InternetAddress (from));
            InternetAddress[] address = {new InternetAddress (to)};
            InternetAddress[] address2 = {new InternetAddress (cc), new InternetAddress (cc2)};
            msg.setRecipients (Message.RecipientType.TO, address);
            msg.setRecipients (Message.RecipientType.CC, address2);
            msg.setSubject (Variables.sendEmailSubject);
            msg.setSentDate (new Date ());

            // Установка тела сообщения
            msg.setText (Variables.sendEmailText);

            // Отправка сообщения
            Transport.send (msg);
        } catch (MessagingException mex) {
            // Печать информации об исключении в случае его возникновения
            mex.printStackTrace ();
        }
    }


    // Цей метод надсилає файл з логом

    public static void emailAttachmentTool(String[] args) {

        String to = Variables.sendEmailTo; // адреса отримувача повідомлення
        String cc = Variables.sendEmailTo2; // адреса отримувача повідомлення
        String cc2 = Variables.sendEmailTo3; // адреса отримувача повідомлення
        String from = Variables.sendEmailFrom; // адреса відправника повідомлення
        String host = Variables.sendEmailHost; // SMTP сервер, що використовується для відправлення повідомлення
        int port = Variables.sendEmailPort; // порт SMTP серверу

        // Создание свойств, получение сессии
        Properties props = new Properties ();

        // При использовании статического метода Transport.send()
        // необходимо указать через какой хост будет передано сообщение
        props.put ("mail.smtp.host", host);
        // Если почтовый сервер использует SSL
        props.put ("mail.smtp.ssl.enable", "true");
        // Указываем порт SMTP сервера.
        props.put ("mail.smtp.port", port);
        // Большинство SMTP серверов, используют авторизацию.
        props.put ("mail.smtp.auth", "true");
        // Включение debug-режима
        props.put ("mail.debug", "true");
        // Авторизируемся.
        Session session = Session.getDefaultInstance (props, new javax.mail.Authenticator () {
            // Указываем логин пароль, от почты, с которой будем отправлять сообщение.
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication (Variables.sendEmailFrom, Variables.sendEmailPass);
            }
        });

        try {
            // Создание объекта сообщения
            Message msg = new MimeMessage (session);

            // Установка атрибутов сообщения
            msg.setFrom (new InternetAddress (from));
            InternetAddress[] address = {new InternetAddress (to)};
            InternetAddress[] address2 = {new InternetAddress (cc), new InternetAddress (cc2)};
            msg.setRecipients (Message.RecipientType.TO, address);
            msg.setRecipients (Message.RecipientType.CC, address2);
            msg.setSubject (Variables.sendEmailSubject);
            msg.setSentDate (new Date ());
            // цей емаіл складається з двох частин - тексту і двох файлів, що додаються - атачменту *.log
            // та атачменту *.html
            BodyPart emailAttachmentOne = new MimeBodyPart ();
            String myfileOne = ".\\log\\FNL_Monitoring.log";
            DataSource sourceOne = new FileDataSource (myfileOne);
            emailAttachmentOne.setDataHandler (new DataHandler (sourceOne));
            emailAttachmentOne.setFileName (myfileOne);


            // текст
            BodyPart emailText = new MimeBodyPart ();
            emailText.setText (Variables.sendEmailText = "Attached please find Funnels availability log of the " + currentDate);
            // складаємо лист з однією частиною
            Multipart multipart = new MimeMultipart ();
            multipart.addBodyPart (emailText);
            multipart.addBodyPart (emailAttachmentOne);

            msg.setContent (multipart);
            // Надсилаємо листа
            Transport.send (msg);
        } catch (MessagingException mex) {
            // Печать информации об исключении в случае его возникновения
            mex.printStackTrace ();
        }
    }



    /**
     * Вносить інфу до текстового поля
     *
     * @param LocatorNameFromUIMapping
     * @param text
     * @return
     */
    public boolean writeTextIntoInputFieldTool(String LocatorNameFromUIMapping, String text) {
        try {
            //	WebElement tempInput =  wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(ui(LocatorNameFromUIMapping))));

            //	wait();
            WebElement tempInput = driver.findElement (ui (LocatorNameFromUIMapping));
	/*  if
		(tempInput.isDisplayed()
		//&& tempInput.isEnabled()
		) {
	*/
            tempInput.clear ();
//            JavascriptExecutor executor = (JavascriptExecutor)driver;
//            executor.executeScript("document.getElementById("+tempInput+").value='"+text+"';");
            tempInput.sendKeys (text);
            log.info (Variables.writeSomethingIntoLogFile + text + " was inputed");
/*
}
else{
log.info("Text " + text + " can't be typed-in into the input ");
}
*/
            return true;
        } catch (Exception e) {
            log.error ("Text " + text + " can't be typed-in into this input: " + e);
            return false;
        }

    }

    public boolean writeTextIntoInputField2Tool(String LocatorNameFromUIMapping, String text) {
        try {
//            	WebElement tempInput =  wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(ui(LocatorNameFromUIMapping))));
//            	wait();
            WebElement tempInput = driver.findElement (ui (LocatorNameFromUIMapping));
	/*  if
		(tempInput.isDisplayed()
		//&& tempInput.isEnabled()
		) {
	*/

//            ((JavascriptExecutor) driver).executeScript("document.getElementsByID("+tempInput+".removeAttribute('data-val-regex-pattern');");
//            WebElement input= driver.findElement(By.id("SSN"));
//            input.clear();
//            input.sendKeys(text);
//            JavascriptExecutor executor = (JavascriptExecutor)driver;
//            executor.executeScript("document.getElementById("+tempInput+").value='"+text+"';");
            tempInput.clear ();
            tempInput.sendKeys (text);
   //         log.info (Variables.writeSomethingIntoLogFile + " was inputed");
/*
}
else{
log.info("Text " + text + " can't be typed-in into the input ");
}
*/
            return true;
        } catch (Exception e) {
            log.error ("Text " + text + " can't be typed-in into this input: " + e);
            return false;
        }

    }


    /**
     * Натискач кнопки
     *
     * @param LocatorNameFromUIMapping
     * @return
     */
    public boolean clickOnButtonTool(String LocatorNameFromUIMapping) {
        try {
            //	wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(ui(LocatorNameFromUIMapping))));
            //   wait.until(ExpectedConditions.not(ExpectedConditions.presenceOfAllElementsLocatedBy(ui(LocatorNameFromUIMapping))));
            //driver.findElement (By.xpath (".//*[@type='submit']")).click ();
            driver.findElement (ui (LocatorNameFromUIMapping)).click ();
            return true;
        } catch (Exception e) {
            log.error ("Catch" + e);
            return false;
        }
    }


    /**
     * Відкриває нову сторінку
     *
     * @param url
     */

    public String openInNewWindowTool(String url) throws IOException {
        String name = "AnotherWindow";
        //String ulr = getCfgValue("CRM_URL");
        ((JavascriptExecutor) driver)
                .executeScript ("window.open(arguments[0],\"" + name + "\")", url);
        return name;
    }

    // ================= перевіряємо сторінку, на яку ми потрапили ===========
    public String whatUrlWeGotToTool(String currentURL) {
        currentURL = driver.getCurrentUrl ();
        Variables.currentURL = currentURL;
        log.info ("We got to the                     " + Variables.currentURL);
        return currentURL;
    }

    public void whatUrlWeAreAtDEVorPRODTool(String currentURL) {
        try {
            currentURL = driver.getCurrentUrl ();

            if (currentURL.startsWith ("http://dev.1800option.com/?password-protected=login&redirect_to=")
                    || currentURL.startsWith ("https://dev.1800option.com/?password-protected=login&redirect_to=")) {
                Variables.pageDEVorPROD = "DEV";
                writeTextIntoInputFieldTool ("devPass.input", "buno123");
                clickOnButtonTool ("devPass.SubmitButton");
                currentURL = driver.getCurrentUrl ();
                log.info ("We are at the DEV - " + currentURL);
            } else {
                Variables.pageDEVorPROD = "PROD";
                log.info ("We are in the PROD - " + getCfgValue ("Main_URL"));


            }

        } catch (Exception e) {
            log.error ("It was impossible to determine what URL we are at");

        }
    }


    /**
     * Перевіряє чи доступний на сторінці якийсь елемент
     * Method check that element is Displayed and is Enable
     *
     * @param elementLocator
     * @return
     */
    public boolean isElementPresentOnPageTool(String elementLocator) {
        WebElement tempElement;
        try {
//            wait.until(ExpectedConditions.elementToBeSelected(driver.findElement(ui(elementLocator))));
            tempElement = driver.findElement (ui (elementLocator));
            if
                    (tempElement.isDisplayed () && tempElement.isEnabled ()) {
                log.info ("Element " + elementLocator + " is present");
                return true;
            } else {
                log.info ("Element \"" + elementLocator + "\" is not present");
                return false;
            }

        } catch (Exception e) {
//            log.error("Catch" + e);
            log.info ("Element \"" + elementLocator + "\" is not present");
            return false;
        }

    }


    //Прибираємо кукі
    public void deleteAllCookiesTool(String url) {
        driver.manage ().deleteAllCookies ();
    }


    //Садистський метод вставки даних в поле - за допомогою JavaScript
    public void fieldInputWithJStool(String toField) {
        try {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript ("arguments[0].setAttribute('value', '" + Variables.currentDataToInputInto + "')",
                    driver.findElement (ui (Variables.currentFieldToInputInto)));
            Thread.sleep (1500);
        } catch (Exception e) {
            System.out.println ("Can't Ctrl-X --> Ctrl-V with this field");
        }
    }


}




