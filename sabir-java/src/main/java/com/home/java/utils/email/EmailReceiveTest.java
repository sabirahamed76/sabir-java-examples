package com.home.java.utils.email;


import java.io.IOException;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;

import com.sun.mail.imap.IMAPStore;

public class EmailReceiveTest {

	public static void main(String[] args) {

		/*String mailPop3Host = "cakelycakes.com";
		String mailStoreType = "pop3";
		String mailUser = "bigcakes";
		String mailPassword = "bigcakes";*/
		String mailPop3Host = "mail.tnets.com.sg";
		String mailStoreType = "imap";
		String mailUser = "sabeer";
		String mailPassword = "1bismillah";
		receiveEmail(mailPop3Host, mailStoreType, mailUser, mailPassword);
	}

	public static void receiveEmail(String pop3Host, String storeType, String user, String password) {

		try {
			Properties properties = new Properties();
			//properties.put("mail.IMAP.host", pop3Host);
			Session emailSession = Session.getDefaultInstance(properties);
			//Provider p=new Provider("IMAP","imap","com.sun.mail.imap.IMAPStore","Sun Microsystems", "Inc");
			
			IMAPStore emailStore = (IMAPStore) emailSession.getStore(storeType);
			emailStore.connect(pop3Host,user, password);

			Folder emailFolder = emailStore.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);

			Message[] messages = emailFolder.getMessages();
			for (int i = 0; i < messages.length; i++) {
				Message message = messages[i];
				System.out.println("==============================");
				System.out.println("Email #" + (i + 1));
				System.out.println("From: " + message.getFrom()[0]);
				//System.out.println("Receipeint List: " + message.getAllRecipients().);
				System.out.println("Subject: " + message.getSubject());
				System.out.println("Received on: " + message.getReceivedDate());
				System.out.println("Sent on: " + message.getSentDate());			
				System.out.println("Text: " + message.getContent().toString());
				
				if (i==5) break;
			}

			emailFolder.close(false);
			emailStore.close();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
