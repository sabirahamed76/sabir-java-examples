package com.home.java.utils.email;

import java.util.Iterator;
import java.util.Properties;
import java.util.TreeSet;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

/**
 * My JavaMail email address extractor.
 * A JavaMail API example.
 * @author alvin alexander, devdaily.com.
 */
public class AddressExtractor
{

  public static void main(String[] args)
  {
    Properties props = new Properties();

    
    String host      = "mail.tnets.com.sg";
    String username  = "sabeer";
    String password  = "bismillah";
    //String provider  = "pop3";
    String provider  = "imap";

    try
    {
      //Connect to the server
      Session session = Session.getDefaultInstance(props, null);
      Store store     = session.getStore(provider);
      store.connect(host, username, password);

      //open the inbox folder
      Folder inbox = store.getFolder("INBOX");
      inbox.open(Folder.READ_ONLY);

      // get a list of javamail messages as an array of messages
      Message[] messages = inbox.getMessages();

      TreeSet treeSet = new TreeSet();

      for(int i = 0; i < messages.length; i++)
      {
        String from = getFrom(messages[i]) + "==========Recived on="+ messages[i].getReceivedDate() +"==========Sent Date"+messages[i].getSentDate()+"==========Subject"+messages[i].getSubject() ;
        if ( from!=null)
        {
          from = removeQuotes(from);
          treeSet.add(from);
        }
      }

      Iterator it = treeSet.iterator();
      while ( it.hasNext() )
      {
        System.out.println("from: " + it.next());
      }

      //close the inbox folder but do not
      //remove the messages from the server
      inbox.close(false);
      store.close();
    }
    catch (NoSuchProviderException nspe)
    {
      System.err.println("invalid provider name");
    }
    catch (MessagingException me)
    {
      System.err.println("messaging exception");
      me.printStackTrace();
    }
  }

  private static String getFrom(Message javaMailMessage) 
  throws MessagingException
  {
    String from = "";
    Address a[] = javaMailMessage.getFrom();
    if ( a==null ) return null;
    for ( int i=0; i<a.length; i++ )
    {
      Address address = a[i];
      from = from + address.toString();
    }

    return from;
  }
  
  private static String getSubject (Message javaMailMessage) 
  throws MessagingException
  {
    return javaMailMessage.getSubject();
  }

  private static String removeQuotes(String stringToModify)
  {
    int indexOfFind = stringToModify.indexOf(stringToModify);
    if ( indexOfFind < 0 ) return stringToModify;

    StringBuffer oldStringBuffer = new StringBuffer(stringToModify);
    StringBuffer newStringBuffer = new StringBuffer();
    for ( int i=0, length=oldStringBuffer.length(); i<length; i++ )
    {
      char c = oldStringBuffer.charAt(i);
      if ( c == '"' || c == '\'' )
      {
        // do nothing
      }
      else
      {
        newStringBuffer.append(c);
      }

    }
    return new String(newStringBuffer);
  }

}
