package com;
// File Name SendEmail.java

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import com.sun.mail.smtp.SMTPTransport;

import javax.activation.*;

public class SendEmail
{
   public static void main(String [] args)
   {  
	   final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory"; 
	   
      // Recipient's email ID needs to be mentioned.
      String to = "divyank.dubey2@gmail.com";

      // Sender's email ID needs to be mentioned
      String from = "divyank.dubey@gmail.com";

      // Assuming you are sending email from localhost
      String host = "smtp.gmail.com";

      // Get system properties
      Properties properties = System.getProperties();

      // Setup mail server
     /* properties.setProperty("mail.smtp.host", "ssl://smtp.gmail.com");
      properties.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
      properties.setProperty("mail.smtp.socketFactory.fallback", "false");
      properties.setProperty("mail.smtp.port", "465");
      properties.setProperty("mail.smtp.socketFactory.port", "465");
      properties.put("mail.smtp.auth", "true");
      properties.put("mail.smtp.starttls.enable", "true");
      properties.put("mail.debug", "true");
      properties.put("mail.store.protocol", "pop3");
      properties.put("mail.transport.protocol", "smtp");*/
      Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		// Please enter proper mail id and password below
      final String username = "urmail@gmail.com";//
      final String password = "urpassword";
      try{
      // Get the default Session object.
      Session session = Session.getDefaultInstance(props, new GMailAuthenticator(username, password));
    
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

         // Set Subject: header field
         message.setSubject("This is the Subject Line!");
         
         // Now set the actual message
         message.setText("This is actual message");

         // Send message
      
         SMTPTransport trans = new SMTPTransport(session,new URLName(host));         trans.connect(host, username, password);
         System.out.println(trans.isConnected());
         trans.sendMessage(message, message.getAllRecipients());
         

         System.out.println("Sent message successfully....");
      }catch (MessagingException mex) {
         mex.printStackTrace();
      }
   }
}