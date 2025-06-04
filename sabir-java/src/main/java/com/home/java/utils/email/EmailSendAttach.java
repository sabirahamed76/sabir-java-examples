package com.home.java.utils.email;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

	public class EmailSendAttach {
	  public static void main (String args[]) 
	      throws Exception {
		
		String host = "smtp.tnets.com.sg";
		String from = "sabeer@tnets.com.sg";
		String to = "sabeer@tnets.com.sg";
	    //String fileAttachment = "C:\\temp\\a.txt";
	    String filePath="C:\\temp\\";
	    String fileName="a.txt";
	    

	    // Get system properties
	    Properties props = System.getProperties();

	    // Setup mail server
	    props.put("mail.smtp.host", host);

	    // Get session
	    Session session = 
	      Session.getInstance(props, null);

	    // Define message
	    MimeMessage message = 
	      new MimeMessage(session);
	    message.setFrom(
	      new InternetAddress(from));
	    message.addRecipient(
	      Message.RecipientType.TO, 
	      new InternetAddress(to));
	    message.setSubject(
	      "Hello JavaMail Attachment");

	    // create the message part 
	    MimeBodyPart messageBodyPart = 
	      new MimeBodyPart();

	    //fill message
	    messageBodyPart.setText("Hi");

	    Multipart multipart = new MimeMultipart();
	    multipart.addBodyPart(messageBodyPart);

	    // Part two is attachment
	    messageBodyPart = new MimeBodyPart();
	    DataSource source =   new FileDataSource(filePath+fileName);
	    messageBodyPart.setDataHandler( new DataHandler(source));
	    messageBodyPart.setFileName(fileName);
	    multipart.addBodyPart(messageBodyPart);

	    // Put parts in message
	    message.setContent(multipart);

	    // Send the message
	    Transport.send( message );
	    System.out.println("Mail sent Successfully");
	  }
	}
