/*
 * To change this template file, choose Settings | Editor | File and Code Templates
 * and change the template in the editor.
 */

package JavaMail;

import com.sun.mail.smtp.SMTPTransport;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author Ahmed Abuwarda.
 */
public class SendEmailWithJava {

    // Data Fields.
    private static final String EMAIL_TO = "YourMail@anyMail.com";
    private static final String EMAIL_SUBJECT = "Your Subject";
    private static final String EMAIL_TEXT = "Type Your Message Here!";
    private static final String EMAIL_FROM = "YourMail@AnyMail.com";
    private static final String EMAIL_PASSWORD = "YourPassword";

    /**
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        // TODO code application logic here.

        System.out.println("Loading Data...");

        // Create New Properties.
        Properties properties = System.getProperties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com"); //optional, defined in SMTPTransport
        properties.put("mail.smtp.port", "587"); // default port 587

        // Create New Session.
        Session session = Session.getInstance(properties, null);
        Message message = new MimeMessage(session);

        // Try Catch Block.
        try {

            // The Message.
            message.setFrom(new InternetAddress(EMAIL_FROM));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(EMAIL_TO, false));
            message.setSubject(EMAIL_SUBJECT);
            message.setText(EMAIL_TEXT);

            // The Connection.
            System.out.println("Connecting...");
            SMTPTransport transport = (SMTPTransport) session.getTransport("smtp");
            transport.connect("smtp.gmail.com", EMAIL_FROM, EMAIL_PASSWORD);
            transport.sendMessage(message, message.getAllRecipients());
            System.out.println("Response: " + transport.getLastServerResponse() + "Message Sent!");
            transport.close();

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

}