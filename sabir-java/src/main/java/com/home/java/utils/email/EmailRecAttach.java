package com.home.java.utils.email;

	import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;

	public class EmailRecAttach {
	  public static void main (String args[]) 
	      throws Exception {

	    String host = "tnets.com.sg";
		String username = "sabeer";
		String password = "bismillah";

	    // Get session
	    Session session = Session.getInstance(
	      new Properties(), null);

	    // Get the store
	    Store store = session.getStore("imap");
	    store.connect(host, username, password);

	    // Get folder
	    Folder folder = store.getFolder("INBOX");
	    folder.open(Folder.READ_ONLY);

	    BufferedReader reader = new BufferedReader (
	      new InputStreamReader(System.in));

	    // Get directory
	    Message message[] = folder.getMessages();
	    for (int i=0, n=message.length; i<n; i++) {
	       System.out.println(i + ": "
	         + message[i].getFrom()[0] 
	         + "	" + message[i].getSubject());

	      System.out.println("Do you want to get the content?	           [YES to read/QUIT to end]");
	      String line = reader.readLine();
	      if ("YES".equals(line)) {
	        Object content = message[i].getContent();
	        if (content instanceof Multipart) {
	          handleMultipart((Multipart)content);
	        } else {
	          handlePart(message[i]);
	        }
	      } else if ("QUIT".equals(line)) {
	        break;
	      }
	    }

	    // Close connection 
	    folder.close(false);
	    store.close();
	  }
	  public static void handleMultipart(Multipart multipart) 
	      throws MessagingException, IOException {
	    for (int i=0, n=multipart.getCount(); i<n; i++) {
	      handlePart(multipart.getBodyPart(i));
	    }
	  }
	  public static void handlePart(Part part) 
	      throws MessagingException, IOException {
	    String disposition = part.getDisposition();
	    String contentType = part.getContentType();
	    if (disposition == null) { // When just body
	      System.out.println("Null: "  + contentType);
	      // Check if plain
	      if ((contentType.length() >= 10) && 
	          (contentType.toLowerCase().substring(
	           0, 10).equals("text/plain"))) {
	        part.writeTo(System.out);
	      } else { // Don't think this will happen
	        System.out.println("Other body: " + contentType);
	        part.writeTo(System.out);
	      }
	    } else if (disposition.equalsIgnoreCase(Part.ATTACHMENT)) {
	      System.out.println("Attachment: " + part.getFileName() + 
	        " : " + contentType);
	      saveFile(part.getFileName(), part.getInputStream());
	    } else if (disposition.equalsIgnoreCase(Part.INLINE)) {
	      System.out.println("Inline: " + 
	        part.getFileName() + 
	        " : " + contentType);
	      saveFile(part.getFileName(), part.getInputStream());
	    } else {  // Should never happen
	      System.out.println("Other: " + disposition);
	    }
	  }
	  public static void saveFile(String filename,
	      InputStream input) throws IOException {
	    if (filename == null) {
	      filename = File.createTempFile("xx", ".out").getName();
	    }
	    // Do no overwrite existing file
	    File file = new File(filename);
	    for (int i=0; file.exists(); i++) {
	      file = new File(filename+i);
	    }
	    FileOutputStream fos = new FileOutputStream(file);
	    BufferedOutputStream bos = new BufferedOutputStream(fos);

	    BufferedInputStream bis = new BufferedInputStream(input);
	    int aByte;
	    while ((aByte = bis.read()) != -1) {
	      bos.write(aByte);
	    }
	    bos.flush();
	    bos.close();
	    bis.close();
	  }
	}
