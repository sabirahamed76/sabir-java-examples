package com.home.java.xml;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.home.java.utils.xml.JAXBUtil;



 public class JAXBSample {
	 
	 	
		
		private static final SimpleDateFormat DATE_FORMAT =  new SimpleDateFormat("dd/MM/yyyy");
		
		public static void main (String[] args){
			try{
				String clsName = "com.home.java.xml";
				String path="c:";
	            String folderPath="temp";
	            String fileName=path + "\\" + folderPath + "\\" + "genratedXml.xml";			
				path = path + "\\" + folderPath ;
				
				System.out.println("Generating XML File from db object.....");
				PermitData pd = (PermitData) generateObject();
				JAXBUtil.marshal(clsName, fileName, pd);
				System.out.println("XML file generated from the object");
							
				System.out.println("==================================");
				
				System.out.println("Generating Object from XML FIle.....");
				pd = (PermitData) JAXBUtil.unMarshal(PermitData.class, fileName);
				System.out.println("Result = " + pd.getHeader().getMsgType()+"--"+pd.getHeader().getMsgVersion());
				System.out.println("Object generated from the XML File");
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		
        
		public static PermitData generateObject(){
			PermitData pd = (new ObjectFactory()).createPermitData();
        	try{
	        	
	        	String dateFrmt = DATE_FORMAT.format(new Date()); 
	        	
				
				Header hdr = new Header();
				    
				hdr.setMsgVersion(0+"");
				hdr.setUserID(1L+"");
				hdr.setMsgType("MSG");
				hdr.setPmtType("PMT");
				hdr.setSubmissionDate(dateFrmt);
					     
	            
				pd.setHeader(hdr);
							
				InwardTransport it = new InwardTransport();
				OutwardTransport ot  = new OutwardTransport();
					     
				pd.setInwardTransport(it);
				pd.setOutwardTransport(ot);
				
				SupportDocs supDocs = new SupportDocs();
				supDocs.setDocumentID(new BigInteger("" + 1));
			    supDocs.setDocumentType("testDoc");
			    supDocs.setFileName("testFile");
							            
				pd.setSupportDocs(supDocs);
						    
	        }
	        catch( Exception e) {
	           e.printStackTrace();
	        }
	        return pd;
        }
	   
	   

}

