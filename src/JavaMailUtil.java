import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Scanner;

public class JavaMailUtil {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter the receiver: ");
        String recever = scanner.nextLine();
        JavaMailUtil.sendMail(recever);
    }
    public static void sendMail(String recepient) {
        System.out.println("Loading...");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        String myAccount = "karla.marsia2@gmail.com";
        String password = "krooshmasary";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccount,password);
            }
        });
        Message message = prepareMessage(session,myAccount,recepient);
        try {
            Transport.send(message);
            System.out.println("Message Sent!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    private static Message prepareMessage(Session session, String myAccount, String recepint) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccount));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepint));
            message.setSubject("My First Email From Java Program!");
            message.setText("Hello there!, \nHow are you today!");
            return message;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }
}