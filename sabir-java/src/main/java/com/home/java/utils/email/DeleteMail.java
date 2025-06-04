package com.home.java.utils.email;



	import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;

	public class DeleteMail {
	  public static void main (String args[]) throws Exception {
		  String host = "smtp.tnets.com.sg";
	    String username = "sabeer";
	    String password = "bismillah";

	    // Get session
	    Session session = Session.getInstance(
	      System.getProperties(), null);

	    // Get the store
	    Store store = session.getStore("imap");
	    store.connect(host, username, password);

	    // Get folder
	    Folder folder = store.getFolder("INBOX");
	    folder.open(Folder.READ_WRITE);

	    BufferedReader reader = new BufferedReader (
	      new InputStreamReader(System.in));

	    // Get directory
	    Message message[] = folder.getMessages();
	    for (int i=0, n=message.length; i<n; i++) {
	       System.out.println(i + ": " + message[i].getFrom()[0] 
	         + "	" + message[i].getSubject());
	       
		       System.out.println("Do you want to delete message? [YES to delete]");
		       String line = reader.readLine();
		       // Mark as deleted if appropriate
		       if ("YES".equals(line)) {
		         message[i].setFlag(Flags.Flag.DELETED, true);
		       }
	       
	    
	    }
		

	    // Close connection 
	    folder.close(true);
	    store.close();
	  }
	}
