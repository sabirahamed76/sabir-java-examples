/*
 * WrapperClass.java
 *
 * Created on December 11, 2009, 9:48 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.home.java.lang.Wrapper;

public class WrapperDemo {
	public static void main(String args[]) {
		Boolean b1 = new Boolean("TRUE");
		Boolean b2 = new Boolean("FALSE");
		System.out.println(b1.toString()+" or "+b2.toString());
		for(int j=0;j<16;++j)
		System.out.print(Character.forDigit(j,16));
		System.out.println();
		
		
		Integer i = new Integer(Integer.parseInt("ef",16));
		Long l = new Long(Long.parseLong("abcd",16));
		long m=l.longValue()*i.longValue();
		System.out.println(Long.toString(m,8));
		System.out.println(Float.MIN_VALUE);
		System.out.println(Double.MAX_VALUE);

		System.out.println(Math.E);
		System.out.println(Math.PI);
		System.out.println(Math.abs(-1234));
		System.out.println(Math.cos(Math.PI/4));
		System.out.println(Math.sin(Math.PI/2));
		System.out.println(Math.tan(Math.PI/4));
		System.out.println(Math.log(1));
		System.out.println(Math.exp(Math.PI));
		for( i=0;i<3;++i)
		System.out.print(Math.random()+" ");
		System.out.println();

		String s = " Java 2 Certification ";
		System.out.println(s);
		System.out.println(s.toUpperCase());
		System.out.println(s.toLowerCase());
		System.out.println("["+s+"]");
		s=s.trim();
		System.out.println("["+s+"]");
		s=s.replace('J','X');
		s=s.replace('C','Y');
		s=s.replace('2','Z');
		System.out.println(s);
		int i1 = s.indexOf('X');
		int i2 = s.indexOf('Y');
		int i3 = s.indexOf('Z');
		char ch[] = s.toCharArray();
		ch[i1]='J';
		ch[i2]='C';
		ch[i3]='2';
		s = new String(ch);
		System.out.println(s);

		StringBuffer sb = new StringBuffer(" is ");
		sb.append("Hot");
		sb.append('!');
		sb.insert(0,"Java");
		sb.append('\n');
		sb.append("This is ");
		sb.append(true);
		sb.setCharAt(21,'T');
		sb.append('\n');
		sb.append("Java is #");
		sb.append(1);
		String s1 = sb.toString();
		System.out.println(s1);
	}
}