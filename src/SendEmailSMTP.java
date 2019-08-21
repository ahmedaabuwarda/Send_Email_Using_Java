import com.sun.mail.smtp.SMTPTransport;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmailSMTP {
    
    private static final String EMAIL_TO = "raitohiory@gmail.com";
    private static final String EMAIL_SUBJECT = "My New HomeWork";
    private static final String EMAIL_TEXT = "Hi Heba\n" +
            "This is my new HomeWork For the last time.\n" +
            "Thank you\n" +
            "karla";

    public static void main(String[] args) {
        System.out.println("Loading...");
        Properties properties = System.getProperties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host", "smtp.gmail.com"); //optional, defined in SMTPTransport
        properties.put("mail.smtp.port", "587"); // default port 587
        Session session = Session.getInstance(properties, null);
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress("karla.marsia2@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(EMAIL_TO, false));
            message.setSubject(EMAIL_SUBJECT);
            message.setText(EMAIL_TEXT);
            SMTPTransport transport = (SMTPTransport) session.getTransport("smtp");
            transport.connect("smtp.gmail.com", "karla.marsia2@gmail.com", "krooshmasary");
            transport.sendMessage(message, message.getAllRecipients());
            System.out.println("Response: " + transport.getLastServerResponse() + "Message Sent!");
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}