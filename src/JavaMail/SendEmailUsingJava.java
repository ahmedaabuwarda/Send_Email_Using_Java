/*
 * To change this template file, choose Settings | Editor | File and Code Templates
 * and change the template in the editor.
 */

package JavaMail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Scanner;

/**
 * @author Ahmed Abuwarda.
 */
public class SendEmailUsingJava {

    /**
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        // TODO code application logic here.

        // Read The Data From The User.
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter the receiver: ");
        String recever = scanner.nextLine();

        // Run The Method.
        SendEmailUsingJava.sendMail(recever);
        
    }

    /**
     *
     * @param recipient The Receiver Email Address.
     */
    private static void sendMail(String recipient) {

        System.out.println("Loading Data...");

        // Create New Properties.
        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");

        // Type Your Account And Password Here.
        String myAccount = "YourMail@AnyMail.com";
        String password = "YourPassword";

        // Start New Session.
        Session session = Session.getInstance(properties, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccount,password);
            }

        });

        // The Message.
        Message message = prepareMessage(session,myAccount,recipient);

        // Try Catch Block.
        try {

            Transport.send(message);
            System.out.println("Message Sent!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * @param session Start New Session.
     * @param myAccount You Email Address Account.
     * @param recepint The recipient Email Address Account.
     * @return Message Or Null
     */
    private static Message prepareMessage(Session session, String myAccount, String recepint) {

        // Try Catch Block.
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccount));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepint));
            message.setSubject("Your Subject");
            message.setText("Type Your Message Here!");
            return message;

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return null;

    }

}