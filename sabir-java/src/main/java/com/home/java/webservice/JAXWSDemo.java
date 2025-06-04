package com.home.java.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;
 
@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public class JAXWSDemo {
 
	@WebMethod
	public String sayHello(String msg){
	return "Hello "+msg;
	}
	 
	public static void main(String[] args){
		Endpoint.publish("<a href=\"http://localhost:8888/DemoWS\">http://localhost:8888/DemoWS</a>", new JAXWSDemo());
	}
}