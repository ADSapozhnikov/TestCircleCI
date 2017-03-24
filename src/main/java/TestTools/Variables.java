package TestTools;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Eugene.Korogodsky on 07.09.2016.
 */
public class Variables
{
    // Інші тимчасові змінні

    public static String writeSomethingIntoLogFile; // записуємо щось у лог-файл
    public static String writeSomethingIntoLogFile2; // записуємо щось у 2й лог-файл
    //public static String writeSomethingIntoLogFile3; // записуємо щось у 3й лог-файл
    public static String tmpString; // тимчасова змінна
    public static String tmpString2; // тимчасова змінна 2
    public static int tmpIntString; // тимчасова цілочислена змінна
    public static String pageDEVorPROD; // на який сервер спрямовує сторінка - DEV чи PROD
    public static String currentURL;
    public static String currentURLtxt;
    // Створюємо email (тип - string) для внесення до відповідних полів
    static SimpleDateFormat Current_Date = new SimpleDateFormat("yyyyMMddHHmmss");
    public static SimpleDateFormat Current_Date_and_Time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat CurrentDateandTime = new SimpleDateFormat("yyyy_MM_dd   HH_mm_ss");
    public static String dateString = Current_Date.format(new Date()); //поточна дата
    public static String currentDate = Current_Date_and_Time.format(new Date()); //поточна дата та час
    public static String currentDateForFile = CurrentDateandTime.format(new Date ());//поточна дата та час для підстановки у файл
    public static String currentDate1;
    //public static String sendEmailTo = "Zarembo222@gmail.com";
    public static String sendEmailTo = "d3bugg3r@gmail.com";
    public static String sendEmailTo2 = "Eugene.Korogodsky@innovecs.com";
    public static String sendEmailTo3 = "Global.QA@ukr.net";
    //public static String sendEmailTo4 = "Zarembo222@gmail.com";// email кому надсилаємо SMS-повідомлення
    public static String sendEmailTo4 = "380683587094@sms.kyivstar.net";// email кому надсилаємо SMS-повідомлення
    public static String sendEmailFrom = "dorado2@ukr.net"; // email з якого надсилаємо повідомлення
    public static String sendEmailPass = "barbatsutsa"; // пароль від поштової скриньки
    public static String sendEmailHost = "smtp.ukr.net"; // SMTP з якого надсилаємо повідомлення
    public static int sendEmailPort = 2525; // № порту з якого надсилаємо повідомлення
    public static String sendEmailSubject = "!!!Funnel issue. Please take a look!!!"; // тема повідомлекння - наразі - суто для зразку
    public static String sendEmailText; // текст повідомлення
    // для використання з файлом XLS
    public static int xlsRowNumber; // № рядку в XLS файлі
    public static int xlsColumnNumber; //№ стовпчику в XLS файлі
    public static String xlsColumnLetter; //№ стовпчику в XLS файлі
    public static int counter = 0; // просто каунтер
    //Тимчасові змінні для заповнення полів
    public static String currentFieldToInputInto; // в яке поле надо внести дані
    public static String currentDataToInputInto;  // що вносимо
    public static String currentButtonToClick;    // яку кнопку тиснемо



    // Змінні, що описують поточний стан даних, внесених на сторінці BU/register по клієнту (open free account)
    public static String registerEmail; // що внесено до поля Email
    public static String registerFirstName; // що внесено до поля FirstName
    public static String registerLastName; // що внесено до поля LastName
    public static String registerCountry; // що внесено до поля Country
    public static String registerPhonePrefix; // що внесено до поля PhonePrefix
    public static String registerPhoneNumber; // що внесено до поля PhoneNumber
    public static String registerPasswordInput; // що внесено до поля Password
    public static String registerPasswordRepeat; // що внесено до поля PasswordRepeat
    public static String registerCurrency; // що внесено до поля Currency

   //перелік поточних фьюнелів

    public static String googleShits[] = {
            "https://spreadsheets.google.com/feeds/list/1qc4qwv4bB_pmIaiGgQVLGFTVDr7cVKTR917Vo1jrWWc/default/public/values",
            "https://spreadsheets.google.com/feeds/list/1qc4qwv4bB_pmIaiGgQVLGFTVDr7cVKTR917Vo1jrWWc/2/public/values"};
    public static ArrayList <String> todayFunnelsList ;//які фьюнели мають перевірятись згідно https://docs.google.com/spreadsheets/d/1qc4qwv4bB_pmIaiGgQVLGFTVDr7cVKTR917Vo1jrWWc/edit?pref=2&pli=1#gid=0
    public static ArrayList <String> buFunnelsLinks ;//фьюнлів, що базуються на binaryuno.com
    public static ArrayList <String> buFunnelsTitles ;//перелік заголовків фьюнлів, що базуються на binaryuno.com
    public static ArrayList <String> currentFunnelsLinks ;//перелік лінків фьюнлів, що базуються на інших доменах
    public static ArrayList <String> currentFunnelsSecondStepLinks ;//перелік лінків 2х степів фьюнлів, що базуються на інших доменах
    public static ArrayList <String> currentFunnelsTitles ;//перелік заголовків фьюнлів, що базуються на інших доменах
    public static ArrayList <String> currentFunnelsSecondStepTitles ;//перелік заголовків 2х степів фьюнлів, що базуються на інших доменах
    public static ArrayList <String> currentDomainsLinks ;//перелік доменів, на яких базуються фьюнели
    public static ArrayList <String> totalFunnelsLinks;//перелік фьюнлів з різними доменами
    public static ArrayList <String> totalFunnelsTitles;//перелік заголовків фьюнлів з різними доменами
    public static String strPageTitle;//поточний тайтл
    // Хедери фьюнелів
    public static String appTitle;

    public static String googleShits2[] = {

            "https://spreadsheets.google.com/feeds/list/17-6pk_-bq04FAw3CbEUZ7nT_Fe_oOc6u6X7y6PFRhVs/1/public/values",
            "https://spreadsheets.google.com/feeds/list/17-6pk_-bq04FAw3CbEUZ7nT_Fe_oOc6u6X7y6PFRhVs/2/public/values",
            "https://spreadsheets.google.com/feeds/list/17-6pk_-bq04FAw3CbEUZ7nT_Fe_oOc6u6X7y6PFRhVs/3/public/values"
    };


    // фотографування екрану

    public static int screenSize = 0; // розмір екрану
    public static int screenWidth = 1920; // Ширина браузеру
    public static int screenHeight = 1080; // Висота браузеру
    public static int screenZoom = 100; // Зум екрану

    public static String browserType; // в якому браузері відкривається





}

