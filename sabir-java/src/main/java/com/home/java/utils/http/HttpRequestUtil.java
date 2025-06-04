package com.home.java.utils.http;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

public class HttpRequestUtil {
	/**
	 * Method to get Remote InetAddress from HttpRequest Header
	 * @param request HttpServletRequest
	 * @return InetAddress Object
	 */
	public static InetAddress getRemoteInetAddress(final HttpServletRequest request){
		InetAddress inetAddress = null;
		try {
			if (request.getHeader("x-forwarded-for") != null) {
				inetAddress =  InetAddress.getByName(request.getHeader("x-forwarded-for"));
			}

			inetAddress =  InetAddress.getByName(request.getRemoteAddr());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}		
		return inetAddress;
	}
	
	/**
	 * Method to get Remote IP address(ie Client IP) from request header.
	 * @param request HttpServletRequest
	 * @return return ipAddress as String
	 */
	public static String getRemoteIP(final HttpServletRequest request){
		if (request.getHeader("x-forwarded-for") != null) {
			return request.getHeader("x-forwarded-for");
		}
		return request.getRemoteAddr();
	}
	
	/**
	 * Method to check if given IPAddress exist with in range of IPAddress
	 * @param startIP Start IPAddress
	 * @param endIP   End IPAddress
	 * @param searchIP IPAddress to search between Start and End IPAddress
	 * @return boolean value of true if exist, otherwise false
	 */
	public static boolean checkIPRange(String startIP, String endIP, String searchIP){
		long ipLo = 0;
		long ipHi = 0;
		long ipToTest = 0;
		try {
			ipLo = converIPToLong(InetAddress.getByName(startIP)); 
			ipHi = converIPToLong(InetAddress.getByName(endIP)); 
			ipToTest = converIPToLong(InetAddress.getByName(searchIP));
		} catch (UnknownHostException e) {		
			e.printStackTrace();
		}  
        return (ipToTest >= ipLo && ipToTest <= ipHi); 
	}
	
	private static long converIPToLong(InetAddress ip) { 
        byte[] octets = ip.getAddress(); 
        long result = 0; 
        for (byte octet : octets) { 
            result <<= 8; 
            result |= octet & 0xff; 
        } 
        return result; 
    } 
}
