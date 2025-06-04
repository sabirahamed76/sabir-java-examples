package com.home.java.utils.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class DOMParserUtil {
	
	   private String fileName;
	   private boolean resourceInClasspath = true;
	   private Document rootDocument;
	   private HashMap<String,Element> elementsMap = new HashMap<String,Element>();
	   private List<Element> elementsList = new ArrayList<Element>();
	   
	   SAXReader saxReader = null;
	   public DOMParserUtil(){	   
	   }
	   
	   public DOMParserUtil(String fileName){
		   this.fileName = fileName;
		   saxReader = new SAXReader();
	   }
	   
	   public DOMParserUtil(String fileName, boolean resourceInClasspath){
		   this.fileName = fileName;
		   this.resourceInClasspath = resourceInClasspath;
		   saxReader = new SAXReader();
	   }
	   
	   public Document getRootDocument() throws Exception{	   	   
		   return rootDocument;	   
	   }
	   
	   public Element getRootElement() throws Exception{	   	   
		   if(this.rootDocument == null){
			   return null;
		   }
		   return this.rootDocument.getRootElement();
	   }
	    
	   public void parse() throws Exception{
		   if(this.fileName == null){
			   throw new Exception("File to be read not specified");
		   }
		   InputStream inS = null;
		   try {
			   if(resourceInClasspath) {
				   inS =   this.getClass().getResourceAsStream("/"+fileName);
			   } else {
				   File f = new File(fileName);
				   inS = new FileInputStream(f);
			   }
			   if(inS == null) {
				   throw new Exception("File not found");
			   }
			   
			   this.rootDocument = saxReader.read(inS);
			   Element rootElement = this.rootDocument.getRootElement();	
			   if(rootElement != null){
			    Iterator<Element> elementIterator = rootElement.elementIterator();
				for ( Iterator<Element> i = elementIterator; i.hasNext(); ) {
			           Element element = i.next();
			      	   elementsMap.put(element.getName(), element);
			      	   elementsList.add(element);
				   }	   
			   }
			   
		   } finally {
			   if(inS != null) {
				   inS.close();
			   }
		   }
	   }  
	   
	   
	   public List<Element> getElementsList() throws Exception{
		   if(this.rootDocument == null){
			   this.parse();
		   }		   
		   return elementsList;
	   }
	   
	   public HashMap<String,Element> getElementsMap() throws Exception{
		   if(this.rootDocument == null){
			   this.parse();
		   }	
		   return this.elementsMap;
	   }
		
	   public Element getElement(String elementName) throws Exception{
		   if(this.rootDocument == null){
			   this.parse();
		   }
		   if(elementsMap == null) {
			   return null;
		   }
		   
		   return elementsMap.get(elementName);
	   }
	   
	   public List<Element> getElementList(String elementName) throws Exception{
		   if(this.rootDocument == null){
			   this.parse();
		   }
		   if(elementsList == null) {
			   return null;
		   }
		   List<Element> elementList = new ArrayList<Element>();
		   for (Iterator iterator = this.elementsList.iterator(); iterator.hasNext();) {
			   Element element = (Element) iterator.next();
			   if(elementName.equals(element.getName())){
				   elementList.add(element);
			   }
		   }		   
		   return elementList;   
	  } 
	    
}
