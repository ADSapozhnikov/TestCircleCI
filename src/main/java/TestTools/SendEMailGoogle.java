package TestTools;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
/**
 * Created by Eugene.Korogodsky on 9/11/2016.
 * УВАГА!!! бібліотеку javax.mail maven самостійно не підтягує - треба самостійно додавати
 * звідси: http://www.java2s.com/Code/Jar/j/Downloadjavaxmailjar.htm
 * і класти до папки з jar-файлами, а потім прописати у Project Structure / Libraries
 * або прописувати залежності у POM.xml-файлі
 */
public class SendEMailGoogle {



    public static void main(String[] args) {

        final String username = "global.qa.msg.cntr";
        final String password = "barbatsutsa";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("global.qa.msg.cntr@gmail.com"));//from email
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("global.qa@ukr.net"));//to email
            message.setSubject("Testing Subject");
            message.setText("Dear Mail Crawler,"
                    + "\n\n No spam to my email, please!");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}