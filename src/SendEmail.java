import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendEmail
{
 public static void main(String [] args){
      String to = "YourMail@AnyMail.com";//change accordingly
      String from = "ReciverMail@AnyMail.com";//change accordingly
      String host = "smtp.gmail.com";//or IP address

     //Get the session object
      Properties properties = System.getProperties();
      properties.setProperty("mail.smtp.host", host);
      Session session = Session.getDefaultInstance(properties);

     //compose the message
      try{
         MimeMessage message = new MimeMessage(session);
         message.setFrom(new InternetAddress(from));
         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
         message.setSubject("Your Subject");
         message.setText("Type Your Message Here!");

         // Send message
         Transport.send(message);
         System.out.println("Sessage Sent Successfully....");

      }catch (MessagingException mex) {mex.printStackTrace();}
   }
}
