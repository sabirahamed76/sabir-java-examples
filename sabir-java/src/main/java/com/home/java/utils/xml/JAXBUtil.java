package com.home.java.utils.xml;

import java.io.File;
import java.io.FileOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


public class JAXBUtil {

	public static void marshal(String pkgName,String xmlFileName,Object obj)throws Exception{
		try {	
			JAXBContext jc = JAXBContext.newInstance(pkgName);			
			Marshaller m = jc.createMarshaller();			
			m.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );		
			m.marshal(obj, new FileOutputStream(xmlFileName));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error while generating XML..", e);
		}

	}
	
	public static Object unMarshal(Class clsName,String xmlFileName)throws Exception {
		try {
			File file = new File(xmlFileName);
			JAXBContext jaxbContext = JAXBContext.newInstance(clsName);		
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Object obj = (Object) jaxbUnmarshaller.unmarshal(file);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error while Parsing XML..", e);
		}
	}
	
}
